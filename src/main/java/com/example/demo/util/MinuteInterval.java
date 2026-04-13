package com.example.demo.util;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 分の刻み間隔を管理する列挙型
 */
@Getter
@RequiredArgsConstructor
public enum MinuteInterval {

	INTERVAL_5(5); // 5分刻みを定義

	private final int value;

	/**
	 * 定義された間隔に基づいた「分」のリストを取得する
	 * @return ["00", "05", "10", ..., "55"]
	 */
	public static List<String> getLabels() {

		int step = INTERVAL_5.value;
		int count = 60 / step;

		return IntStream.iterate(0, n -> n + step)
				.limit(count)
				.mapToObj(n -> String.format("%02d", n))
				.toList();
	}
}