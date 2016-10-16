package com.codingopus.matrix;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;

import com.codingopus.collect.DoubleArray;

public class DoubleMatrix extends AbstractMatrixFunctions implements DoubleRealMatrix, DoubleMatrixOperations {

	private final double[][] matrix;
	private final int row;
	private final int col;

	private DoubleMatrix(double[][] doubleMatrix) {
		super(doubleMatrix);
		this.matrix = doubleMatrix;
		this.row = doubleMatrix[0].length;
		this.col = doubleMatrix.length;
	}

	private DoubleMatrix() {
		super();
		this.matrix = new double[0][0];
		this.row = 0;
		this.col = 0;
	}

	public static DoubleMatrix of(int row, int col) {

		double[][] doubleMatrix = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				doubleMatrix[i][j] = Math.abs(new Random().nextDouble());
			}
		}
		return new DoubleMatrix(doubleMatrix);
	}

	public static DoubleMatrix of(double[][] doubleMatrix) {

		double[][] tempMatrix = new double[doubleMatrix.length][doubleMatrix[0].length];
		for (int i = 0; i < doubleMatrix.length; i++) {
			for (int j = 0; j < doubleMatrix[0].length; j++) {
				tempMatrix[i][j] = doubleMatrix[i][j];
			}
		}
		return new DoubleMatrix(tempMatrix);
	}

	@Override
	public DoubleMatrix add(double scalar, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix subtract(double scalar, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix multiply(double scalar, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix add(DoubleMatrix DoubleMatrix, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt addRows(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt addRow(int rowNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt addRows(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt addColumns(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt addColumn(int columnNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt addColumns(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix subtract(DoubleMatrix DoubleMatrix, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractRow(int rowNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractRows(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractRows(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractColumn(int columnNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractColumns(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractColumns(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix multiply(DoubleMatrix DoubleMatrix, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyRow(int rowNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyRows(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyRows(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyColumn(int columnNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyColumns(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyColumns(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix dotProduct(DoubleMatrix DoubleMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix divide(DoubleMatrix DoubleMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleArray flattenMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt minRow(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt minColumn(int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt maxRow(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt maxColumn(int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix sort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix sortRow(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix sortColumn(int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt peakToPeak() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix abs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix signum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSquareMatrix() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRowLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DoubleArray getRowElements(int rowNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleArray getColumnElements(int columnNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowVector() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isColumnVector() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmptyMatrix() {
		// TODO Auto-generated method stub
		return false;
	}

	public double[][] toArray() {
		return matrix;
	}

	@Override
	public String toString() {
		String lineSeparator = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		for (double[] row : matrix) {
			sb.append(Arrays.toString(row)).append(lineSeparator);
		}
		return sb.toString();
	}

}
