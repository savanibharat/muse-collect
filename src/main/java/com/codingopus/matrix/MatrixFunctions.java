package com.codingopus.matrix;

public interface MatrixFunctions {

	public DoubleMatrix sin();

	public DoubleMatrix sinh();

	public DoubleMatrix arcsin();

	public DoubleMatrix cos();

	public DoubleMatrix cosh();

	public DoubleMatrix arccos();

	public DoubleMatrix tan();

	public DoubleMatrix tanh();

	public DoubleMatrix arctan();

	public DoubleMatrix toRadians();

	public DoubleMatrix toDegrees();

	public DoubleMatrix exp();

	public DoubleMatrix log();

	public DoubleMatrix log10();

	public DoubleMatrix log1p();

	public DoubleMatrix sqrt();

	public DoubleMatrix cbrt();

	public DoubleMatrix ceil();// IntMatrix will return "this"

	public DoubleMatrix floor();// IntMatrix will return "this"

	public DoubleMatrix rint();

	public DoubleMatrix pow(double exp);

	public DoubleMatrix round();// IntMatrix will return "this"
}
