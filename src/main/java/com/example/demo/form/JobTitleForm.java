package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class JobTitleForm {

	/** 役職コード **/
	@NotBlank(message = "役職コードを入力してください")
	@Size(max = 3, message = "最大3文字までしか登録できません")
	@Pattern(regexp = "^[0-9a-zA-Z]+$", message = "英数字で入力してください")
	private String jobTitleCode;

	/** 役職名 **/
	@NotBlank(message = "役職名を入力してください")
	@Size(max = 25, message = "最大25文字までしか登録できません")
	private String jobTitleName;

	/** 表示順序　**/
	@NotBlank(message = "表示順序を入力してください")
	@Pattern(regexp = "^[0-9]+$", message = "数字のみ入力してください")
	private String displayOrder;

	/** 削除区分 **/
	private Boolean active;
}
