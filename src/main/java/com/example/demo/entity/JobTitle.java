package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_job_title")
@Data
public class JobTitle {

	/** 役職コード **/
	@Id
	@Column(name = "job_title_code", length = 3)
	private String jobTitleCode;

	/** 役職名　**/
	@Column(name = "job_title_name", length = 50, nullable = false)
	private String jobTitleName;

	/** 表示順序 **/
	@Column(name = "display_order", nullable = false)
	private Integer displayOrder;

	/** 削除区分 **/
	@Column(name = "is_active", nullable = false)
	private boolean active;

	/** 登録日時 **/
	@Column(name = "add_date", nullable = false)
	private LocalDateTime addDate;

	/** 登録者コード **/
	@Column(name = "add_code", length = 32, nullable = false)
	private String addCode;

	/** 更新日時 **/
	@Column(name = "upd_date")
	private LocalDateTime updDate;

	/** 更新者コード **/
	@Column(name = "upd_code", length = 32)
	private String updCode;
}
