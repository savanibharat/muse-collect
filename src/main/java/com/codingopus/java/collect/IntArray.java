package com.codingopus.java.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

/**
 * Categorize methods in Static factory, intermediate and terminal operations.
 */
public final class IntArray {

	private final int[] elementData;

	private static final IntArray EMPTY_INT_ARRAY = new IntArray(new int[] {});

	private IntArray(final int[] input) {
		this.elementData = input;
	}

	public static IntArray of(int a0) {

		return new IntArray(new int[] { a0 });
	}

	public static IntArray of(int a0, int... an) {

		Objects.requireNonNull(an);
		int[] resultant = new int[1 + an.length];
		resultant[0] = a0;
		int j = 1;
		for (int i = 0; i < an.length; i++) {
			resultant[j++] = an[i];
		}
		return new IntArray(resultant);
	}

	public static IntArray of(int a[]) {

		Objects.requireNonNull(a, "Input array must not be null.");

		int[] result = new int[a.length];
		System.arraycopy(a, 0, result, 0, a.length);

		return new IntArray(result);
	}

	public static IntArray of(Collection<? extends Number> c) {

		Objects.requireNonNull(c, "Collection must not be null.");

		if (c.size() == 0) {
			return EMPTY_INT_ARRAY;
		}

		int[] res = c.stream().mapToInt(val -> val.intValue()).toArray();

		return new IntArray(res);
	}

	public static IntArray zeros(int length) {
		return ofValue(0, length);
	}

	public static IntArray ones(int length) {
		return ofValue(1, length);
	}

	public static IntArray ofValue(int value, int length) {

		if (length < 0) {
			throw new IllegalArgumentException("Length cannot be negative");
		}

		int[] result = new int[length];
		Arrays.fill(result, value);

		return new IntArray(result);
	}

	/**
	 * Appends the specified element to the end of this array.
	 */
	public IntArray add(int value) {

		int[] tempArray = new int[elementData.length + 1];
		System.arraycopy(elementData, 0, tempArray, 0, elementData.length);
		tempArray[elementData.length] = value;

		return new IntArray(tempArray);
	}

	/**
	 * Inserts the specified element at the specified position in this array.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 */
	public IntArray add(int index, int value) {

		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		int[] tempArray = new int[elementData.length + 1];
		System.arraycopy(elementData, 0, tempArray, 0, index);
		tempArray[index] = value;
		System.arraycopy(elementData, index, tempArray, index + 1, elementData.length - index);

		return new IntArray(tempArray);
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this array.
	 */
	public IntArray addAll(Collection<? extends Number> c) {

		Objects.requireNonNull(c);

		if (c.size() == 0)
			return this;

		int[] tempArray = c.stream().mapToInt(val -> val.intValue()).toArray();
		return addAll(this, IntArray.of(tempArray));
	}

	public LongArray toLongArray() {

		Objects.requireNonNull(elementData);

		long[] longArray = new long[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			longArray[i] = (long) elementData[i];
		}

		return LongArray.of(longArray);
	}

	public DoubleArray toDoubleArray() {

		Objects.requireNonNull(elementData);

		double[] doubleArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			doubleArray[i] = (double) elementData[i];
		}

		return DoubleArray.of(doubleArray);
	}

	public List<Integer> toList() {

		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < elementData.length; i++) {
			list.add(Integer.valueOf(elementData[i]));
		}

		return Collections.unmodifiableList(list);
	}

	/**
	 * Increments every value by 1.
	 */
	public IntArray plusOne() {

		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] + 1;
		}

		return new IntArray(tempArray);
	}

	/**
	 * Increments every value by specified factor.
	 */
	public IntArray plus(int factor) {

		if (factor == 1)
			return plusOne();

		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] + factor;
		}

		return new IntArray(tempArray);
	}

	/**
	 * Adds every value from this array to value in specified array.
	 */
	public IntArray plus(IntArray intArray) {

		Objects.requireNonNull(intArray, "IntArray must not be null.");
		if (elementData.length != intArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		int[] secondArray = intArray.toArray();
		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] + secondArray[i];
		}

		return new IntArray(tempArray);
	}

	public IntArray minus(int factor) {

		if (factor == 0)
			return this;

		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] - factor;
		}

		return new IntArray(tempArray);
	}

	public IntArray minus(IntArray intArray) {

		Objects.requireNonNull(intArray, "IntArray must not be null.");
		if (elementData.length != intArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		int[] secondArray = intArray.toArray();
		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] - secondArray[i];
		}

		return new IntArray(tempArray);
	}

	/**
	 * What if factor is 0? Handle the condition.
	 */
	public IntArray multiply(int factor) {

		if (factor == 1)
			return this;

		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] * factor;
		}

		return new IntArray(tempArray);
	}

	public IntArray multiply(IntArray intArray) {

		Objects.requireNonNull(intArray, "IntArray must not be null.");
		if (elementData.length != intArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		int[] secondArray = intArray.toArray();
		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] * secondArray[i];
		}

		return new IntArray(tempArray);
	}

	/**
	 * What if factor is 0? Handle the condition.
	 */
	public IntArray divide(int factor) {

		if (factor == 0) {
			throw new ArithmeticException("Division by 0 tends to INFINITY.");
		}

		if (factor == 1)
			return this;

		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] / factor;
		}

		return new IntArray(tempArray);
	}

	/**
	 * Handle condition if any value is 0.
	 */
	public IntArray divide(IntArray intArray) {

		Objects.requireNonNull(intArray, "IntArray must not be null.");
		if (elementData.length != intArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		int[] secondArray = intArray.toArray();
		int[] tempArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] / secondArray[i];
		}

		return new IntArray(tempArray);
	}

	public IntArray merge(IntArray intArray, IntBinaryOperator intBinaryOperator) {

		Objects.requireNonNull(intArray);
		Objects.requireNonNull(intBinaryOperator);

		if (elementData.length != intArray.toArray().length) {
			throw new IllegalArgumentException("Both arays must be of same size");
		}
		int[] resultant = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			resultant[i] = intBinaryOperator.applyAsInt(elementData[i], intArray.elementData[i]);
		}
		return new IntArray(resultant);
	}

	public IntArray sort() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		Arrays.sort(elementData);
		return this;
	}

	public IntArray distinct() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		int[] distinctElements = Arrays.stream(elementData).distinct().toArray();

		return new IntArray(distinctElements);
	}

	/**
	 * We are modifing the array. This is allowed as array is final and not
	 * array contents.
	 */
	public IntArray reverse() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		int l = elementData.length;
		for (int i = 0; i < l / 2; i++) {
			int temp = elementData[i];
			elementData[i] = elementData[l - i - 1];
			elementData[l - i - 1] = temp;
		}
		return new IntArray(elementData);
	}

	public OptionalInt max() {
		return reduce(Math::max);
	}

	public OptionalInt min() {
		return reduce(Math::min);
	}

	public OptionalInt sum() {
		return reduce(Integer::sum);
	}

	public OptionalInt reduce(IntBinaryOperator operator) {

		if (elementData.length == 0) {
			return OptionalInt.empty();
		} else if (elementData.length == 1) {
			return OptionalInt.of(elementData[0]);
		}

		int result = elementData[0];
		for (int i = 1; i < elementData.length; i++) {
			result = operator.applyAsInt(result, elementData[i]);
		}
		return OptionalInt.of(result);
	}

	/**
	 * Take from https://www.mkyong.com/java/java-how-to-join-arrays/
	 */
	public static IntArray addAll(int[]... elementData) {

		Objects.requireNonNull(elementData);
		int length = 0;
		for (int[] array : elementData) {
			length += Objects.requireNonNull(array).length;
		}

		int[] result = new int[length];

		int offset = 0;
		for (int[] array : elementData) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}

		return IntArray.of(result);
	}

	public static IntArray addAll(IntArray... elementData) {

		Objects.requireNonNull(elementData);
		int length = 0;
		for (IntArray intArray : elementData) {
			length += Objects.requireNonNull(intArray).toArray().length;
		}

		int[] result = new int[length];

		int offset = 0;
		for (IntArray intArray : elementData) {
			System.arraycopy(intArray.toArray(), 0, result, offset, intArray.toArray().length);
			offset += intArray.toArray().length;
		}

		return IntArray.of(result);
	}

	/**
	 * Removing all elements which makes array length 0;
	 */
	public IntArray clear() {
		return new IntArray(new int[] {});
	}

	public int[] toArray() {
		return elementData;
	}

	public int size() {
		return elementData.length;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(int value) {

		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i] == value) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(IntArray intArray) {

		Objects.requireNonNull(intArray, "IntArray must not be null");

		List<Integer> resultList = Arrays.stream(elementData).boxed().collect(Collectors.toList());
		List<Integer> secondList = Arrays.stream(intArray.toArray()).boxed().collect(Collectors.toList());

		return resultList.containsAll(secondList);
	}

	public boolean containsAll(IntArray intArray) {
		return contains(intArray);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.elementData);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntArray other = (IntArray) obj;
		if (!Arrays.equals(elementData, other.elementData))
			return false;
		return true;
	}

	public int get(int index) {
		if (index >= size()) {
			throw new IndexOutOfBoundsException("Index " + index + ", Size: " + size());
		}
		return elementData[index];
	}

	public int indexOf(int value) {

		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i] == value) {
				return i;
			}
		}

		return -1;
	}

	public int lastIndexOf(int value) {

		for (int i = elementData.length - 1; i >= 0; i--) {
			if (elementData[i] == value) {
				return i;
			}
		}

		return -1;
	}

	public Iterator<Integer> iterator() {
		return new IntArrayIterator(elementData);
	}

	public ListIterator<Integer> listIterator() {
		return new IntArrayListIterator(elementData, 0);
	}

	public ListIterator<Integer> listIterator(int index) {

		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}

		return new IntArrayListIterator(elementData, index);
	}

	private class IntArrayIterator implements Iterator<Integer> {

		private final int[] intArray;
		private int cursor;

		private IntArrayIterator(int[] intArray) {
			this.intArray = intArray;
			cursor = 0;
		}

		@Override
		public boolean hasNext() {
			return cursor != intArray.length;
		}

		@Override
		public Integer next() {
			if (hasNext()) {
				return Integer.valueOf(elementData[cursor++]);
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}

	private class IntArrayListIterator extends IntArrayIterator implements ListIterator<Integer> {

		private int cursor;

		private IntArrayListIterator(int[] intArray, int index) {
			super(intArray);
			cursor = index;
		}

		public boolean hasPrevious() {
			return cursor != 0;
		}

		public int nextIndex() {
			return cursor;
		}

		public int previousIndex() {
			return cursor - 1;
		}

		@Override
		public Integer previous() {
			int i = cursor - 1;
			if (i < 0)
				throw new NoSuchElementException();

			int[] intArray = IntArray.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i;
			return Integer.valueOf(intArray[i]);
		}

		@Override
		public void set(Integer e) {
			throw new UnsupportedOperationException("set");
		}

		@Override
		public void add(Integer e) {
			throw new UnsupportedOperationException("add");
		}
	}

	@Override
	public String toString() {
		return "IntArray [result=" + Arrays.toString(elementData) + "]";
	}

}