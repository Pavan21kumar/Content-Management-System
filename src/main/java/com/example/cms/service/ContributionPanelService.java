package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.util.Responstructure;

public interface ContributionPanelService {

	ResponseEntity<Responstructure<ContributionPanelResponse>> addUserToContribution(int userId, int panelId);

	ResponseEntity<Responstructure<ContributionPanelResponse>> removeUserFromContribution(int userId, int panelId);

}
