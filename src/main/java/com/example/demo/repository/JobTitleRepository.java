package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.JobTitle;

public interface JobTitleRepository extends JpaRepository<JobTitle, String> {
	List<JobTitle> findAllByOrderByDisplayOrderAsc();
}
