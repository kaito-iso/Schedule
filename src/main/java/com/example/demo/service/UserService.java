package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	/**
	 * ユーザーIDをキーにユーザー情報を取得
	 * @param userId 検索対象のユーザーID
	 * @return 該当するユーザーが存在する場合は、エンティティを含むOptional、存在しない場合は空のOptional
	 */
	public Optional<User> findByUser(String userId) {
		return repository.findById(userId);
	}

	/**
	 * ユーザーIDをキーにユーザー情報を取得して名前を返す
	 * @param userId 検索対象のユーザーID
	 * @return 該当するユーザーのユーザー名
	 */
	public String userName(String userId) {

		User user = repository.findById(userId)
				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません: " + userId));

		return String.format("%s %s", user.getLastName(), user.getFirstName());
	}

	/**
	 * 指定されたユーザーの最終ログイン日時を現在時刻に更新
	 * @param userId 更新対象のユーザーID
	 */
	@Transactional
	public void updateLastLoginDate(String userId) {
		repository.findById(userId).ifPresent(user -> {
			user.setLastLoginDate(LocalDateTime.now());
		});
	}
}
