package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ScheduleCategory;

public interface ScheduleCategoryRepository extends JpaRepository<ScheduleCategory, String> {

	List <ScheduleCategory> findByActive(boolean active);

}
