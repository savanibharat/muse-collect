package com.codingopus.matrix;

public interface IntScalarOperations {

	/**
	 * Add specified value i.e. scalar to all elements to {@code IntMatrix}.
	 * matrix[i][j] + scalar;
	 * Intermediate operation.
	 */
	IntMatrix add(int scalar, ExceptionPredicate pred);

	/**
	 * Subtract specified value i.e. scalar from all elements to {@code IntMatrix}.
	 * matrix[i][j] - scalar;
	 * Intermediate operation.
	 */
	IntMatrix subtract(int scalar, ExceptionPredicate pred);

	/**
	 * Multiply specified value i.e. scalar to all elements to {@code IntMatrix}.
	 * matrix[i][j] * scalar;
	 * Intermediate operation.
	 */
	IntMatrix multiply(int scalar, ExceptionPredicate pred);

}
