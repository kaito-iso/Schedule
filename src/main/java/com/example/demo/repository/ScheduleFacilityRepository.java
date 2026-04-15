package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ScheduleFacility;
import com.example.demo.entity.composite.ScheduleFacilityId;

public interface ScheduleFacilityRepository extends JpaRepository<ScheduleFacility, ScheduleFacilityId> {

}
