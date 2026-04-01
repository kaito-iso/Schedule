package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_user")
@Data
public class User {

	/** ユーザーID **/
	@Id
	@Column(name = "user_id")
	private String userId;

	/** パスワード　**/
	@Column(name = "password")
	private String password;

	/** 権限 **/
	@Column(name = "role")
	private String role;

	/** アカウント有効 **/
	@Column(name = "is_account_enabled")
	private boolean isAccountEnabled;
	
	/** 最終ログイン日時 **/
	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;
	
}
