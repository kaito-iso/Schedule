package com.example.demo.service;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidCurrentPasswordException;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordChangeService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * 指定されたユーザーのパスワードを変更します
	 * @param userId パスワード変更対象のユーザーID
	 * @param currentPassword 現在使用しているパスワード
	 * @param newPassword 新しく設定するパスワード
	 * @throws RuntimeException ユーザーが存在しない場合
	 * @throws InvalidCurrentPasswordException 現在のパスワードが一致しない場合
	 */
	@Transactional
	public void passwordChange(String userId, String currentPassword, String newPassword) {

		// ユーザー情報の取得
		var user = repository.findById(userId)
				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

		// 現在のパスワードが正しいか
		if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
			// パスワードが違う場合は、専用の例外を投げる
			throw new InvalidCurrentPasswordException();
		}

		//　パスワードをハッシュ化
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);

		// 保存
		repository.save(user);
	}
}
