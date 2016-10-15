package com.codingopus.matrix;

public interface IntScalarOperations {

	IntMatrix add(int scalar, ExceptionPredicate pred);

	IntMatrix subtract(int scalar, ExceptionPredicate pred);

	IntMatrix multiply(int scalar, ExceptionPredicate pred);

}
