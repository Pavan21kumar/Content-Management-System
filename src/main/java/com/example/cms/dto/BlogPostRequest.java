package com.example.cms.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
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
public class BlogPostRequest {

	@NotNull(message = "title should not be null")
	private String title;
	private String subTitle;
	@Length(min = 500, max = 3000, message = "summary should be atlest 500 charecters.....and lessthan 3000 charecters")
	private String summary;

}
