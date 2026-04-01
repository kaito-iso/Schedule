package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PasswordChangeForm {

	/** 現在のパスワード **/
	@NotBlank(message = "パスワードを入力してください")
	private String currentPassword;

	/** パスワード **/
	@Size(min = 6, max = 32, message = "6～32文字で入力してください")
	private String password1;

	/** パスワード(確認用） **/
	@Size(min = 6, max = 32, message = "6～32文字で入力してください")
	private String password2;

}
