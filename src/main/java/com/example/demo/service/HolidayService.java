package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Holiday;
import com.example.demo.form.HolidayForm;
import com.example.demo.repository.HolidayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HolidayService {

	private final HolidayRepository repository;

	public List<Holiday> findHolidays() {
		return repository.findByIsDeletedOrderByHolidayDateAsc(false);
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
		holi.setCreateType("man");
		holi.setDeleted(false);

		repository.save(holi);
	}

	public boolean holidayDelete(LocalDate holidayDate) {

		Optional<Holiday> optHoliday = findById(holidayDate);

		if (optHoliday.get().getCreateType().equals("api")) {
			System.out.println("api");
		} else {
			System.out.println("man");
		}
		return true;
	}

}
