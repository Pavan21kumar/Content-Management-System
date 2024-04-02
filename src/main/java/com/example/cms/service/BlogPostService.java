package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.util.Responstructure;

public interface BlogPostService {

	ResponseEntity<Responstructure<BlogPostResponse>> createDraft(BlogPostRequest postRequest, int blogId);

	ResponseEntity<Responstructure<BlogPostResponse>> updateDraft(int postId);

	ResponseEntity<Responstructure<BlogPostResponse>> updatePost( BlogPostRequest postRequest, int postId);

	ResponseEntity<Responstructure<String>> deletePost(int postId);

}
