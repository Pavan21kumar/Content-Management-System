package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PublishResponse;
import com.example.cms.entity.BlogPost;
import com.example.cms.entity.Blogs;
import com.example.cms.entity.Publish;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogsRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UsersRepository;
import com.example.cms.service.BlogPostService;
import com.example.cms.util.BlogNotFoundException;
import com.example.cms.util.IllegalAccessRequestException;
import com.example.cms.util.PostNotFoundByIdException;
import com.example.cms.util.Responstructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

	private BlogsRepository blogRepo;
	private BlogPostRepository postRepo;
	private Responstructure<BlogPostResponse> response;
	private UsersRepository userRepo;
	private ContributionPanelRepository panelRepo;
	private Responstructure<String> structure;

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> createDraft(BlogPostRequest postRequest, int blogId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return blogRepo.findById(blogId).map(blog -> {
			if (validation(email, blog)) {
				BlogPost blogPost = maptoBlogPost(postRequest, email);
				blogPost.setBlog(blog);
				blogPost = postRepo.save(blogPost);
				return ResponseEntity.ok(response.setStatusCode(HttpStatus.OK.value()).setMessage("post is created")
						.setData(mapToResponse(blogPost)));

			}
			throw new IllegalAccessRequestException("Login User Not A Contributer...");

		}).orElseThrow(() -> new BlogNotFoundException("blog not found by Given Id"));
	}

	public BlogPostResponse mapToResponse(BlogPost post) {

		return BlogPostResponse.builder().title(post.getTitle()).postId(post.getPostId()).summary(post.getSummary())
				.subTitle(post.getSubTitle()).publishResponse(mapToPublishRespons(post.getPublish())).build();
	}

	private PublishResponse mapToPublishRespons(Publish publish) {
		if (publish == null) {
			return null;

		}
		return PublishResponse.builder().publishId(publish.getPublishId()).seoTitle(publish.getSeoTitle())
				.seoDescription(publish.getSeoDescription()).seoTags(publish.getSeoTags()).build();
	}

	private BlogPost maptoBlogPost(BlogPostRequest postRequest, String email) {

		return BlogPost.builder().title(postRequest.getTitle()).subTitle(postRequest.getSubTitle())
				.summary(postRequest.getSummary()).type(PostType.DRAFT).createBy(email).LastModifiedBy(email).build();

	}

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> updateDraft(int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return postRepo.findById(postId).map(post -> {
			if (validation(email, post.getBlog())) {
				post.setType(PostType.PUBLISHED);
				post.setBlog(post.getBlog());
				post = postRepo.save(post);
				return ResponseEntity.ok(response.setStatusCode(HttpStatus.OK.value())
						.setMessage("post is updated Draft To Published").setData(mapToResponse(post)));
			}
			throw new IllegalAccessRequestException("Login User Not A Contributer or Owner...");

		}).orElseThrow(() -> new BlogNotFoundException("BlogPost not found by Given Id"));

	}

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> updatePost(BlogPostRequest postRequest, int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return postRepo.findById(postId).map(post -> {
			if (validation(email, post.getBlog())) {
				post.setTitle(postRequest.getTitle());
				post.setSubTitle(postRequest.getSubTitle());
				post.setSummary(postRequest.getSummary());
				post.setLastModifiedBy(email);
				post.setBlog(post.getBlog());
				post = postRepo.save(post);
				return ResponseEntity.ok(response.setStatusCode(HttpStatus.OK.value()).setMessage("post is updated..")
						.setData(mapToResponse(post)));
			}
			throw new IllegalAccessRequestException("Login User Not A Contributer or Owner...");

		}).orElseThrow(() -> new BlogNotFoundException("blogPost not found by Given id"));

	}

	private boolean validation(String email, Blogs blog) {
		return userRepo.findByEmail(email).map(user -> {
			if (blog.getUser().getEmail().equals(email)
					|| panelRepo.existsByPanelIdAndUsers(blog.getPanel().getPanelId(), user))
				return true;
			return false;
		}).get();

	}

	@Override
	public ResponseEntity<Responstructure<String>> deletePost(int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return postRepo.findById(postId).map(post -> {
			if (post.getBlog().getUser().getEmail().equals(email) || post.getCreateBy().equals(email)) {
				postRepo.delete(post);
				return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
						.setMessage("Post Is deletd..successFully").setData("Post Is Deleted "));
			}

			throw new IllegalAccessRequestException("Owner Only Canm Delete The post..");

		}).orElseThrow(() -> new PostNotFoundByIdException("post Not Found..."));

	}

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> unpublishedBlogPost(int postId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return postRepo.findById(postId).map(post -> {
			if (post.getBlog().getUser().getEmail().equals(email) || post.getCreateBy().equals(email)) {

				post.setType(PostType.DRAFT);
				postRepo.save(post);
				return ResponseEntity.ok(response.setStatusCode(HttpStatus.OK.value())
						.setMessage("successpully unpublished the blogPost").setData(mapToResponse(post)));
			}

			throw new IllegalAccessRequestException("Owner Only Canm Delete The post..");

		}).orElseThrow(() -> new PostNotFoundByIdException("post Not Found..."));

	}

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> fetchBlogPostById(int postId) {
		return postRepo.findById(postId).map(post -> {
			return ResponseEntity.ok(response.setStatusCode(HttpStatus.OK.value()).setMessage("post is Found")
					.setData(mapToResponse(post)));

		}).orElseThrow(() -> new PostNotFoundByIdException("post Not Found..."));

	}

	@Override
	public ResponseEntity<Responstructure<BlogPostResponse>> findByIdAndPostType(int postId) {
		return postRepo.findByPostIdAndType(postId, PostType.PUBLISHED).map(post -> {

			return ResponseEntity.status(HttpStatus.FOUND).body(response.setStatusCode(HttpStatus.FOUND.value())
					.setMessage("post found").setData(mapToResponse(post)));
		}).orElseThrow(() -> new PostNotFoundByIdException("blog post not found by given Id"));

	}

}
