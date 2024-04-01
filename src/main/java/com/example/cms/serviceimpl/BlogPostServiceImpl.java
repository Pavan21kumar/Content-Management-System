package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.BlogResponse;
import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogsRepository;
import com.example.cms.service.BlogPostService;
import com.example.cms.util.BlogNotFoundException;
import com.example.cms.util.Responstructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

	private BlogsRepository blogRepo;
	private BlogPostRepository postRepo;
	private Responstructure<BlogPostResponse> response;

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> createDraft(BlogPostRequest postRequest, int blogId) {

		return blogRepo.findById(blogId).map(blog -> {
			BlogPost blogPost = maptoBlogPost(postRequest);
			blogPost = postRepo.save(blogPost);
			return ResponseEntity.ok(response.setStatusCode(HttpStatus.OK.value()).setMessage("post is created")
					.setData(mapToResponse(blogPost)));
		}).orElseThrow(() -> new BlogNotFoundException("blog not found by Given Id"));
	}

	public BlogPostResponse mapToResponse(BlogPost post) {

		return BlogPostResponse.builder().title(post.getTitle()).postId(post.getPostId()).summary(post.getSummary())
				.type(post.getType()).seo(post.getSeo()).seoDescription(post.getSeoDescription())
				.seoTags(post.getSeoTags()).createAt(post.getCreateAt()).createBy(post.getCreateBy())
				.lastModifiedAt(post.getLastModifiedAt()).LastModifiedBy(post.getLastModifiedBy())
				.subTitle(post.getSubTitle()).build();
	}

	private BlogPost maptoBlogPost(BlogPostRequest postRequest) {

		return BlogPost.builder().title(postRequest.getTitle()).subTitle(postRequest.getSubTitle())
				.summary(postRequest.getSummary()).type(PostType.DRAFT).seo(postRequest.getSeo())
				.seoDescription(postRequest.getSeoDescription()).seoTags(postRequest.getSeoTags()).build();

	}

}
