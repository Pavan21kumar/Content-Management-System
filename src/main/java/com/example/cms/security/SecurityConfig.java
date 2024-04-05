package com.example.cms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	private CustomeUserDetailservice userDetailservice;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);// more secured and more use and 12 times hashing is basic secure
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailservice);
		return provider;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/users/register", "/users/{userId}", "/blog-posts/{postId}/postType")
						.permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).build();
	}
}
