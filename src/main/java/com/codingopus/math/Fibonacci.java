package com.codingopus.math;

import java.math.BigInteger;

public final class Fibonacci {

	private Fibonacci() {
	}

	public static BigInteger fibonacci(int n) {

		if (n < 0) {
			throw new IllegalArgumentException("Non-negative number.");
		}
		return generateFibonacci(n);
	}
	
	public static BigInteger fibonacci(long n) {

		if (n < 0) {
			throw new IllegalArgumentException("Non-negative number.");
		}
		return generateFibonacci((int)n);
	}

	private static BigInteger generateFibonacci(int n) {
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		for (int i = 31 - Integer.numberOfLeadingZeros(n); i >= 0; i--) {

			BigInteger d = multiply(a, b.shiftLeft(1).subtract(a));
			BigInteger e = multiply(a, a).add(multiply(b, b));
			a = d;
			b = e;

			if (((n >>> i) & 1) != 0) {
				BigInteger c = a.add(b);
				a = b;
				b = c;
			}
		}
		return a;
	}

	private static BigInteger multiply(BigInteger x, BigInteger y) {
		return x.multiply(y);
	}

}
