package com.codingopus.collect;

import java.util.function.IntPredicate;

public class NumericPredicates {

	private NumericPredicates() {
	}

	public static final IntPredicate EVEN = val -> ((val & 1) == 0);
	public static final IntPredicate ODD = EVEN.negate();
	
	
}
