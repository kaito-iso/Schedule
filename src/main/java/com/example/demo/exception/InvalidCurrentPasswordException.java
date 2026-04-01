package com.example.demo.exception;

/**
 * 現在のパスワードが違う場合にスローされる例外
 */
public class InvalidCurrentPasswordException extends RuntimeException {
	
	public InvalidCurrentPasswordException() {
		super("現在のパスワードが正しくありません");
	}
}
