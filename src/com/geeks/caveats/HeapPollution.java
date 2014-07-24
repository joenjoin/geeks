package com.geeks.caveats;

import java.util.Arrays;
import java.util.List;

/**
 * Heap pollution is a technical term. It refers to references which have a type
 * that is not a supertype of the object they point to.
 * 
 * @author joenjoin
 * 
 */
public class HeapPollution {

	public static void main(String[] args) {
		List<Integer> listOfAs = Arrays.asList(1, 2);

		List<String> listOfBs = (List<String>) (Object) listOfAs;

		// No compile error, but ClassCastException will be thrown at runtime
		// If the heap never gets polluted, this should never throw a CCE
		String b = listOfBs.get(0);
	}
}
