package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.Users;
import com.example.cms.util.Responstructure;

import jakarta.validation.Valid;

public interface UsersService {

	ResponseEntity<Responstructure<UserResponse>> saveUsers( UserRequest userRequest);

}
