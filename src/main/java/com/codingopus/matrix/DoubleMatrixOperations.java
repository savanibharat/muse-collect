package com.codingopus.matrix;

public interface DoubleMatrixOperations extends DoubleScalarOperations, MatrixFunctions {

	/**
	 * Copy {@code this} DoubleMatrix to another DoubleMatrix and return
	 * DoubleMatrix
	 */
	DoubleMatrix copy();

	/**
	 * Addition {@code this} DoubleMatrix with DoubleMatrix in parameter
	 */
	DoubleMatrix add(DoubleMatrix doubleMatrix);

	/**
	 * Subtract {@code this} DoubleMatrix with DoubleMatrix in parameter
	 */
	DoubleMatrix subtract(DoubleMatrix doubleMatrix);

	/**
	 * Multiply {@code this} DoubleMatrix with DoubleMatrix in parameter
	 */
	DoubleMatrix multiply(DoubleMatrix doubleMatrix);

	/**
	 * Divide {@code this} DoubleMatrix with DoubleMatrix in parameter
	 */
	DoubleMatrix divide(DoubleMatrix doubleMatrix);

	public DoubleMatrix abs();

	public DoubleMatrix signum();

}
