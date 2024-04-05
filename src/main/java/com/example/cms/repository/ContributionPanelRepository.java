package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.Users;

public interface ContributionPanelRepository extends JpaRepository<ContributionPanel, Integer> {

	

	boolean existsByPanelIdAndUsers(int panelId, Users user);

}
