package com.example.cms.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PublishResponse;
import com.example.cms.dto.PublishedRequest;
import com.example.cms.dto.ScheduleRequest;
import com.example.cms.entity.BlogPost;
import com.example.cms.entity.Publish;
import com.example.cms.entity.Schedule;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.PublishRepository;
import com.example.cms.repository.SchedulerRepository;
import com.example.cms.service.PublisherService;
import com.example.cms.util.IllegalAccessRequestException;
import com.example.cms.util.InValidDateTimeException;
import com.example.cms.util.PostNotFoundByIdException;
import com.example.cms.util.Responstructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublishServiceimpl implements PublisherService {

	private PublishRepository publishRepo;
	private BlogPostRepository postRepo;
	private Responstructure<PublishResponse> postStructure;
	private Responstructure<BlogPostResponse> structure;
	private SchedulerRepository scheduleRepo;

	@Override
	public ResponseEntity<Responstructure<PublishResponse>> publishPost(PublishedRequest publishedRequest, int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return postRepo.findById(postId).map(post -> {

			Publish publish = null;
			if (post.getBlog().getUser().getEmail().equals(email) || post.getCreateBy().equals(email)) {
				if (post.getPublish() != null) {
					publish = mapToPublishEntity(publishedRequest, post.getPublish());

				} else {
					publish = mappToPublish(publishedRequest);
				}
				if (publishedRequest.getSchedule() != null) {
					Schedule schedule = publish.getSchedule();

					if (publishedRequest.getSchedule().getDateTime().isAfter(LocalDateTime.now())) {
						if (schedule == null) {

							publish.setPost(post);
							publish.setSchedule(scheduleRepo.save(mapToSchedule(publishedRequest.getSchedule())));
							publish = publishRepo.save(publish);
							post.setType(PostType.SCHEDULED);
							post = postRepo.save(post);
							return ResponseEntity.ok(postStructure.setStatusCode(HttpStatus.OK.value())
									.setMessage("post is published ").setData(mappToResponse(publish)));
						} else {

							publish.setPost(post);
							publish.setSchedule(scheduleRepo
									.save(mappToSchedule(publish.getSchedule(), publishedRequest.getSchedule())));
							publish = publishRepo.save(publish);

							post.setType(PostType.SCHEDULED);
							post = postRepo.save(post);

							return ResponseEntity.ok(postStructure.setStatusCode(HttpStatus.OK.value())
									.setMessage("post is published ").setData(mappToResponse(publish)));

						}
					}
					throw new InValidDateTimeException("please give current time or Future");

				} else {
					publish.setPost(post);
					post.setType(PostType.PUBLISHED);
					publishRepo.save(publish);
					post = postRepo.save(post);
					return ResponseEntity.ok(postStructure.setStatusCode(HttpStatus.OK.value())
							.setMessage("post is published ").setData(mappToResponse(publish)));
				}

			}
			throw new IllegalAccessRequestException("Owner Only Can Delete The post..");

		}).orElseThrow(() -> new PostNotFoundByIdException("post not found by Given Id"));

	}

	private Schedule mappToSchedule(Schedule schedule, ScheduleRequest schedule2) {
		schedule.setDateTime(schedule2.getDateTime());
		return schedule;
	}

	private Publish mapToPublishEntity(PublishedRequest publishedRequest, Publish publish) {
		publish.setPublishId(publish.getPublishId());
		publish.setSeoTitle(publishedRequest.getSeoTitle());
		publish.setSeoDescription(publishedRequest.getSeoDescription());
		publish.setSeoTags(publishedRequest.getSeoTags());

		return publish;

	}

	public PublishResponse mappToResponse(Publish publish) {
		if (publish.getSchedule() != null)
			return PublishResponse.builder().seoTitle(publish.getSeoTitle()).seoDescription(publish.getSeoDescription())
					.seoTags(publish.getSeoTags()).schedule(publish.getSchedule()).publishId(publish.getPublishId())
					.build();
		return PublishResponse.builder().seoTitle(publish.getSeoTitle()).seoDescription(publish.getSeoDescription())
				.seoTags(publish.getSeoTags()).schedule(null).build();

	}

	private Publish mappToPublish(PublishedRequest publishedRequest) {
		if (publishedRequest.getSchedule() != null)
			return Publish.builder().seoTitle(publishedRequest.getSeoTitle())
					.seoDescription(publishedRequest.getSeoDescription()).seoTags(publishedRequest.getSeoTags())
					.schedule(mapToSchedule(publishedRequest.getSchedule())).build();
		return Publish.builder().seoTitle(publishedRequest.getSeoTitle())
				.seoDescription(publishedRequest.getSeoDescription()).seoTags(publishedRequest.getSeoTags())
				.schedule(null).build();

	}

	private Schedule mapToSchedule(ScheduleRequest schedule) {

		return Schedule.builder().dateTime(schedule.getDateTime()).build();
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
				.build();
	}
	
	

}
