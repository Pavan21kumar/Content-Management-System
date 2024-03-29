package com.example.cms.serviceimpl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.entity.Blogs;
import com.example.cms.repository.BlogsRepository;
import com.example.cms.repository.UsersRepository;
import com.example.cms.service.BlogsService;
import com.example.cms.util.BlogNotFoundException;
import com.example.cms.util.Responstructure;
import com.example.cms.util.TitleAllreadyPresentException;
import com.example.cms.util.TopicIsNullException;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogsService {

	private BlogsRepository repo;
	private Responstructure<BlogResponse> structure;
	private UsersRepository userRepo;
	private Responstructure<Boolean> booleanStructure;

	@Override
	public ResponseEntity<Responstructure<BlogResponse>> createBlog(BlogRequest blogRequest, int userId) {

		return userRepo.findById(userId).map(user -> {
			if (repo.existsByTitle(blogRequest.getTitle()))
				throw new TitleAllreadyPresentException("Title is allready present");
			if (blogRequest.getTopic().length < 1)
				throw new TopicIsNullException("topic should be at least one");
			Blogs blog = mapToBlogs(blogRequest);
			blog.setUsers(Arrays.asList(user));

			blog = repo.save(blog);
			userRepo.save(user);
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("blog is created...")
					.setData(mapToBlogResponse(blog)));

		}).orElseThrow(() -> new UserNotFoundByIdException("user Not Found By Given UserId"));

	}

	private Blogs mapToBlogs(BlogRequest blogRequest) {

		return Blogs.builder().title(blogRequest.getTitle()).topic(blogRequest.getTopic()).about(blogRequest.getAbout())
				.build();

	}

	private BlogResponse mapToBlogResponse(Blogs blog) {

		return BlogResponse.builder().title(blog.getTitle()).topic(blog.getTopic()).about(blog.getAbout())
				.blogId(blog.getBlogId()).build();

	}

	@Override
	public ResponseEntity<Responstructure<Boolean>> checkBlogTitleAvailable(String title) {
		if (repo.existsByTitle(title)) {
			return ResponseEntity.ok(
					booleanStructure.setStatusCode(HttpStatus.OK.value()).setMessage("title is found").setData(true));
		}
		return ResponseEntity
				.ok(booleanStructure.setStatusCode(HttpStatus.OK.value()).setMessage("title is found").setData(false));

	}

	@Override
	public ResponseEntity<Responstructure<BlogResponse>> findBlogById(int blogId) {

		return repo.findById(blogId).map(blog -> {
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("blog Found")
					.setData(mapToBlogResponse(blog)));

		}).orElseThrow(() -> new BlogNotFoundException("Blog Not Found By GivenId"));

	}

	@Override
	public ResponseEntity<Responstructure<BlogResponse>> updateBlogdata(BlogRequest blogRequest, int blogId) {
		return repo.findById(blogId).map(blog -> {

		Blogs	blogs = mapToBlogs(blogRequest);
		blogs.setBlogId(blog.getBlogId());
		blogs=repo.save(blogs);
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value()).setMessage("blog Found")
					.setData(mapToBlogResponse(blogs)));

		}).orElseThrow(() -> new BlogNotFoundException("Blog Not Found By GivenId"));

	}

}
