package com.example.cms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Blogs")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogId;
	private String title;
	private String[] topic;
	private String about;
	@ManyToOne
	@JsonIgnore
	private Users user;
	@OneToOne
	private ContributionPanel panel;
	@OneToMany(mappedBy = "blog")
	private List<BlogPost> blogPost;
}
