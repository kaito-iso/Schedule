package com.example.demo.service;

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

	public List<Facility> findAll() {
		return repository.findAll();
	}

	public List<Facility> findFacilities(boolean acitive) {
		return repository.findByActiveOrderByDisplayOrder(acitive);
	}

	public Optional<Facility> findById(String facilityCode) {
		return repository.findById(facilityCode);
	}

	public void delete(String facilityCode) {
		repository.deleteById(facilityCode);
	}

	public void save(FacilityForm form, String mode) {

		if ("edit".equals(mode)) {
			if (!repository.existsById(form.getFacilityCode())) {
				throw new RuntimeException("不正なリクエストです：更新対象が存在しません");
			}
		}

		if ("create".equals(mode)) {
			if (repository.existsById(form.getFacilityCode())) {
				throw new RuntimeException("この施設コードは既に登録されています");
			}
		}

		Facility facility = new Facility();
		facility.setFacilityCode(form.getFacilityCode());
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
