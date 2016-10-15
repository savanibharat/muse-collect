package com.codingopus.matrix;

/**
 * Typically used in method like <br/>
 * <code>IntMatrix matrix = IntMatrix.of(3,3).add(10, () -> true);</code> <br/>
 * If the addition overflows then it throws exception else it returns result.
 * <br/>
 * <code>IntMatrix matrix = IntMatrix.of(3,3).add(10, () -> false);</code> <br/>
 * It will return result regardless of result overflow.
 * 
 */
@FunctionalInterface
public interface ExceptionPredicate {

	public boolean throwException();

}
