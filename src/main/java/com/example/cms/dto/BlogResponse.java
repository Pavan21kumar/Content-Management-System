package com.example.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BlogResponse {

	private int blogId;
	private String title;
	private String[] topic;
	private String about;
}
