package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_schedule")
@Data
public class Schedule {

	/** id **/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動採番
	@Column(name = "id")
	private Integer id;

	/** スケジュールカテゴリー　**/
	@Column(name = "category_code", length = 5)
	private String categoryCode;

	/** タイトル　**/
	@Column(name = "title", length = 100)
	private String title;

	/** 内容 **/
	@Column(name = "content")
	private String content;

	/** 開始日時 **/
	@Column(name = "start_date")
	private LocalDateTime startDate;

	/** 終了日時 **/
	@Column(name = "end_date")
	private LocalDateTime endDate;

	/** 終日判定 **/
	@Column(name = "is_all_day")
	private Boolean isAllDay;

	/** 公開/非公開 **/
	@Column(name = "is_public")
	private Boolean isPublic;

	/** URL **/
	@Column(name = "meeting_url", length = 500)
	private String meetingUrl;

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
