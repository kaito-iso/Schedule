package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_facility")
@Data
public class Facility {

	/** 施設コード **/
	@Id
	@Column(name = "facility_code")
	private String facilityCode;

	/** 施設名　**/
	@Column(name = "facility_name")
	private String facilityName;

	/** 表示順序 **/
	@Column(name = "display_order")
	private String displayOrder;

	/** 削除区分 **/
	@Column(name = "is_active")
	private boolean isActive;

	/** 登録日時 **/
	@Column(name = "add_date")
	private LocalDateTime addDate;

	/** 登録者コード **/
	@Column(name = "add_code")
	private LocalDateTime addCode;

	/** 更新日時 **/
	@Column(name = "upd_date")
	private LocalDateTime updDate;

	/** 更新者コード **/
	@Column(name = "upd_code")
	private LocalDateTime updCode;
}
