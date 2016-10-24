package com.codingopus.math;

public final class Product {

	private Product() {
	}

	public static int multiply(int a, int b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		return a * b;
	}
	
	public static long multiply(long a, long b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		return a * b;
	}

	public static double multiply(double a, double b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		return a * b;
	}

}
