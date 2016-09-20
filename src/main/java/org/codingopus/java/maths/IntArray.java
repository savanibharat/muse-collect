package org.codingopus.java.maths;

public final class IntArray {

	private final int[] result;

	private IntArray(final int[] input) {
		this.result = new int[input.length];
		System.arraycopy(input, 0, result, 0, input.length);
	}

	public static IntArray of(
			int a1) {
		return new IntArray(new int[] { a1 });
	}
	
	public static IntArray of(
			int a1, int a2) {
		return new IntArray(new int[] { a1, a2 });
	}
	
	public static IntArray of(
			int a1, int a2, int a3) {
		return new IntArray(new int[] { a1, a2, a3});
	}
	
	public static IntArray of(
			int a1, int a2, int a3, int a4) {
		return new IntArray(new int[] { a1, a2, a3, a4 });
	}

}
