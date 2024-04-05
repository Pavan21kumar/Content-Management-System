package com.example.cms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Publish")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publishId;
	private String seoTitle;
	private String seoDescription;
	private String[] seoTags;

	@OneToOne
	private BlogPost post;
	@OneToOne
	private Schedule schedule;

}
