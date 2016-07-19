package com.mozzartbet.stats.intro;

import java.util.Arrays;

public class NumberProcessor {

	public int[] reverseDigits(int value) {
		int number = Math.abs(value);

		int[] digits = new int[20];

		int i = 0;
		while (number >= 0) {
			digits[i++] = number % 10;
			number /= 10;
			if (number == 0) {
				break;
			}
		}

		return Arrays.copyOf(digits, i);
	}

}
