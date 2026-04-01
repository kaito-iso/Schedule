package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * アプリケーション全体のセキュリティ設定を定義するクラス
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * HTTPリクエストに対するセキュリティフィルターを定義
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// アクセス認可の設定
				.authorizeHttpRequests(authz -> authz
						// ログイン前でもアクセスを許可
						.requestMatchers("/login", "/css/**").permitAll()
						// 上記以外は、ログインしないとアクセス不可
						.anyRequest().authenticated())
				// ログイン機能の設定
				.formLogin(form -> form
						.loginPage("/login") // ログインページ（HTML）のURL
						.loginProcessingUrl("/login") // フォームの送信先UR
						.defaultSuccessUrl("/menu", true) // ログイン成功時のリダイレクト先
						.usernameParameter("userId") // HTMLのth:fieldと一致
						.passwordParameter("password") //HTMLのth:fieldと一致
						.permitAll())
				// ログアウト機能の設定
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout") // ログアウト成功後の戻り先
						.permitAll());

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}