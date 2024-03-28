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

		return BlogResponse.builder().title(blog.getTitle()).topic(blog.getTopic()).about(blog.getAbout()).blogId(blog.getBlogId()).build();

	}

}
