package com.example.cms.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ScheduledJob {
	private BlogPostRepository postRepo;

	@Scheduled(fixedDelay = 60000l)
	public void publishScheduledBlogPost() {
		List<BlogPost> posts = postRepo
				.findAllByPublishScheduleDateTimeLessThanEqualAndType(LocalDateTime.now(), PostType.SCHEDULED).stream()
				.map(post -> {
					post.setType(PostType.PUBLISHED);
					return post;
				}).collect(Collectors.toList());
		postRepo.saveAll(posts);
	}

}
