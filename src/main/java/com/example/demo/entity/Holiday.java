package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_holiday")
@Data
public class Holiday {

	/** 祝日date **/
	@Id
	@Column(name = "holiday_date")
	private LocalDate holidayDate;

	/** 祝日name　**/
	@Column(name = "holiday_name", length = 30, nullable = false)
	private String holidayName;

	/** 登録タイプ **/
	@Column(name = "create_type", length = 5, nullable = false)
	private String createType;
}
