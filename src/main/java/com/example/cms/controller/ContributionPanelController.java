package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.util.ErrorStructure;
import com.example.cms.util.Responstructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ContributionPanelController {

	private ContributionPanelService service;

	@Operation(description = "this endpoit  is used to Update the Blog Based On BlogId", responses = {
			@ApiResponse(responseCode = "200", description = "contributer is created  "),
			@ApiResponse(responseCode = "404", description = "Unautherized Operation", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "user Not Found by GivenId", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "panel Not Found by GivenId", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })

	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<Responstructure<ContributionPanelResponse>> addUsersToContributionPanel(
			@PathVariable int userId, @PathVariable int panelId) {

		return service.addUserToContribution(userId, panelId);
	}

	@Operation(description = "this endpoit  is used to Update the Blog Based On BlogId", responses = {
			@ApiResponse(responseCode = "200", description = "contributer is removed  "),
			@ApiResponse(responseCode = "404", description = "Unautherized Operation", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "user Not Found by GivenId", content = @Content(schema = @Schema(implementation = ErrorStructure.class))),
			@ApiResponse(responseCode = "404", description = "panel Not Found by GivenId", content = @Content(schema = @Schema(implementation = ErrorStructure.class))) })
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<Responstructure<ContributionPanelResponse>> removeUsersFromContributionPanel(
			@PathVariable int userId, @PathVariable int panelId) {

		return service.removeUserFromContribution(userId, panelId);
	}
}
