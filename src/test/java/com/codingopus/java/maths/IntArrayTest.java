package com.codingopus.java.maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import com.codingopus.collect.IntArray;

import static org.testng.internal.junit.ArrayAsserts.*;

public class IntArrayTest {

	private final IntArray intArray0 = IntArray.of(new int[] {});
	private final IntArray intArray1 = IntArray.of(1);
	private final IntArray intArray2 = IntArray.of(1, 2);
	private final IntArray intArray3 = IntArray.of(1, 2, 3);
	private final IntArray intArray4 = IntArray.of(1, 2, 3, 4);
	private final IntArray intArrayN = IntArray.of(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });

	@Test
	public void intArray_size_check() {
		assertEquals(0, intArray0.size());
		assertEquals(1, intArray1.size());
		assertEquals(2, intArray2.size());
		assertEquals(3, intArray3.size());
		assertEquals(4, intArray4.size());
		assertEquals(8, intArrayN.size());
	}

	@Test
	public void intArray_VALUE_check() {
		assertArrayEquals(new int[] {}, intArray0.toArray());
		assertArrayEquals(new int[] { 1 }, intArray1.toArray());
		assertArrayEquals(new int[] { 1, 2 }, intArray2.toArray());
		assertArrayEquals(new int[] { 1, 2, 3 }, intArray3.toArray());
		assertArrayEquals(new int[] { 1, 2, 3, 4 }, intArray4.toArray());
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, intArrayN.toArray());
	}

	@Test
	public void intArray_zeros_check() {
		IntArray values = IntArray.zeros(10);
		assertArrayEquals(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, values.toArray());
	}

	@Test
	public void intArray_ones_check() {
		IntArray values = IntArray.ones(10);
		assertArrayEquals(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, values.toArray());
	}

	@Test
	public void intArray_ofValue_check() {
		IntArray values = IntArray.ofValue(-1, 10);
		assertArrayEquals(new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, values.toArray());
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void intArray_varargs_check() {
		IntArray.of(1, null);
	}

	@Test
	public void intArray_varargs_check_1() {
		int size = IntArray.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).size();
		assertEquals(10, size);
	}

	@Test
	public void intArray_collection() {

		List<Integer> number = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

		assertArrayEquals(intArray4.toArray(), IntArray.of(number).toArray());
	}

	@Test
	public void intArray_add() {

		IntArray intArray = IntArray.of(4, 5, 6).add(7);

		assertArrayEquals(new int[] { 4, 5, 6, 7 }, intArray.toArray());
	}

	@Test
	public void intArray_add_with_index() {

		IntArray intArray1 = IntArray.of(4, 5, 6).add(2, 7);

		assertArrayEquals(new int[] { 4, 5, 7, 6 }, intArray1.toArray());

		IntArray intArray2 = IntArray.of(4, 5, 6).add(0, 7);

		assertArrayEquals(new int[] { 7, 4, 5, 6 }, intArray2.toArray());

		IntArray intArray3 = IntArray.of(4, 5, 6).add(3, 7);

		assertArrayEquals(new int[] { 4, 5, 6, 7 }, intArray3.toArray());
	}

	@Test
	public void intArray_addAll() {

		IntArray intArray1 = IntArray.of(1, 2, 3, 4).addAll(Arrays.asList(45, 123, 78));
		assertArrayEquals(new int[] { 1, 2, 3, 4, 45, 123, 78 }, intArray1.toArray());

		IntArray intArray2 = IntArray.of(1, 2, 3, 4).addAll(Arrays.asList());
		assertArrayEquals(new int[] { 1, 2, 3, 4 }, intArray2.toArray());
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void intArray_addAll_with_null() {
		IntArray.of(1, 2, 3, 4).addAll(Arrays.asList(45, null, 78));
	}

	@Test
	public void intArray_plusAll() {
		IntArray intArray = IntArray.of(1, 2, 3, 4).plusOne();
		assertArrayEquals(new int[] { 2, 3, 4, 5 }, intArray.toArray());
	}

	@Test
	public void intArray_plus_with_factor() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4).plus(10);
		assertArrayEquals(new int[] { 11, 12, 13, 14 }, intArray1.toArray());

		IntArray intArray2 = IntArray.of(1, 2, 3, 4).plus(-3);
		assertArrayEquals(new int[] { -2, -1, 0, 1 }, intArray2.toArray());
	}

	@Test
	public void intArray_plus_with_intarray_with_same_length() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		IntArray intArray2 = IntArray.of(5, 6, 7, 8);

		IntArray result = intArray1.plus(intArray2);
		assertArrayEquals(new int[] { 6, 8, 10, 12 }, result.toArray());

	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void intArray_plus_with_intarray_with_different_length() {
		IntArray intArrayShortedLength = IntArray.of(1, 2, 3);
		intArray1.plus(intArrayShortedLength);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void intArray_plus_with_intarray_with_null() {
		IntArray.of(1, 2, 3).plus(null);
	}

	@Test
	public void intArray_minus_with_factor() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4).minus(10);
		assertArrayEquals(new int[] { -9, -8, -7, -6 }, intArray1.toArray());

		IntArray intArray2 = IntArray.of(1, 2, 3, 4).minus(-3);
		assertArrayEquals(new int[] { 4, 5, 6, 7 }, intArray2.toArray());
	}

	@Test
	public void intArray_minus_with_intarray_with_same_length() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		IntArray intArray2 = IntArray.of(5, 6, 7, 8);

		IntArray result = intArray1.minus(intArray2);
		assertArrayEquals(new int[] { -4, -4, -4, -4 }, result.toArray());

	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void intArray_minus_with_intarray_with_different_length() {
		IntArray intArrayShortedLength = IntArray.of(1, 2, 3);
		intArray1.minus(intArrayShortedLength);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void intArray_minus_with_intarray_with_null() {
		IntArray.of(1, 2, 3).minus(null);
	}

	@Test
	public void intArray_multiply_with_factor() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4).multiply(10);
		assertArrayEquals(new int[] { 10, 20, 30, 40 }, intArray1.toArray());

		IntArray intArray2 = IntArray.of(1, 2, 3, 4).multiply(-3);
		assertArrayEquals(new int[] { -3, -6, -9, -12 }, intArray2.toArray());
	}

	@Test
	public void intArray_multiply_with_intarray_with_same_length() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		IntArray intArray2 = IntArray.of(5, 6, 7, 8);

		IntArray result = intArray1.multiply(intArray2);
		assertArrayEquals(new int[] { 5, 12, 21, 32 }, result.toArray());

	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void intArray_multiply_with_intarray_with_different_length() {
		IntArray intArrayShortedLength = IntArray.of(1, 2, 3);
		intArray1.multiply(intArrayShortedLength);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void intArray_multiply_with_intarray_with_null() {
		IntArray.of(1, 2, 3).multiply(null);
	}

	@Test
	public void intArray_divide_with_factor() {
		IntArray intArray1 = IntArray.of(10, 20, 30, 45).divide(10);
		assertArrayEquals(new int[] { 1, 2, 3, 4 }, intArray1.toArray());

		IntArray intArray2 = IntArray.of(11, 12, 13, 14).divide(-3);
		assertArrayEquals(new int[] { -3, -4, -4, -4 }, intArray2.toArray());
	}

	@Test
	public void intArray_divide_with_intarray_with_same_length() {
		IntArray intArray1 = IntArray.of(11, 12, 33, 14);
		IntArray intArray2 = IntArray.of(5, 6, 7, 8);

		IntArray result = intArray1.divide(intArray2);
		assertArrayEquals(new int[] { 2, 2, 4, 1 }, result.toArray());

	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void intArray_divide_with_intarray_with_different_length() {
		IntArray intArrayShortedLength = IntArray.of(1, 2, 3);
		intArray1.divide(intArrayShortedLength);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void intArray_divide_with_intarray_with_null() {
		IntArray.of(1, 2, 3).divide(null);
	}

	@Test
	public void intArray_clear() {
		IntArray intArray = IntArray.of(1, 2, 3, 4).clear();
		assertEquals(0, intArray.size());
	}

	@Test
	public void intArray_toArray() {
		IntArray intArray = IntArray.of(1, 2, 3, 4);
		assertArrayEquals(new int[] { 1, 2, 3, 4 }, intArray.toArray());
	}

	@Test
	public void intArray_size() {
		IntArray intArray = IntArray.of(1, 2, 3, 4);
		assertEquals(4, intArray.size());
	}

	@Test
	public void intArray_isEmpty() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		assertFalse(intArray1.isEmpty());

		IntArray intArray2 = IntArray.of(1, 2, 3, 4).clear();
		assertTrue(intArray2.isEmpty());
	}

	@Test
	public void intArray_contains_value() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		assertFalse(intArray1.contains(56));

		IntArray intArray2 = IntArray.of(1, 2, 3, 4);
		assertTrue(intArray2.contains(2));
	}

	@Test
	public void intArray_contains_intArray() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		IntArray intArray2 = IntArray.of(1, 2, 3, 4);
		assertTrue(intArray1.contains(intArray2));

		// 1 contains all elements of 3.
		IntArray intArray3 = IntArray.of(1, 2, 3);
		assertTrue(intArray1.contains(intArray3));

		// 3 does not contain all elements of 1.
		assertFalse(intArray3.contains(intArray1));
	}

	@Test
	public void intArray_hashcode_equals() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		IntArray intArray2 = IntArray.of(1, 2, 3, 4);
		assertTrue(intArray1.hashCode() == intArray2.hashCode());
		assertTrue(intArray1.equals(intArray2));

		IntArray intArray3 = IntArray.of(1, 2, 3);
		assertFalse(intArray1.hashCode() == intArray3.hashCode());
		assertFalse(intArray1.equals(intArray3));
	}

	@Test
	public void intArray_get() {
		IntArray intArray1 = IntArray.of(1, 2, 3, 4);
		assertEquals(3, intArray1.get(2));
	}

	@Test(expectedExceptions = IndexOutOfBoundsException.class)
	public void intArray_get_exception() {
		IntArray.of(1, 2, 3, 4).get(100);
	}

	@Test
	public void intArray_indexOf() {
		IntArray intArray1 = IntArray.of(10, 20, 30, 40);
		assertEquals(3, intArray1.indexOf(40));
		assertEquals(-1, intArray1.indexOf(400));
	}

	@Test
	public void intArray_lastIndexOf() {
		IntArray intArray1 = IntArray.of(10, 20, 30, 40);
		assertEquals(3, intArray1.lastIndexOf(40));

		IntArray intArray2 = IntArray.of(new int[] { 10, 20, 30, 40, 40 });
		assertEquals(4, intArray2.lastIndexOf(40));
	}

	@Test
	public void intArray_sort() {
		IntArray values = IntArray.of(110, 20, 310, 89).sort();
		assertArrayEquals(new int[] { 20, 89, 110, 310 }, values.toArray());
	}

	@Test
	public void intArray_distinct() {
		IntArray values = IntArray.of(7, 6, 3, 4, 5, 4, 5).distinct();
		assertArrayEquals(new int[] { 7, 6, 3, 4, 5 }, values.toArray());
	}

	@Test
	public void intArray_reverse() {
		IntArray values = IntArray.of(1, 2, 3, 4, 5).reverse();
		assertArrayEquals(new int[] { 5, 4, 3, 2, 1 }, values.toArray());
	}

	@Test
	public void intArray_max() {
		OptionalInt max = IntArray.of(110, 20, 310, 89).max();
		assertEquals(OptionalInt.of(310), max);
	}

	@Test
	public void intArray_min() {
		OptionalInt min = IntArray.of(110, 20, 310, 89).min();
		assertEquals(OptionalInt.of(20), min);
	}

	@Test
	public void intArray_sum() {
		OptionalInt sum = IntArray.of(1, 2, 3, 4, 5).sum();
		assertEquals(OptionalInt.of(15), sum);
	}

	@Test
	public void intArray_addAll_arrays() {
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 11, 12, 13 };
		int[] arr3 = { 21, 22, 23 };
		IntArray values = IntArray.of(arr1, arr2, arr3);
		assertArrayEquals(new int[] { 1, 2, 3, 11, 12, 13, 21, 22, 23 }, values.toArray());
	}

	@Test
	public void intArray_addAll_intarray() {
		IntArray values1 = IntArray.of(new int[] { 1, 2, 3 });
		IntArray values2 = IntArray.of(new int[] { 11, 12, 13 });
		IntArray values3 = IntArray.of(new int[] { 21, 22 });
		IntArray values = IntArray.of(values1, values2, values3);
		assertArrayEquals(new int[] { 1, 2, 3, 11, 12, 13, 21, 22 }, values.toArray());
	}

	@Test
	public void intArray_add_3arrays_distict() {
		int[] arr1 = { 1, 1, 2, 2, 3, 7 };
		int[] arr2 = { 2, 3, 4, 5 };

		IntArray intArray1 = IntArray.of(arr1).distinct();
		IntArray intArray2 = IntArray.of(arr2).distinct();
		IntArray intArray = intArray1.plus(intArray2);

		assertArrayEquals(new int[] { 3, 5, 7, 12 }, intArray.toArray());

	}

	@Test
	public void intArray_distinct_sorted_add_reverse() {

		IntArray intArray1 = IntArray.of(10, 5, 5, 2, 2, 8);
		IntArray intArray2 = IntArray.of(1, 2, 3, 4);

		// distinct, sorted, add and reverse.
		IntArray result = intArray1.distinct().sort().plus(intArray2).reverse();
		assertArrayEquals(new int[] { 14, 11, 7, 3 }, result.toArray());
	}

	@Test
	public void intArray_evens() {

		IntArray intArray1 = IntArray.of(10, 5, 5, 2, 2, 8).evens();
		System.out.println(intArray1);

		IntArray intArray2 = IntArray.of(10, 5, 5, 2, 2, 8).odds();
		System.out.println(intArray2);
	}

	@Test
	public void intArray_shuffle() {
		IntArray intArray = IntArray.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		System.out.println(intArray.shuffle());
	}
}
