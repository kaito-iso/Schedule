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
public class UserService{

	private final UserRepository repository;

	public Optional<User> findByUser(String userId) {
		return repository.findById(userId);
	}
	
	@Transactional
    public void updateLastLoginDate(String userId) {
        repository.findById(userId).ifPresent(user -> {
            user.setLastLoginDate(LocalDateTime.now());
            // @Transactionalがあるので、これだけでDBに反映されます
        });
    }
}
