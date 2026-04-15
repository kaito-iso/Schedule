package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	// 開始日時と終了日時の間にあるデータを取得する
	List<Schedule> findByStartDateBetween(LocalDateTime start, LocalDateTime end);

	// 開始日時と終了日時の間にあるデータを取得して、終日順・予定が早い順でソート
	List<Schedule> findByStartDateBetweenOrderByIsAllDayDescStartDateAsc(LocalDateTime start, LocalDateTime end);
}
