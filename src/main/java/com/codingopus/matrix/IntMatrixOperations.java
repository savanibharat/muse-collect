package com.codingopus.matrix;

import java.util.OptionalInt;

import com.codingopus.collect.IntArray;

/**
 * More methods are yet to be added.
 */
public interface IntMatrixOperations extends IntScalarOperations, MatrixFunctions {

	/**
	 * Intermediate operation.
	 * Copy {@code this} IntMatrix to another IntMatrix and return IntMatrix
	 */
	public IntMatrix copy();

	/**
	 * Intermediate operation.
	 * Addition {@code this} IntMatrix with IntMatrix in parameter
	 */
	public IntMatrix add(IntMatrix IntMatrix, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Add elements of all row.
	 * */
	public OptionalInt addRows(ExceptionPredicate pred);
	
	/**
	 * Terminal Operation.
	 * Add elements of specified row.
	 * */
	public OptionalInt addRow(int rowNumber, ExceptionPredicate pred);
	
	/**
	 * Terminal Operation.
	 * Add elements of all row with some value.
	 * */
	public OptionalInt addRows(int factor, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Add elements of all columns.
	 * */
	public OptionalInt addColumns(ExceptionPredicate pred);
	
	/**
	 * Terminal Operation.
	 * Add elements of specified column.
	 * */
	public OptionalInt addColumn(int columnNumber, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Add elements of all column with some value.
	 * */
	public OptionalInt addColumns(int factor, ExceptionPredicate pred);

	/**
	 * Subtract {@code this} IntMatrix with IntMatrix in parameter
	 */
	public IntMatrix subtract(IntMatrix IntMatrix, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Subtract elements of specified row.
	 * */
	public OptionalInt subtractRow(int rowNumber, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Subtract elements of all rows.
	 * */
	public OptionalInt subtractRows(ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Subtract elements of all rows with some value.
	 * */
	public OptionalInt subtractRows(int factor, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Subtract elements of specified column.
	 * */
	public OptionalInt subtractColumn(int columnNumber, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Subtract elements of all column.
	 * */
	public OptionalInt subtractColumns(ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Subtract elements of all columns with some value.
	 * */
	public OptionalInt subtractColumns(int factor, ExceptionPredicate pred);

	/**
	 * Multiply {@code this} IntMatrix with IntMatrix in parameter
	 */
	public IntMatrix multiply(IntMatrix IntMatrix, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Multiply elements of specified row.
	 * */
	public OptionalInt multiplyRow(int rowNumber, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Multiply elements of all rows.
	 * */
	public OptionalInt multiplyRows(ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Multiply elements of all rows with some value.
	 * */
	public OptionalInt multiplyRows(int factor, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Multiply elements of specified column.
	 * */
	public OptionalInt multiplyColumn(int columnNumber, ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Multiply elements of all columns.
	 * */
	public OptionalInt multiplyColumns(ExceptionPredicate pred);

	/**
	 * Terminal Operation.
	 * Multiply elements of all columns with some value.
	 * */
	public OptionalInt multiplyColumns(int factor, ExceptionPredicate pred);

	/**
	 * Intermediate operations.
	 * Dot product of two matrix.
	 * */
	public IntMatrix dotProduct(IntMatrix intMatrix);

	/**
	 * Divide {@code this} IntMatrix with IntMatrix in parameter
	 */
	public IntMatrix divide(IntMatrix IntMatrix);

	/**
	 * @see Math#abs(double)
	 * */
	public IntMatrix abs();

	/**
	 * @see Math#signum(double)
	 * */
	public IntMatrix signum();

	/**
	 * Convert 2D Matrix to 1D matrix.
	 */
	public IntArray flattenMatrix();

	/**
	 * Terminal Operation.
	 * Min value of matrix.
	 * */
	public OptionalInt min();

	/**
	 * Terminal Operation.
	 * Min value of matrix for specified row.
	 * */
	public OptionalInt minRow(int row);

	/**
	 * Terminal Operation.
	 * Min value of matrix for specified column.
	 * */
	public OptionalInt minColumn(int column);

	/**
	 * Terminal Operation.
	 * Max value of matrix.
	 * */
	public OptionalInt max();

	/**
	 * Terminal Operation.
	 * Max value of matrix for specified row.
	 * */
	public OptionalInt maxRow(int row);

	/**
	 * Terminal Operation.
	 * Max value of matrix for specified column.
	 * */
	public OptionalInt maxColumn(int column);

	/**
	 * Terminal operation.
	 * Sort all values and returns new IntMatrix.
	 * */
	public IntMatrix sort();

	/**
	 * Terminal operation.
	 * Sort all values of specified row and returns new IntMatrix.
	 * */
	public IntMatrix sortRow(int row);

	/**
	 * Terminal operation.
	 * Sort all values of specified column and returns new IntMatrix.
	 * */
	public IntMatrix sortColumn(int column);

	/**
	 * max-min
	 */
	public OptionalInt peakToPeak();

}