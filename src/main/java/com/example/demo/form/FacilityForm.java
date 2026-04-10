package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class FacilityForm {

	/** 施設コード **/
	@NotBlank(message = "施設コードを入力してください")
	@Size(max = 5, message = "最大5文字までしか登録できません")
	@Pattern(regexp = "^[0-9a-zA-Z]+$", message = "英数字で入力してください")
	private String facilityCode;

	/** 施設名 **/
	@NotBlank(message = "施設名を入力してください")
	@Size(max = 25, message = "最大25文字までしか登録できません")
	private String facilityName;

	/** 表示順序　**/
	@NotBlank(message = "表示順序を入力してください")
	@Pattern(regexp = "^[0-9]+$", message = "数字のみ入力してください")
	private String displayOrder;

	/** 削除区分 **/
	private boolean active;
}
