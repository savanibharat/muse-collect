package com.codingopus.matrix;

public interface DoubleScalarOperations {

	/**
	 * Add specified value i.e. scalar to all elements to {@code DoubleMatrix}.
	 * matrix[i][j] + scalar;
	 * Intermediate operation.
	 */
	DoubleMatrix add(double scalar, ExceptionPredicate pred);

	/**
	 * Subtract specified value i.e. scalar from all elements to {@code DoubleMatrix}.
	 * matrix[i][j] - scalar;
	 * Intermediate operation.
	 */
	DoubleMatrix subtract(double scalar, ExceptionPredicate pred);

	/**
	 * Multiply specified value i.e. scalar to all elements to {@code DoubleMatrix}.
	 * matrix[i][j] * scalar;
	 * Intermediate operation.
	 */
	DoubleMatrix multiply(double scalar, ExceptionPredicate pred);

}
