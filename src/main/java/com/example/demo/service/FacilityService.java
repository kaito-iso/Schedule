package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Facility;
import com.example.demo.form.FacilityForm;
import com.example.demo.repository.FacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityService {

	private final FacilityRepository repository;

	/**
	 * 全ての施設情報を取得
	 * @return 施設マスタのリスト
	 */
	public List<Facility> findAll() {
		return repository.findAllByOrderByDisplayOrderAsc();
	}

	/**
	 * 施設コードをキーに施設情報を取得
	 * @param facilityCode 検索対象の施設コード
	 * @return 該当する施設が存在する場合はそのエンティティを含むOptional、存在しない場合は空のOptional
	 */
	public Optional<Facility> findById(String facilityCode) {
		return repository.findById(facilityCode);
	}

	/**
	 * 指定された施設コードのデータを削除
	 * @param facilityCode 削除対象の施設コード
	 */
	public void delete(String facilityCode) {
		repository.deleteById(facilityCode);
	}

	/**
	 * 施設情報の登録または更新を行う
	 * <p>
	 * mode引数によって以下のバリデーションを行います：
	 * <ul>
	 * <li>"create": 施設コードが既に存在する場合は例外を投げる</li>
	 * <li>"edit": 更新対象のデータが存在しない場合は例外を投げる</li>
	 * </ul>
	 * 表示順が数値として不正な場合は、デフォルト値として0を設定します。
	 * </p>
	 * @param form 施設情報の入力内容を含むフォームオブジェクト
	 * @param mode 処理モード ("create" または "edit")
	 * @throws RuntimeException バリデーションエラーが発生した場合
	 */
	public void save(FacilityForm form, String mode, String userId) {

		Facility facility;

		if ("edit".equals(mode)) {
			facility = repository.findById(form.getFacilityCode())
					.orElseThrow(() -> new RuntimeException("不正なリクエストです：更新対象が存在しません"));

			facility.setUpdDate(LocalDateTime.now());
			facility.setUpdCode(userId);

		} else if ("create".equals(mode)) {

			if (repository.existsById(form.getFacilityCode())) {
				throw new RuntimeException("この施設コードは既に登録されています");
			}
			
			facility = new Facility();
			facility.setFacilityCode(form.getFacilityCode());
			facility.setAddDate(LocalDateTime.now());
			facility.setAddCode(userId);
			
		} else {
			throw new IllegalArgumentException("無効なモードです: " + mode);
		}

		facility.setFacilityName(form.getFacilityName());

		try {
			facility.setDisplayOrder(Integer.parseInt(form.getDisplayOrder()));
		} catch (NumberFormatException e) {
			facility.setDisplayOrder(0);
		}

		facility.setActive(form.isActive());

		repository.save(facility);
	}
}
