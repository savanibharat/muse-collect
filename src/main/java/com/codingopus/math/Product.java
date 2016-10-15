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

}
