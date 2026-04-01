package com.example.demo.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

	private final User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 権限を取得してSpringSecurityの権限オブジェクトに変換し、リストで返す
		return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
	}

	/**
	 * ユーザー名を返す
	 */
	@Override
	public String getUsername() {
		return user.getUserId();
	}

	/*
	 * パスワードを返す
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * アカウントが有効化されているかを判定
	 */
	@Override
	public boolean isEnabled() {
		// falseならログインを拒否
		return user.isAccountEnabled();
	}
}