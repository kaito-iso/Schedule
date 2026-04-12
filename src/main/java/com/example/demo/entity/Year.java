package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_year")
@Data
public class Year {

	/** 西暦 **/
	@Id
	@Column(name = "`year`")
	private Integer year;

	/** 元号　**/
	@Column(name = "era_name", length = 10)
	private String eraName;

	/** 元号年 **/
	@Column(name = "eraYear")
	private Integer eraYear;

	/** 削除区分 **/
	@Column(name = "is_active")
	private boolean active;

}
