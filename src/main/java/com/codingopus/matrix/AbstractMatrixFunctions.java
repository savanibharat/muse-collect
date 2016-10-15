package com.codingopus.matrix;

public abstract class AbstractMatrixFunctions implements MatrixFunctions {

	private final double[][] matrix;
	private final int row;
	private final int col;

	protected AbstractMatrixFunctions() {
		this.matrix = new double[0][0];
		this.row = 0;
		this.col = 0;
	}

	protected AbstractMatrixFunctions(double[][] doubleMatrix) {
		this.row = doubleMatrix.length;
		this.col = doubleMatrix[0].length;
		this.matrix = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = doubleMatrix[i][j];
			}
		}
	}

	protected AbstractMatrixFunctions(int[][] intMatrix) {
		this.row = intMatrix.length;
		this.col = intMatrix[0].length;
		this.matrix = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = (double) intMatrix[i][j];
			}
		}
	}

	@Override
	public DoubleMatrix sin() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.sin(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix sinh() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.sinh(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix arcsin() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.asin(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix cos() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.cos(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix cosh() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.cosh(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix arccos() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.acos(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix tan() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.tan(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix tanh() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.tanh(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix arctan() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.atan(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix toRadians() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.toRadians(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix toDegrees() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.toDegrees(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix exp() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.exp(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix log() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.log(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix log10() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.log10(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix log1p() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.log1p(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix sqrt() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.sqrt(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix cbrt() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.cbrt(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix ceil() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.ceil(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix floor() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.floor(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix rint() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.rint(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix pow(double exp) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.pow(matrix[i][j], exp);
			}
		}
		return DoubleMatrix.of(matrix);
	}

	@Override
	public DoubleMatrix round() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Math.round(matrix[i][j]);
			}
		}
		return DoubleMatrix.of(matrix);
	}

}
