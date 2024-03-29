package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.util.Responstructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ContributionPanelController {

	private ContributionPanelService service;

	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<Responstructure<ContributionPanelResponse>> addUsersToContributionPanel(
			@PathVariable int userId, @PathVariable int panelId) {
		
		return service.addUserToContribution(userId, panelId);
	}
}
