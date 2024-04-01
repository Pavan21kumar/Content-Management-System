package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.service.BlogPostService;
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
public class BlogPostController {

	private BlogPostService blogService;

	@Operation(description = "this endpoit  is used to create a Post Based On Blog", responses = {
			@ApiResponse(responseCode = "200", description = "Post is Created "),
			@ApiResponse(responseCode = "400", description = "invalid inputs", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Blog Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })

	@PostMapping("/blogs/{blogId}/blog-posts")
	private ResponseEntity<Responstructure<BlogPostResponse>> createDraft(
			@Valid @RequestBody BlogPostRequest postRequest, @PathVariable int blogId) {
		return blogService.createDraft(postRequest, blogId);
	}

	@Operation(description = "this endpoit  is used to update a Post Based On postId", responses = {
			@ApiResponse(responseCode = "200", description = "Post is Updated "),
			@ApiResponse(responseCode = "400", description = "invalid inputs", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Post Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@PutMapping("/blog-posts/{postId}")
	public ResponseEntity<Responstructure<BlogPostResponse>> updateDraft(@PathVariable int postId) {
		return blogService.updateDraft(postId);
	}

	@Operation(description = "this endpoit  is used to update a Post Based On postId", responses = {
			@ApiResponse(responseCode = "200", description = "Post is Updated "),
			@ApiResponse(responseCode = "400", description = "invalid inputs", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Post Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@PutMapping("/blog-posts/{postId}/post")
	public ResponseEntity<Responstructure<BlogPostResponse>> updatePost(@Valid @RequestBody BlogPostRequest postRequest,
			@PathVariable int postId) {
		return blogService.updatePost(postRequest, postId);
	}
}
