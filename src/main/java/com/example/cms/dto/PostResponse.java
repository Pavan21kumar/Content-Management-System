package com.example.cms.dto;

import com.example.cms.enums.PostType;

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
public class PostResponse {

	private int postId;
	private String title;
	private String subTitle;
	private String summary;
	private PostType type;
	private String createBy;
	private String seoTitle;
	private String seoDescription;
	private String[] seoTags;
}
