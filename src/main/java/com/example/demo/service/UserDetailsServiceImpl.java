package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.auth.CustomUserDetails;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// 指定されたIDでDBを検索
		return userRepository.findById(userId)
				// ユーザーが見つかった場合にCustomUserDetailsにセット
				.map(user -> new CustomUserDetails(user))
				// ユーザーが見つからなかった場合にSecurity専用の例外を投げる
				.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + userId));
	}
}