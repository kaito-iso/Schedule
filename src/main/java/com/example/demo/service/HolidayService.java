package com.example.demo.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Holiday;
import com.example.demo.form.HolidayForm;
import com.example.demo.repository.HolidayRepository;

import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class HolidayService {

	private final HolidayRepository repository;
	private final ObjectMapper objectMapper;

	/**
	 * 全ての祝日情報を取得し、日付の昇順でソートして返す
	 * @return 祝日エンティティのリスト
	 */
	public List<Holiday> findHolidays() {
		return repository.findByOrderByHolidayDateAsc();
	}

	/**
	 * 指定された日付をキーに祝日情報を取得
	 * @param holiday 検索対象の日付
	 * @return 該当する祝日が存在する場合はそのエンティティを含むOptional、存在しない場合は空のOptional
	 */
	public Optional<Holiday> findById(LocalDate holiday) {
		return repository.findById(holiday);
	}

	/**
	 * 指定された日付の祝日データが存在するか確認
	 * @param holiday 確認対象の日付
	 * @return 存在する場合はtrue、存在しない場合はfalse
	 */
	public boolean existsById(LocalDate holiday) {
		return repository.existsById(holiday);
	}

	/**
	 * 画面からの入力値を元に、手動登録として祝日情報を保存
	 * @param form 登録内容を含むフォームオブジェクト
	 */
	public void save(HolidayForm form) {

		Holiday holi = new Holiday();
		holi.setHolidayDate(form.getHolidayDate());
		holi.setHolidayName(form.getHolidayName());
		holi.setCreateType("manual");

		repository.save(holi);
	}

	/**
	 * 指定された日付の祝日データを削除
	 * @param holidayDate 削除対象の日付
	 */
	public void holidayDelete(LocalDate holidayDate) {
		repository.deleteById(holidayDate);
	}

	/**
	 * 外部APIから指定された年の祝日データを取得し、データベースにインポートする
	 * @param year 取得対象の年（例: 2024）
	 * @throws Exception API通信失敗やJSON解析エラーが発生した場合
	 */
	public void importHolidaysApi(int year) throws Exception {

		String apiUrl = String.format("https://holidays-jp.github.io/api/v1/%d/date.json", year);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiUrl))
				.GET()
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() != 200) {
			throw new RuntimeException("API取得に失敗しました。ステータスコード: " + response.statusCode());
		}

		Map<String, String> apiData = objectMapper.readValue(
				response.body(),
				new TypeReference<Map<String, String>>() {
				});

		apiData.entrySet().stream()
				.map(entry -> {
					Holiday holiday = new Holiday();
					holiday.setHolidayDate(LocalDate.parse(entry.getKey()));
					holiday.setHolidayName(entry.getValue());
					holiday.setCreateType("api");
					return holiday;
				})
				.forEach(holiday -> repository.save(holiday));

	}
}
