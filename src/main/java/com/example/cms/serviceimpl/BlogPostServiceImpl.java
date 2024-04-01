package com.example.cms.serviceimpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.BlogResponse;
import com.example.cms.entity.BlogPost;
import com.example.cms.entity.Users;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogsRepository;
import com.example.cms.service.BlogPostService;
import com.example.cms.util.BlogNotFoundException;
import com.example.cms.util.IllegalAccessRequestException;
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
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return blogRepo.findById(blogId).map(blog -> {
			List<Users> users = blog.getPanel().getUsers();
			for (Users user : users) {
				if (user.getEmail().equals(email) != false)
					throw new IllegalAccessRequestException("Login User Not A Contributer...");
			}
			BlogPost blogPost = maptoBlogPost(postRequest);
			blogPost = postRepo.save(blogPost);
			return ResponseEntity.ok(response.setStatusCode(HttpStatus.OK.value()).setMessage("post is created")
					.setData(mapToResponse(blogPost)));

		}).orElseThrow(() -> new BlogNotFoundException("blog not found by Given Id"));
	}

	public BlogPostResponse mapToResponse(BlogPost post) {

		return BlogPostResponse.builder().title(post.getTitle()).postId(post.getPostId()).summary(post.getSummary())
				.createAt(post.getCreateAt()).createBy(post.getCreateBy()).lastModifiedAt(post.getLastModifiedAt())
				.LastModifiedBy(post.getLastModifiedBy()).subTitle(post.getSubTitle()).type(post.getType()).build();
	}

	private BlogPost maptoBlogPost(BlogPostRequest postRequest) {

		return BlogPost.builder().title(postRequest.getTitle()).subTitle(postRequest.getSubTitle())
				.summary(postRequest.getSummary()).type(PostType.DRAFT).build();

	}

}
