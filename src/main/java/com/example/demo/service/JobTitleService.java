package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.JobTitle;
import com.example.demo.repository.JobTitleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobTitleService {

	private final JobTitleRepository repository;

	/**
	 * 全ての役職マスタを取得
	 * @return 役職マスタのリスト
	 */
	public List<JobTitle> findAll() {
		return repository.findAllByOrderByDisplayOrderAsc();
	}
}
