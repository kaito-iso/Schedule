package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.JobTitle;
import com.example.demo.form.JobTitleForm;
import com.example.demo.repository.JobTitleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobTitleService {

	private final JobTitleRepository repository;

	/**
	 * 全ての役職情報を取得
	 * @return 役職マスタのリスト
	 */
	public List<JobTitle> findAll() {
		return repository.findAllByOrderByDisplayOrderAsc();
	}
	
	/**
	 * 役職コードをキーに役職情報を取得
	 * @param jobTitleCode 検索対象の施設コード
	 * @return 該当する役職が存在する場合はそのエンティティを含むOptional、存在しない場合は空のOptional
	 */
	public Optional<JobTitle> findById(String jobTitleCode) {
		return repository.findById(jobTitleCode);
	}
	
	/**
	 * 指定された役職コードのデータを削除
	 * @param facilityCode 削除対象の施設コード
	 */
	public void delete(String jobTitleCode) {
		repository.deleteById(jobTitleCode);
	}
	
	/**
	 * 役職情報の登録または更新を行う
	 * <p>
	 * mode引数によって以下のバリデーションを行います：
	 * <ul>
	 * <li>"create": 役職コードが既に存在する場合は例外を投げる</li>
	 * <li>"edit": 更新対象のデータが存在しない場合は例外を投げる</li>
	 * </ul>
	 * 表示順が数値として不正な場合は、デフォルト値として0を設定します。
	 * </p>
	 * @param form 役職情報の入力内容を含むフォームオブジェクト
	 * @param mode 処理モード ("create" または "edit")
	 * @throws RuntimeException バリデーションエラーが発生した場合
	 */
	public void save(JobTitleForm form, String mode, String userId) {

		JobTitle jobTitle;

		if ("edit".equals(mode)) {
			jobTitle = repository.findById(form.getJobTitleCode())
					.orElseThrow(() -> new RuntimeException("不正なリクエストです：更新対象が存在しません"));

			jobTitle.setUpdDate(LocalDateTime.now());
			jobTitle.setUpdCode(userId);

		} else if ("create".equals(mode)) {

			if (repository.existsById(form.getJobTitleCode())) {
				throw new RuntimeException("この施設コードは既に登録されています");
			}
			
			jobTitle = new JobTitle();
			jobTitle.setJobTitleCode(form.getJobTitleCode());
			jobTitle.setAddDate(LocalDateTime.now());
			jobTitle.setAddCode(userId);
			
		} else {
			throw new IllegalArgumentException("無効なモードです: " + mode);
		}

		jobTitle.setJobTitleName(form.getJobTitleName());

		try {
			jobTitle.setDisplayOrder(Integer.parseInt(form.getDisplayOrder()));
		} catch (NumberFormatException e) {
			jobTitle.setDisplayOrder(0);
		}

		jobTitle.setActive(form.getActive());

		repository.save(jobTitle);
	}
}
