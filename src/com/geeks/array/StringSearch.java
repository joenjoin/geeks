package com.geeks.array;

import java.util.Arrays;

public class StringSearch {

	public static void main(String[] args) {

		char[] pattern = "nanonannad".toCharArray();
		char[] target = "banananobano".toCharArray();

		int matched = kmp(pattern, target);
		System.out.println(matched);
	}

	/**
	 * 
	 * @param pattern
	 * @param target
	 * @return
	 */
	public static int kmp(char[] pattern, char[] target) {
		int matched = -1;

		int[] overlaps = preprocessPattern(pattern);

		System.out.println(Arrays.toString(overlaps));
		return matched;
	}

	private static int[] preprocessPattern(char[] pattern) {
		// nanonannad
		int[] overlaps = new int[pattern.length];

		overlaps[0] = 0;

		int j = 1;

		while (j < pattern.length) {
			if (pattern[j] == pattern[overlaps[j - 1] - 1 + 1]) {
				overlaps[j] = overlaps[j - 1] + 1;
			} else {
				int k = overlaps[j - 1];
				while (k != 0) {
					if (pattern[k] == pattern[j]) {
						overlaps[j] = overlaps[k - 1] + 1;
					} else {
						k = overlaps[k - 1];
					}
				}

				if (k == 0) {
					if (pattern[k] == pattern[j]) {
						overlaps[j] = 1;
					} 
				}
			}

			j++;
		}

		return overlaps;
	}
}
