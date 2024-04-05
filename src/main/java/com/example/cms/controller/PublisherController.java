package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PublishResponse;
import com.example.cms.dto.PublishedRequest;
import com.example.cms.service.PublisherService;
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
public class PublisherController {

	private PublisherService service;

	@Operation(description = "this endpoit  is used to create a publsh Entity and publish The Post based On postId", responses = {
			@ApiResponse(responseCode = "200", description = "Post is published "),
			@ApiResponse(responseCode = "400", description = "invalid inputs", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "post Not Found", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })

	@PostMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<Responstructure<PublishResponse>> publishingPost(
			@Valid @RequestBody PublishedRequest publishedRequest, @PathVariable int postId) {
		return service.publishPost(publishedRequest, postId);
	}

	@Operation(description = "this endpoit  is used to unpublished a Post Based On postId", responses = {
			@ApiResponse(responseCode = "200", description = "Post is unpublished "),
			@ApiResponse(responseCode = "404", description = "post is not found inputs", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "Unautherized Operation", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@PutMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<Responstructure<BlogPostResponse>> unpublishBlogPost(@PathVariable int postId) {
		return service.unpublishedBlogPost(postId);
	}
}
