package com.example.demo.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HolidayForm {
	
	/** 祝日date **/
	private LocalDate holidayDate;

	/** 祝日name　**/
	private String holidayName;
}
