package com.codingopus.matrix;

import com.codingopus.collect.DoubleArray;

public interface DoubleRealMatrix {

	/** Determines whether Matrix is Square matrix or not */
	/**
	 * Checks if is square matrix.
	 *
	 * @param matrix
	 *            the matrix
	 * @return true, if is square matrix
	 */
	boolean isSquareMatrix();

	/** Returns the Row Length of the Matrix */
	/**
	 * Gets the row length.
	 *
	 * @param matrix
	 *            the matrix
	 * @return the row length
	 */
	int getRowLength();

	/** Returns the Column Length of the Matrix */
	/**
	 * Gets the column length.
	 *
	 * @param matrix
	 *            the matrix
	 * @return the column length
	 */
	int getColumnLength();

	/** Returns the array for specified Row else returns null */
	/**
	 * Gets the row elements.
	 *
	 * @param rowNumber
	 *            the row number
	 * @return the row elements
	 */
	DoubleArray getRowElements(int rowNumber);

	/** Returns the array for specified Column else returns null */
	/**
	 * Gets the column elements.
	 *
	 * @param columnNumber
	 *            the column number
	 * @return the column elements
	 */
	DoubleArray getColumnElements(int columnNumber);

	/** Checks whether the Matrix is Row Vector or not */
	/**
	 * Checks if is row vector.
	 *
	 * @param matrix
	 *            the matrix
	 * @return true, if is row vector
	 */
	boolean isRowVector();

	/** Checks whether the Matrix is Column Vector or not */
	/**
	 * Checks if is column vector.
	 *
	 * @param matrix
	 *            the matrix
	 * @return true, if is column vector
	 */
	boolean isColumnVector();

	/**
	 * Checks whether the Matrix is Empty or not Rows -> 0 Columns -> 0
	 */
	/**
	 * Checks if is empty matrix.
	 *
	 * @param matrix
	 *            the matrix
	 * @return true, if is empty matrix
	 */
	boolean isEmptyMatrix();

}
