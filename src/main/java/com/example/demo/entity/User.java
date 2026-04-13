package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_user")
@Data
public class User {

	/** ユーザーID **/
	@Id
	@Column(name = "user_id", length = 32)
	private String userId;

	/** パスワード　**/
	@Column(name = "password", length = 255, nullable = false)
	private String password;

	/** 権限 **/
	@Column(name = "role", length = 15, nullable = false)
	private String role;

	/** アカウント有効 **/
	@Column(name = "is_account_enabled", nullable = false)
	private boolean isAccountEnabled;

	/** 苗字 **/
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;

	/** ミドルネーム **/
	@Column(name = "middle_name", length = 50)
	private String middleName;

	/** 名前 **/
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	/** 苗字(カナ） **/
	@Column(name = "last_name_kana", length = 50, nullable = false)
	private String lastNameKana;

	/** ミドルネーム(カナ) **/
	@Column(name = "middle_name_kana", length = 50)
	private String middleNameKana;

	/** 名前(カナ) **/
	@Column(name = "first_name_kana", length = 50, nullable = false)
	private String firstNameKana;

	/** メールアドレス **/
	@Column(name = "mail", length = 255)
	private String mail;

	/** 役職コード **/
	@Column(name = "job_title_code", length = 3)
	private String jobTitleCode;

	/** 内線番号 **/
	@Column(name = "extension_tel", length = 50)
	private String extensionTel;

	/** 携帯電話番号 **/
	@Column(name = "mobile_tel", length = 50)
	private String mobileTel;

	/** 誕生日 **/
	@Column(name = "birthday")
	private LocalDate birthday;

	/** 性別 **/
	@Column(name = "gender", length = 1)
	private String gender;

	/** 最終ログイン日時 **/
	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;

	/** 入社日 **/
	@Column(name = "hire_date")
	private LocalDate hireDate;

	/** 削除区分 **/
	@Column(name = "is_active")
	private boolean active;

	/** 登録日時 **/
	@Column(name = "add_date")
	private LocalDateTime addDate;

	/** 登録者コード **/
	@Column(name = "add_code", length = 32)
	private String addCode;

	/** 更新日時 **/
	@Column(name = "upd_date")
	private LocalDateTime updDate;

	/** 更新者コード **/
	@Column(name = "upd_code", length = 32)
	private String updCode;
	
	/** 苗字 + 名前**/
	public String getFullName() {
        return String.format("%s %s", this.lastName, this.firstName);
    }
}
