package com.example.cms.dto;

import java.time.LocalDateTime;

import com.example.cms.enums.PostType;

import jakarta.persistence.Column;
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
	private PostType type;
	private String seo;
	private String seoDescription;
	private String[] seoTags;
	private LocalDateTime createAt;
	private String createBy;
	private LocalDateTime lastModifiedAt;
	private String LastModifiedBy;
}
