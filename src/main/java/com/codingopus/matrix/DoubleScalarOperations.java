package com.codingopus.matrix;

public interface DoubleScalarOperations {

	DoubleMatrix add(double scalar);

	DoubleMatrix addUnsafe(double scalar);

	DoubleMatrix subtract(double scalar);

	DoubleMatrix subtractUnsafe(double scalar);

	DoubleMatrix multiply(double scalar);

	DoubleMatrix multiplyUnsafe(double scalar);

}
