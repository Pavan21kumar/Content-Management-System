package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PostResponse;
import com.example.cms.dto.PublishedRequest;
import com.example.cms.entity.BlogPost;
import com.example.cms.entity.Publish;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.PublishRepository;
import com.example.cms.service.PublisherService;
import com.example.cms.util.IllegalAccessRequestException;
import com.example.cms.util.PostNotFoundByIdException;
import com.example.cms.util.Responstructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublishServiceimpl implements PublisherService {

	private PublishRepository publishRepo;
	private BlogPostRepository postRepo;
	private Responstructure<PostResponse> postStructure;
	private Responstructure<BlogPostResponse> structure;

	@Override
	public ResponseEntity<Responstructure<PostResponse>> publishPost(PublishedRequest publishedRequest, int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return postRepo.findById(postId).map(post -> {

			if (post.getBlog().getUser().getEmail().equals(email) || post.getCreateBy().equals(email)) {
				Publish publish = mappToPublish(publishedRequest);
				publish.setPost(post);
				publish = publishRepo.save(publish);

				post.setType(PostType.PUBLISHED);
				post = postRepo.save(post);
				return ResponseEntity.ok(postStructure.setStatusCode(HttpStatus.OK.value())
						.setMessage("post is published ").setData(mappToResponse(publish, post)));

			}
			throw new IllegalAccessRequestException("Owner Only Canm Delete The post..");

		}).orElseThrow(() -> new PostNotFoundByIdException("post not found by Given Id"));

	}

	private PostResponse mappToResponse(Publish publish, BlogPost post) {
		return PostResponse.builder().title(post.getTitle()).postId(post.getPostId()).subTitle(post.getSubTitle())
				.summary(post.getSummary()).type(post.getType()).createBy(post.getCreateBy())
				.seoTitle(publish.getSeoTitle()).seoDescription(publish.getSeoDescription())
				.seoTags(publish.getSeoTags()).build();

	}

	private Publish mappToPublish(PublishedRequest publishedRequest) {

		return Publish.builder().seoTitle(publishedRequest.getSeoTitle())
				.seoDescription(publishedRequest.getSeoDescription()).seoTags(publishedRequest.getSeoTags()).build();

	}

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> unpublishedBlogPost(int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return postRepo.findById(postId).map(post -> {
			if (post.getBlog().getUser().getEmail().equals(email) || post.getCreateBy().equals(email)) {
				post.setType(PostType.DRAFT);
				post = postRepo.save(post);
				return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("post is published ")
						.setData(mappToBlogResponse(post)));
			}
			throw new IllegalAccessRequestException("Owner Only Canm Delete The post..");

		}).orElseThrow(() -> new PostNotFoundByIdException("post not found by Given Id"));

	}

	private BlogPostResponse mappToBlogResponse(BlogPost post) {
		return BlogPostResponse.builder().title(post.getTitle()).subTitle(post.getSubTitle()).summary(post.getSummary())
				.type(post.getType()).createBy(post.getCreateBy()).build();
	}

}
