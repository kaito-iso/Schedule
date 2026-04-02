package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, LocalDate> {

	List<Holiday> findByOrderByHolidayDateAsc();
}
