package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PostResponse;
import com.example.cms.dto.PublishedRequest;
import com.example.cms.util.Responstructure;

public interface PublisherService {

	ResponseEntity<Responstructure<PostResponse>> publishPost(PublishedRequest publishedRequest, int postId);

	ResponseEntity<Responstructure<BlogPostResponse>> unpublishedBlogPost(int postId);

}
