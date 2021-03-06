package com.codingopus.matrix;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;

import com.codingopus.collect.IntArray;

public class IntMatrix extends AbstractMatrixFunctions implements IntRealMatrix, IntMatrixOperations {

	private int row;
	private int column;
	private int[][] matrix;

	private IntMatrix(int[][] intMatrix) {
		super(intMatrix);
		this.matrix = intMatrix;
		this.row = intMatrix.length;
		this.column = intMatrix[0].length;
	}

	private IntMatrix() {
		super();
		this.matrix = new int[0][0];
		this.row = 0;
		this.column = 0;
	}

	public static IntMatrix of(int row, int col) {

		int[][] intMatrix = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				intMatrix[i][j] = Math.abs(new Random().nextInt(50));
			}
		}
		return new IntMatrix(intMatrix);
	}

	public static IntMatrix of(int[][] matrix) {
		int[][] tempMatrix = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				tempMatrix[i][j] = matrix[i][j];
			}
		}
		return new IntMatrix(tempMatrix);
	}

	@Override
	public IntMatrix add(int scalar, ExceptionPredicate pred) {
		if (pred.throwException()) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					matrix[i][j] = Math.addExact(matrix[i][j], scalar);
				}
			}
			return new IntMatrix(matrix);
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = matrix[i][j] + scalar;
			}
		}
		return new IntMatrix(matrix);
	}

	@Override
	public IntMatrix subtract(int scalar, ExceptionPredicate pred) {
		if (pred.throwException()) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					matrix[i][j] = Math.subtractExact(matrix[i][j], scalar);
				}
			}
			return new IntMatrix(matrix);
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = matrix[i][j] - scalar;
			}
		}
		return new IntMatrix(matrix);
	}

	@Override
	public IntMatrix multiply(int scalar, ExceptionPredicate pred) {
		if (pred.throwException()) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					matrix[i][j] = Math.multiplyExact(matrix[i][j], scalar);
				}
			}
			return new IntMatrix(matrix);
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = matrix[i][j] * scalar;
			}
		}
		return new IntMatrix(matrix);
	}

	/**
	 * We have deep copy of matrix with us. No need to copy it again.
	 */
	@Override
	public IntMatrix copy() {
		return new IntMatrix(matrix);
	}

	@Override
	public IntMatrix add(IntMatrix intMatrix, ExceptionPredicate pred) {
		checkDimensions(intMatrix);

		int[][] intMatrixArray = intMatrix.toArray();
		int[][] tempMatrix = new int[row][column];
		if (pred.throwException()) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					tempMatrix[i][j] = Math.addExact(this.matrix[i][j], intMatrixArray[i][j]);
				}
			}
			return new IntMatrix(tempMatrix);
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				tempMatrix[i][j] = this.matrix[i][j] + intMatrixArray[i][j];
			}
		}
		return new IntMatrix(tempMatrix);
	}

	private void checkDimensions(IntMatrix intMatrix) {
		if (this.getRowLength() != intMatrix.getRowLength() || this.getColumnLength() != intMatrix.getColumnLength()) {
			StringBuilder sb = new StringBuilder();
			sb.append("matrix1 row: ").append(this.getRowLength());
			sb.append("matrix1 col: ").append(this.getColumnLength());
			sb.append("matrix2 row: ").append(intMatrix.getRowLength());
			sb.append("matrix2 col: ").append(intMatrix.getColumnLength());
			throw new IllegalArgumentException(sb.toString());
		}
	}

	@Override
	public IntMatrix subtract(IntMatrix intMatrix, ExceptionPredicate pred) {
		checkDimensions(intMatrix);

		int[][] intMatrixArray = intMatrix.toArray();
		int[][] tempMatrix = new int[row][column];
		if (pred.throwException()) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					tempMatrix[i][j] = Math.subtractExact(this.matrix[i][j], intMatrixArray[i][j]);
				}
			}
			return new IntMatrix(tempMatrix);
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				tempMatrix[i][j] = this.matrix[i][j] - intMatrixArray[i][j];
			}
		}
		return new IntMatrix(tempMatrix);
	}

	@Override
	public IntMatrix multiply(IntMatrix intMatrix, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntMatrix divide(IntMatrix intMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntMatrix abs() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = Math.abs(matrix[i][j]);
			}
		}
		return IntMatrix.of(matrix);
	}

	@Override
	public IntMatrix signum() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = (int) Math.signum((float) matrix[i][j]);
			}
		}
		return IntMatrix.of(matrix);
	}

	@Override
	public boolean isSquareMatrix() {
		return row == column;
	}

	@Override
	public int getRowLength() {
		return row;
	}

	@Override
	public int getColumnLength() {
		return column;
	}

	/**
	 * matrix[rowNumber][col1] matrix[rowNumber][col2] matrix[rowNumber][col3]
	 * matrix[rowNumber][col4]
	 */
	@Override
	public IntArray getRowElements(int rowNumber) {
		checkForRow(rowNumber);
		int[] rows = new int[getRowLength()];
		for (int i = 0; i < column; i++) {
			rows[i] = matrix[rowNumber - 1][i];
		}
		return IntArray.of(rows);
	}

	private void checkForRow(int rowNumber) {
		if (rowNumber <= 0 || rowNumber >= (row + 1)) {
			throw new IllegalArgumentException("row is greater than " + this.row);
		}
	}

	@Override
	public IntArray getColumnElements(int columnNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowVector() {
		return row == 1;
	}

	@Override
	public boolean isColumnVector() {
		return column == 1;
	}

	@Override
	public boolean isEmptyMatrix() {
		return (row == 0 && column == 0);
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
	public OptionalInt subtractRows(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractRow(int rowNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractRows(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractColumns(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractColumn(int columnNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt subtractColumns(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyRows(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyRow(int rowNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyRows(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyColumns(ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyColumn(int columnNumber, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt multiplyColumns(int factor, ExceptionPredicate pred) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntMatrix dotProduct(IntMatrix intMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntArray flattenMatrix() {
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
	public IntMatrix sort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntMatrix sortRow(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntMatrix sortColumn(int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt peakToPeak() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[][] toArray() {
		return matrix;
	}

	@Override
	public String toString() {
		String lineSeparator = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		for (int[] row : matrix) {
			sb.append(Arrays.toString(row)).append(lineSeparator);
		}
		return sb.toString();
	}

}
