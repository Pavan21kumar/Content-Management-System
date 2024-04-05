package com.example.cms.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

	Optional<BlogPost> findByPostIdAndType(int postId, PostType published);

	//List<BlogPost> findAllByPublishAndDateTimeLessThanEqualAndType(LocalDateTime now, PostType scheduled);

	List<BlogPost> findAllByPublishScheduleDateTimeLessThanEqualAndType(LocalDateTime now, PostType scheduled);

}
