package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.PostResponse;
import com.example.cms.dto.PublishedRequest;
import com.example.cms.service.PublisherService;
import com.example.cms.util.Responstructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PublisherController {

	private PublisherService service;

	@PostMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<Responstructure<PostResponse>> publishingPost(
			@Valid @RequestBody PublishedRequest publishedRequest, @PathVariable int postId) {
		return service.publishPost(publishedRequest, postId);
	}
}
