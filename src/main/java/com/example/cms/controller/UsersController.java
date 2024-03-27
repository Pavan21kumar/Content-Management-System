package com.example.cms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.Users;
import com.example.cms.repository.UsersRepository;
import com.example.cms.service.UsersService;
import com.example.cms.util.DuplicateEmailException;
import com.example.cms.util.ErrorStructure;
import com.example.cms.util.Responstructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UsersController {

	private UsersService service;
	
	@Operation(description = "this endpoit  is used to register the Users", responses = {
			@ApiResponse(responseCode = "200", description = "user is Register"),
			@ApiResponse(responseCode = "400", description = "invalid inputs" ,content = @Content(schema = @Schema(implementation = ErrorStructure.class))) 
			,@ApiResponse(responseCode = "404", description = "DuplicateEmail" ,content = @Content(schema = @Schema(implementation = ErrorStructure.class)))})
	@PostMapping("/users/register")
	public ResponseEntity<Responstructure<UserResponse>> userRegister(@RequestBody @Valid UserRequest userRequest) {
		
		return service.saveUsers(userRequest);
	}
	@GetMapping("/test")
	public String test()
	{
		return "Hello from cms";
	}
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Responstructure<String>>softDeleteUser(@PathVariable int userId)
	{
		return service.softDeleteUser(userId);
	}
	@GetMapping("/users/{userId}")
	public ResponseEntity<Responstructure<UserResponse>> findUniqueId(@PathVariable int userId)
	{
		return service.findUniqueId(userId);
	}
}
