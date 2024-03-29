package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Blogs;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.Users;

public interface BlogsRepository  extends JpaRepository<Blogs, Integer>{

	boolean existsByTitle(String title);

	boolean existsByUserAndPanel(Users users , ContributionPanel panel);


}
