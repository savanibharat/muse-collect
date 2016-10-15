package com.codingopus.matrix;

/**
 * More methods are yet to be added.
 */
public interface IntMatrixOperations extends IntScalarOperations, MatrixFunctions {

	/**
	 * Copy {@code this} IntMatrix to another IntMatrix and return IntMatrix
	 */
	IntMatrix copy();

	/**
	 * Addition {@code this} IntMatrix with IntMatrix in parameter
	 */
	IntMatrix add(IntMatrix IntMatrix, ExceptionPredicate pred);

	/**
	 * Addition {@code this} IntMatrix with IntMatrix in parameter
	 */
	IntMatrix rowAddAll(ExceptionPredicate pred);

	IntMatrix rowAddAll(int factor, ExceptionPredicate pred);

	/**
	 * Subtract {@code this} IntMatrix with IntMatrix in parameter
	 */
	IntMatrix subtract(IntMatrix IntMatrix, ExceptionPredicate pred);

	/**
	 * Multiply {@code this} IntMatrix with IntMatrix in parameter
	 */
	IntMatrix multiply(IntMatrix IntMatrix, ExceptionPredicate pred);

	/**
	 * Divide {@code this} IntMatrix with IntMatrix in parameter
	 */
	IntMatrix divide(IntMatrix IntMatrix);

	public IntMatrix abs();

	public IntMatrix signum();

}