package com.example.cms.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cms.repository.UsersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomeUserDetailservice implements UserDetailsService {

		private UsersRepository repo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return repo.findByEmail(userName).map(user -> new CustomUserDetails(user)

		).orElseThrow(() -> new UsernameNotFoundException("user not found"));

	}

}
