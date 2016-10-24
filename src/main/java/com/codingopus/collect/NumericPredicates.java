package com.codingopus.collect;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

public class NumericPredicates {

	private NumericPredicates() {
	}

	public static final IntPredicate EVEN = val -> ((val & 1) == 0);
	public static final IntPredicate ODD = EVEN.negate();
	
	public static final LongPredicate LONG_EVEN = val -> ((val & 1) == 0);
	public static final LongPredicate LONG_ODD = LONG_EVEN.negate();

	public static final DoublePredicate DOUBLE_EVEN = val -> (val % 2 == 0);
	public static final DoublePredicate DOUBLE_ODD = DOUBLE_EVEN.negate();

}
