package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
			@ApiResponse(responseCode = "400", description = "invalid inputs", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "User Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })

	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<Responstructure<BlogResponse>> createBlogs(@Valid @RequestBody BlogRequest blog,
			@PathVariable int userId) {
		return service.createBlog(blog, userId);
	}

	@Operation(description = "this endpoit  is used to check Title is present or not ", responses = {
			@ApiResponse(responseCode = "200", description = "Title is present is Created "),
			@ApiResponse(responseCode = "404", description = "Title Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@GetMapping("/titles/{title}/blogs")
	public ResponseEntity<Responstructure<Boolean>> checkBlogTitleAvailable(@PathVariable String title) {
		return service.checkBlogTitleAvailable(title);
	}

	@Operation(description = "this endpoit  is used to check Blog Present or not Based On BlogId ", responses = {
			@ApiResponse(responseCode = "200", description = "Blog is Found "),
			@ApiResponse(responseCode = "404", description = "Blog Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<Responstructure<BlogResponse>> findBlogByBlogId(@PathVariable int blogId) {
		return service.findBlogById(blogId);
	}

	@Operation(description = "this endpoit  is used to Update the Blog Based On BlogId", responses = {
			@ApiResponse(responseCode = "200", description = "Blog is Updated "),
			@ApiResponse(responseCode = "400", description = "invalid inputs", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Blog Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<Responstructure<BlogResponse>> updateBlogData(@RequestBody BlogRequest blogRequest,
			@PathVariable int blogId) {
		return service.updateBlogdata(blogRequest, blogId);
	}

}
