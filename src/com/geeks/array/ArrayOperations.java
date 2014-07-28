package com.geeks.array;

/**
 * @author joenjoin
 */
public class ArrayOperations {

	public static void main(String[] args) {
		// // test reverseInPlace
		// char[] input = { '3', '8', '8', '8', '8', '6' };
		//
		// int lastValue = Integer.valueOf(String.valueOf(input));
		// do {
		// char[] tmp = nextGreatNumber(input);
		// int intValue = Integer.valueOf(String.valueOf(input));
		//
		// if (lastValue == intValue)
		// break;
		//
		// lastValue = intValue;
		// System.out.println(tmp);
		// } while (true);

		// String string = "   I am a   good   student   ";
		// char[] array = string.toCharArray();
		//
		// ArrayOperations.removeDuplicateWhiteSpaces(array);
		// System.out.println(array);

		int[] array = { -2, -3, 4, -1, -2, 1, 5, -3 };
		// int[] array = { -2, -3, -4, 1, -2, 3, -1, -3 };
		largestSumContiguousSubarrayRecur(array);

		int maxSum = largestSumContiguousSubarrayDP(array);
		System.out.println(maxSum);
	}

	/**
	 * http://stackoverflow.com/questions/9368205/given-a-number-find-the-next-
	 * higher-number-which-has-the-exact-same-set-of-digi/9368616#9368616
	 * 
	 * By calling this method repeatedly, we can get a permutation
	 * 
	 * @param number
	 * @return
	 */
	static char[] nextGreatNumber(char[] number) {
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
	static void reverseInPlace(char[] array, int start, int end) {
		char temp;
		for (int i = start, j = end; i < j; i++, j--) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	/**
	 * Remove duplicate white spaces in a char array, for example:
	 * 
	 * *************************************************************************
	 * input: _ _ _ _ _ _ I_am_a__good___students______
	 * 
	 * output:I_am_a_good_students
	 * 
	 * @param array
	 *            char array which contains multiple white spaces
	 */
	static void removeDuplicateWhiteSpaces(char[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		int p = 0; // fast pointer
		int s = 0; // slow pointer

		boolean flag = false;
		if (array[s] == ' ')
			flag = true;

		while (p < array.length) {

			if (array[p] != ' ') {
				array[s++] = array[p];
			} else {
				while (p < array.length && array[p] == ' ') {
					p++;
				}

				if (flag || p == array.length) {
					// Processing starting white spaces
					// or trailing white spaces
					flag = false;
				} else {
					array[s++] = ' ';
				}
				p--;
			}
			p++;
		}

		while (s < array.length) {
			// Fill trailing whitespace with *
			array[s++] = '*';
		}
	}

	/**
	 * Given an array A[] and a number x, check for pair in A[] with sum as x
	 * Write a C program that, given an array A[] of n numbers and another
	 * number x, determines whether or not there exists two elements in S whose
	 * sum is exactly x.
	 * 
	 * @param array
	 * @param sum
	 */
	static void printPairsOfElementsEqualsToSum(char array[], int sum) {
		// 1. Sort the array

		// 2. Find the elements
	}

	/**
	 * Merge 2 sorted array
	 * 
	 * @param array1
	 *            Containing n+m capacity and n elements, after meged, array1
	 *            will hold all merged elements
	 * @param array2
	 *            Containing m elements to be merged
	 */
	static void mergeTwoSortedArrays(char[] array1, char[] array2) {
	}

	/**
	 * Largest Sum Contiguous Sub-array
	 * 
	 * Write an efficient C program to find the sum of contiguous sub-array
	 * within a one-dimensional array of numbers which has the largest sum.
	 * 
	 * @param array
	 */
	public static void largestSumContiguousSubarrayRecur(int[] array) {
		int[] tmp = new int[2];
		largestSumContiguousSubarrayRecurInternal(array, 0, array.length - 1,
				true, tmp);

		System.out.println(tmp[0]);
		// System.out.println(tmp[1]);
	}

	/**
	 * Compute latest sum of sub-array in an array, recursive way
	 * 
	 * @param array
	 *            target array
	 * @param start
	 *            start pos for recur
	 * @param end
	 *            end pos for recur
	 * @param direction
	 *            true if this is the left wing of parent, false for right wing
	 * @param tmp
	 *            tmp array to carry data among recursions, tmp[0] for local max
	 *            sum, tmp[1] for max adjacent to use by parent
	 */
	private static void largestSumContiguousSubarrayRecurInternal(int[] array,
			int start, int end, boolean direction, int[] tmp) {

		if (start == end) {
			tmp[0] = tmp[1] = array[start];
			return;
		}

		int mid = (start + end) / 2;
		int maxLeft, maxLeftAdjacent, maxRight, maxRightAdjacent;

		if (mid == start) {
			maxLeft = maxLeftAdjacent = array[mid];
			maxRight = maxRightAdjacent = array[end];
		} else {
			largestSumContiguousSubarrayRecurInternal(array, start, mid, true,
					tmp);
			maxLeft = tmp[0];
			maxLeftAdjacent = tmp[1];

			largestSumContiguousSubarrayRecurInternal(array, mid + 1, end,
					false, tmp);
			maxRight = tmp[0];
			maxRightAdjacent = tmp[1];
		}

		int localMax = max(maxLeft, maxLeftAdjacent, maxRight,
				maxRightAdjacent, (maxLeftAdjacent + maxRightAdjacent));
		tmp[0] = localMax;

		int maxAdjacent;
		if (direction) { // This is the left wing of parent
			maxAdjacent = array[end];
			for (int i = end - 1; i > start - 1; i--) {
				int sum = maxAdjacent + array[i];
				if (sum > maxAdjacent) {
					maxAdjacent = sum;
				}
			}
		} else { // Right wing
			maxAdjacent = array[start];
			for (int i = start + 1; i <= end; i++) {
				int sum = maxAdjacent + array[i];
				if (sum > maxAdjacent) {
					maxAdjacent = sum;
				}
			}
		}
		tmp[1] = maxAdjacent;
	}

	/**
	 * Compute latest sum of sub-array in an array, dynamic programming way
	 * 
	 * Should consider cases when there're all negative numbers
	 * 
	 * @param array
	 */
	public static int largestSumContiguousSubarrayDP(int[] array) {
		int maxSumToNow = array[0];
		int maxSumGlobal = maxSumToNow;

		for (int i = 0; i < array.length; i++) {
			maxSumToNow = max(array[i], maxSumToNow + array[i]);
			maxSumGlobal = max(maxSumToNow, maxSumGlobal);
		}

		return maxSumGlobal;
	}

	private final static int max(int... values) {
		int max = values[0];
		for (int value : values) {
			if (value > max)
				max = value;
		}

		return max;
	}
}
