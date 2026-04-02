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

	public List<Holiday> findHolidays() {
		return repository.findByOrderByHolidayDateAsc();
	}

	public Optional<Holiday> findById(LocalDate holiday) {
		return repository.findById(holiday);
	}

	public boolean existsById(LocalDate holiday) {
		return repository.existsById(holiday);
	}

	public void save(HolidayForm form) {

		Holiday holi = new Holiday();
		holi.setHolidayDate(form.getHolidayDate());
		holi.setHolidayName(form.getHolidayName());
		holi.setCreateType("manual");

		repository.save(holi);
	}

	public void holidayDelete(LocalDate holidayDate) {
		repository.deleteById(holidayDate);
	}

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
