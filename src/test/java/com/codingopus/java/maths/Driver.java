package com.codingopus.java.maths;

import java.util.Arrays;
import java.util.Random;

import com.codingopus.collect.IntArray;

public class Driver {

	public static void main(String[] args) {

		int[] arr = new int[10000];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt();
		}
		long start = System.currentTimeMillis();
		IntArray.of(arr).plusOne(() -> true);
		System.out.println(System.currentTimeMillis() - start);

		start = -99999999;
		start = System.currentTimeMillis();
		System.out.println("start "+start);
		Arrays.stream(arr).map(val -> Math.addExact(val, 1)).toArray();
		System.out.println(System.currentTimeMillis() - start);
	}

}
