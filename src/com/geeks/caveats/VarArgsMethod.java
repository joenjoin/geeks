package com.geeks.caveats;

public class VarArgsMethod {
	public static void main(String[] args) {
		int values[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int size = getVargsLength(values);

		System.out.println(size); // output: 1

		String strings[] = { "abc", "cde", "efg" };
		size = getVargsLength(strings);
		System.out.println(size); // output: 3

	}

	/**
	 * @Caveat 1: primitive array is treated as on object for vargs, reference
	 *         array can be treated as array
	 */
	private static <T> int getVargsLength(T... args) {
		return args.length;
	}
}
