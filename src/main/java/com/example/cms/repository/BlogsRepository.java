package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Blogs;

public interface BlogsRepository  extends JpaRepository<Blogs, Integer>{

	boolean existsByTitle(String title);

}
