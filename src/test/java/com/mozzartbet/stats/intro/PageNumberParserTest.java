package com.mozzartbet.stats.intro;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.SortedSet;

import org.junit.Test;

import com.google.common.collect.Sets;

public class PageNumberParserTest {

	final PageNumberParser p = new PageNumberParser();

	@Test
	public void testParse() throws Exception {
		assertEquals(set(1), p.parse("1"));
		assertEquals(set(1, 2), p.parse("2, 1"));
		assertEquals(set(1, 2, 5, 6, 7), p.parse("6-7,1-2 , 5"));
		assertEquals(set(1, 2, 4, 5, 6, 7, 9, 10), p.parse(" 1, 5 -6, 9-10, 6, 4 -   7, 2"));
	}

	@Test(expected = NumberFormatException.class)
	public void testParseInvalidChar() throws Exception {
		assertEquals(set(1), p.parse("1a"));
	}

	@Test(expected = NumberFormatException.class)
	public void testParseIncomplete() throws Exception {
		assertEquals(set(1), p.parse("1,2/z"));
	}

	private static SortedSet<Integer> set(Integer... values) {
		return Sets.newTreeSet(Arrays.asList(values));
	}
}
