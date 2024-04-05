package com.example.cms.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

//	@Bean
//	AuditorAware<String> auditor() {
//		// Authentication authentication =
//		// SecurityContextHolder.getContext().getAuthentication();
//
//		return () -> Optional.of(
//
//				SecurityContextHolder.getContext().getAuthentication().getName());
//
////		 
//	}

}
