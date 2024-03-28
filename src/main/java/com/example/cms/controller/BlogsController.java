package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.service.BlogsService;
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
public class BlogsController {

	private BlogsService service;
	
	@Operation(description = "this endpoit  is used to create a Blog Based On User", responses = {
			@ApiResponse(responseCode = "200", description = "Blog is Created "),
			@ApiResponse(responseCode = "400", description = "invalid inputs" ,content = @Content(schema = @Schema(implementation = ErrorStructure.class))) 
			,@ApiResponse(responseCode = "404", description = "User Not Found" ,content = @Content(schema = @Schema(implementation = ErrorStructure.class)))})
	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<Responstructure<BlogResponse>> createBlogs(@Valid @RequestBody BlogRequest blog,@PathVariable int userId)
	{
		return service.createBlog(blog,userId);
	}
}
