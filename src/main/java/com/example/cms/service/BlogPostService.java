package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.BlogResponse;
import com.example.cms.util.Responstructure;

public interface BlogPostService {

	ResponseEntity<Responstructure<BlogPostResponse>> createDraft(BlogPostRequest postRequest, int blogId);

	ResponseEntity<Responstructure<BlogPostResponse>> updateDraft(int postId);

}
