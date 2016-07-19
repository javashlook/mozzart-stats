package com.mozzartbet.stats.intro;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberProcessorTest {

	final NumberProcessor np = new NumberProcessor();

	@Test
	public void testReverseDigits() throws Exception {
		assertArrayEquals(new int[] { 0 }, np.reverseDigits(0));
		assertArrayEquals(new int[] { 1 }, np.reverseDigits(1));
		assertArrayEquals(new int[] { 2, 1 }, np.reverseDigits(12));
		assertArrayEquals(new int[] { 0, 1 }, np.reverseDigits(10));
		assertArrayEquals(new int[] { 0, 0, 0, 1 }, np.reverseDigits(1000));
		assertArrayEquals(new int[] { 4, 3, 2, 1 }, np.reverseDigits(1234));
		assertArrayEquals(new int[] { 4, 3, 2, 1 }, np.reverseDigits(-1234));
		assertArrayEquals(new int[] { 1, 1 }, np.reverseDigits(11));
		assertArrayEquals(new int[] { 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, np.reverseDigits(1234567890));
	}

}
