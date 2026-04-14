package com.example.demo.form;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.Data;

@Data
public class ScheduleForm {

	/** 開始年 **/
	@NotNull(message = "開始年が入力されていません")
	private Integer startYear;

	/** 開始月 **/
	@NotNull(message = "開始月が入力されていません")
	private Integer startMonth;

	/** 開始日 **/
	@NotNull(message = "開始日が入力されていません")
	private Integer startDay;

	/** 開始時間 **/
	private Integer startHour;

	/** 開始分 **/
	private Integer startMinute;

	/** 終了年 **/
	@NotNull(message = "終了年が入力されていません")
	private Integer endYear;

	/** 終了月 **/
	@NotNull(message = "終了月が入力されていません")
	private Integer endMonth;

	/** 終了日 **/
	@NotNull(message = "終了日が入力されていません")
	private Integer endDay;

	/** 終了時間 **/
	private Integer endHour;

	/** 終了分 **/
	private Integer endMinute;

	/** 終日チェック **/
	private Boolean allDay;

	/** スケジュールカテゴリー **/
	private String categoryCode;

	/** 予定内容 **/
	@Size(max = 50, message = "最大50文字までしか登録できません")
	private String title;

	/** 内容 **/
	private String content;

	/** URL **/
	@URL(message = "有効なURL形式で入力してください")
	@Size(max = 500, message = "最大500文字までしか登録できません")
	private String meetingUrl;

	/** 参加者 **/
	private List<String> participants;

	/** 施設 **/
	private List<String> facilities;

	/** 公開 **/
	private Boolean isPublic;

	/** 開始日時変換 **/
	public LocalDateTime getStartDateTime() {
		if (this.allDay)
			return LocalDateTime.of(startYear, startMonth, startDay, 0, 0);

		int hour = (startHour != null) ? startHour : 0;
		int minute = (startMinute != null) ? startMinute : 0;

		return LocalDateTime.of(startYear, startMonth, startDay, hour, minute);
	}

	/** 終了日時変換 **/
	public LocalDateTime getEndDateTime() {
		if (this.allDay)
			return LocalDateTime.of(endYear, endMonth, endDay, 23, 59, 59);

		int hour = (endHour != null) ? endHour : 23;
		int minute = (endMinute != null) ? endMinute : 59;

		return LocalDateTime.of(endYear, endMonth, endDay, hour, minute);
	}
}
