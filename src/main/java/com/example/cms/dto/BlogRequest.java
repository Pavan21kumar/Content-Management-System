package com.example.cms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class BlogRequest {

	@NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Title should contain only alphabetical characters")
	private String title;
	private String[] topic;
	private String about;
	
}
