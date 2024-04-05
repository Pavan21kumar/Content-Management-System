package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Schedule;

public interface SchedulerRepository extends JpaRepository<Schedule, Integer> {

}
