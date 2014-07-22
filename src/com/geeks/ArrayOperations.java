package com.geeks;

/**
 * http://stackoverflow.com/questions/9368205/given-a-number-find-the-next-
 * higher-number-which-has-the-exact-same-set-of-digi/9368616#9368616
 * 
 * @author joenjoin
 * 
 */
public class ArrayOperations {

	public static void main(String[] args) {
		// test reverseInPlace
		char[] input = { '3', '8', '8', '8', '8', '6' };

		int lastValue = Integer.valueOf(String.valueOf(input));
		do {
			char[] tmp = nextGreatNumber(input);
			int intValue = Integer.valueOf(String.valueOf(input));

			if (lastValue == intValue)
				break;

			lastValue = intValue;
			System.out.println(tmp);
		} while (true);
	}

	/**
	 * By calling this method repeatedly, we can get a permutation
	 * 
	 * @param number
	 * @return
	 */
	private static char[] nextGreatNumber(char[] number) {
		int i = number.length - 1;
		for (; i > 0; i--) {
			if (number[i] > number[i - 1]) {
				break;
			}
		}

		if (i == 0) {
			System.out.println("No pos");
			return number;
		}

		int minLargestPos = i - 1;
		int k;
		for (k = i; k < number.length; k++) {
			if (number[k] < number[minLargestPos]) {
				break;
			}
		}
		char temp = number[k - 1];
		number[k - 1] = number[minLargestPos];
		number[minLargestPos] = temp;

		k = i;
		for (int j = number.length - 1; k <= j; k++, j--) {
			temp = number[k];
			number[k] = number[j];
			number[j] = temp;
		}

		return number;
	}

	/**
	 * Reverse a char sequence in place
	 * 
	 * @param array
	 * @param start
	 *            inclusive start
	 * @param end
	 *            inclusive end
	 */
	private static void reverseInPlace(char[] array, int start, int end) {
		char temp;
		for (int i = start, j = end; i < j; i++, j--) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}
}
