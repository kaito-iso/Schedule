package com.example.demo.form;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import lombok.Data;

@Data
public class ScheduleForm {

	/** 開始年 **/
	private Integer startYear;

	/** 開始月 **/
	private Integer startMonth;

	/** 開始日 **/
	private Integer startDay;

	/** 開始時間 **/
	private Integer startHour;

	/** 開始分 **/
	private Integer startMinute;

	/** 終了年 **/
	private Integer endYear;

	/** 終了月 **/
	private Integer endMonth;

	/** 終了日 **/
	private Integer endDay;

	/** 終了時間 **/
	private Integer endHour;

	/** 終了分 **/
	private Integer endMinute;
	
	/** 終日チェック **/
	private boolean allDay;

	/** スケジュールカテゴリー **/
	private String categoryCode;

	/** 予定内容 **/
	private String title;

	/** 内容 **/
	private String content;

	/** URL **/
	@URL(message = "有効なURL形式で入力してください")
	private String meetingUrl;

	/** 参加者 **/
	private List<String> participants;

	/** 施設 **/
	private List<String> facilities;

	/** 公開 **/
	private boolean isPublic;
}
