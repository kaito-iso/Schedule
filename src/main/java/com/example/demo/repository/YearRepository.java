package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Year;

public interface YearRepository extends JpaRepository<Year, Integer> {

	List<Year> findByActive(boolean active);

}
