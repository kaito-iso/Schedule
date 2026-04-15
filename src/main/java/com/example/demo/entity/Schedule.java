package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	/** 
	 * カテゴリーマスタ(m_schedule_category)との紐付け
     * * @ManyToOne: 
     * 「多(Schedule) 対 一(Category)」の関係
     * JPAに対し、内部的にLEFT OUTER JOINを発行してカテゴリー情報を取得するよう指示
     * * @JoinColumn:
     * どの列をキーにして結合するかを指定。
     * name: 自身のテーブル(t_schedule)にある結合キー「category_code」
     * referencedColumnName: 相手のテーブル(m_schedule_category)の結合キー「category_code」
     */
	@ManyToOne
	@JoinColumn(name = "category_code", referencedColumnName = "category_code", insertable = false, updatable = false)
	private ScheduleCategory category;

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
