package com.example.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogPostResponse {

	private int postId;
	private String title;
	private String subTitle;
	private String summary;
	private PublishResponse publishResponse;
}
