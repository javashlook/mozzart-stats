package com.mozzartbet.stats.intro;

import java.util.SortedSet;
import java.util.TreeSet;

public class PageNumberParser {

	public SortedSet<Integer> parse(String str) {
		SortedSet<Integer> pages = new TreeSet<>();

		for (String group : trimWhitespace(str).split(",")) {
			String[] parts = group.split("-");

			if (parts.length == 1) {
				pages.add(Integer.parseInt(parts[0]));
			}
			else if (parts.length == 2) {
				int from = Integer.parseInt(parts[0]);
				int to = Integer.parseInt(parts[1]);
				for (int i = from; i <= to; i ++) {
					pages.add(i);
				}
			}
		}

		return pages;
	}

	private String trimWhitespace(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, n = str.length(); i < n; i ++) {
			int cp = str.codePointAt(i);
			if (!Character.isWhitespace(cp)) {
				sb.appendCodePoint(cp);
			}
		}
		return sb.toString();
	}


}
