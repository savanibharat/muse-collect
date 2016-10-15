package com.codingopus.matrix;

import java.util.Arrays;
import java.util.Random;

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
	public DoubleMatrix add(double scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix addUnsafe(double scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix subtract(double scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix subtractUnsafe(double scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix multiply(double scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix multiplyUnsafe(double scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix add(DoubleMatrix doubleMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix subtract(DoubleMatrix doubleMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix multiply(DoubleMatrix doubleMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix divide(DoubleMatrix doubleMatrix) {
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
	public double[] getRowElements(int rowNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getColumnElements(int columnNumber) {
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
