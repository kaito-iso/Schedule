package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class HolidayForm {

	/** 祝日date **/
	@NotNull(message = "祝日日付を入力してください")
	private LocalDate holidayDate;

	/** 祝日name　**/
	@NotBlank(message = "祝日名を入力してください")
	@Size(max = 15, message = "最大15文字までしか登録できません")
	private String holidayName;
}
