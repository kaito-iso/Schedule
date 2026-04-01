package com.example.demo.config;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final UserService userService;

	/*
	 * ログインに成功すると自動で呼び出されるメソッド
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// ログインしたユーザーのIDを取得
		String userId = authentication.getName();

		// 最終ログイン日時を更新
		userService.updateLastLoginDate(userId);

		// ログイン成功時のリダイレクト先
		this.setAlwaysUseDefaultTargetUrl(true);
		this.setDefaultTargetUrl("/menu");

		// 遷移先へリダイレクトさせる処理
		super.onAuthenticationSuccess(request, response, authentication);
	}
}