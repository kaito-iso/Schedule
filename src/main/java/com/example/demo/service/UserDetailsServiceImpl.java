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

	/**
	 * ユーザーIDをキーにデータベースを検索し、Spring Securityのユーザー詳細オブジェクト（UserDetails）を生成
	 * <p>
	 * 検索の結果、ユーザーが存在しない場合は {@link UsernameNotFoundException} をスローする
	 * </p>
	 * @param userId ログイン時に入力されたユーザーID
	 * @return Spring Securityのコンテキストで管理されるユーザー情報
	 * @throws UsernameNotFoundException 指定されたユーザーIDに該当するユーザーがDBに存在しない場合
	 */
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