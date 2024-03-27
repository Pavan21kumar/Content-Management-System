package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.Users;
import com.example.cms.entity.Users.UsersBuilder;
import com.example.cms.repository.UsersRepository;
import com.example.cms.service.UsersService;
import com.example.cms.util.DuplicateEmailException;
import com.example.cms.util.Responstructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

	private UsersRepository repo;
	private Responstructure<UserResponse> structure;
	private PasswordEncoder encoder;

	@Override
	public ResponseEntity<Responstructure<UserResponse>> saveUsers(UserRequest userRequest) {

		if (repo.existsByEmail(userRequest.getEmail())) {
			throw new DuplicateEmailException("user register if Failed.....,Because User Email Is Allredy Present..");
		}
		Users user = mappedToUserRequestToUsers(new Users(), userRequest);
		user = repo.save(user);
		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
				.setMessage("user data successFully stored").setData(mapTouser(user)));

	}

	private UserResponse mapTouser(Users user) {

		return UserResponse.builder().userId(user.getUserId()).userName(user.getUserName()).email(user.getEmail())
				.createdAt(user.getCreatedAt()).lastModifiedAt(user.getLastModifiedAt()).build();

	}

	private Users mappedToUserRequestToUsers(Users user, UserRequest userRequest) {

		return Users.builder().userName(userRequest.getUserName()).email(userRequest.getEmail())
				.password(encoder.encode(userRequest.getPassword())).build();

		
	}

}
