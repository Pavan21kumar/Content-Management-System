package com.example.cms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublishedRequest {

	@NotNull(message = "title should not be null")
	private String seoTitle;
	private String seoDescription;
	private String[] seoTags;
	private ScheduleRequest schedule;

}
