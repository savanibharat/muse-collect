package com.codingopus.collect;

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
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;

/**
 * Categorize methods in Static factory, intermediate and terminal operations.
 */
public final class DoubleArray {

	private final double[] elementData;

	private static final DoubleArray EMPTY_DOUBLE_ARRAY = new DoubleArray(new double[] {});

	private DoubleArray(final double[] input) {
		this.elementData = input;
	}

	public static DoubleArray of(double a0) {

		return new DoubleArray(new double[] { a0 });
	}

	public static DoubleArray of(double a0, double... an) {

		Objects.requireNonNull(an);
		double[] resultant = new double[1 + an.length];
		resultant[0] = a0;
		int j = 1;
		for (int i = 0; i < an.length; i++) {
			resultant[j++] = an[i];
		}
		return new DoubleArray(resultant);
	}

	public static DoubleArray of(double a[]) {

		Objects.requireNonNull(a, "Input array must not be null.");

		double[] result = new double[a.length];
		System.arraycopy(a, 0, result, 0, a.length);

		return new DoubleArray(result);
	}

	public static DoubleArray of(Collection<? extends Number> c) {

		Objects.requireNonNull(c, "Collection must not be null.");

		if (c.size() == 0) {
			return EMPTY_DOUBLE_ARRAY;
		}

		double[] res = c.stream().mapToDouble(val -> val.doubleValue()).toArray();

		return new DoubleArray(res);
	}

	public static DoubleArray zeros(int length) {
		return ofValue(0d, length);
	}

	public static DoubleArray ones(int length) {
		return ofValue(1d, length);
	}

	public static DoubleArray ofValue(double value, int length) {

		if (length < 0) {
			throw new IllegalArgumentException("Length cannot be negative");
		}

		double[] result = new double[length];
		Arrays.fill(result, value);

		return new DoubleArray(result);
	}

	/**
	 * Appends the specified element to the end of this array.
	 */
	public DoubleArray add(double value) {

		double[] tempArray = new double[elementData.length + 1];
		System.arraycopy(elementData, 0, tempArray, 0, elementData.length);
		tempArray[elementData.length] = value;

		return new DoubleArray(tempArray);
	}

	/**
	 * Inserts the specified element at the specified position in this array.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 */
	public DoubleArray add(int index, double value) {

		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		double[] tempArray = new double[elementData.length + 1];
		System.arraycopy(elementData, 0, tempArray, 0, index);
		tempArray[index] = value;
		System.arraycopy(elementData, index, tempArray, index + 1, elementData.length - index);

		return new DoubleArray(tempArray);
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this array.
	 */
	public DoubleArray addAll(Collection<? extends Number> c) {

		Objects.requireNonNull(c);

		if (c.size() == 0)
			return this;

		double[] tempArray = c.stream().mapToDouble(val -> val.doubleValue()).toArray();
		return addAll(this, DoubleArray.of(tempArray));
	}

	public IntArray toIntArray() {

		Objects.requireNonNull(elementData);

		int[] intArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			intArray[i] = (int) elementData[i];
		}

		return IntArray.of(intArray);
	}

	public LongArray toLongArray() {

		Objects.requireNonNull(elementData);

		long[] longArray = new long[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			longArray[i] = (long) elementData[i];
		}

		return LongArray.of(longArray);
	}

	public List<Double> toList() {

		List<Double> list = new ArrayList<Double>();

		for (int i = 0; i < elementData.length; i++) {
			list.add(Double.valueOf(elementData[i]));
		}

		return Collections.unmodifiableList(list);
	}

	/**
	 * Increments every value by 1.
	 */
	public DoubleArray plusOne() {

		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] + 1;
		}

		return new DoubleArray(tempArray);
	}

	/**
	 * Increments every value by specified factor.
	 */
	public DoubleArray plus(double factor) {

		if (factor == 1)
			return plusOne();

		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] + factor;
		}

		return new DoubleArray(tempArray);
	}

	/**
	 * Adds every value from this array to value in specified array.
	 */
	public DoubleArray plus(DoubleArray DoubleArray) {

		Objects.requireNonNull(DoubleArray, "DoubleArray must not be null.");
		if (elementData.length != DoubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = DoubleArray.toArray();
		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] + secondArray[i];
		}

		return new DoubleArray(tempArray);
	}

	public DoubleArray minus(double factor) {

		if (factor == 0)
			return this;

		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] - factor;
		}

		return new DoubleArray(tempArray);
	}

	public DoubleArray minus(DoubleArray DoubleArray) {

		Objects.requireNonNull(DoubleArray, "DoubleArray must not be null.");
		if (elementData.length != DoubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = DoubleArray.toArray();
		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] - secondArray[i];
		}

		return new DoubleArray(tempArray);
	}

	/**
	 * What if factor is 0? Handle the condition.
	 */
	public DoubleArray multiply(double factor) {

		if (factor == 1)
			return this;

		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] * factor;
		}

		return new DoubleArray(tempArray);
	}

	public DoubleArray multiply(DoubleArray DoubleArray) {

		Objects.requireNonNull(DoubleArray, "DoubleArray must not be null.");
		if (elementData.length != DoubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = DoubleArray.toArray();
		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] * secondArray[i];
		}

		return new DoubleArray(tempArray);
	}

	/**
	 * What if factor is 0? Handle the condition.
	 */
	public DoubleArray divide(double factor) {

		if (factor == 0) {
			throw new ArithmeticException("Division by 0 tends to INFINITY.");
		}

		if (factor == 1)
			return this;

		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] / factor;
		}

		return new DoubleArray(tempArray);
	}

	/**
	 * Handle condition if any value is 0.
	 */
	public DoubleArray divide(DoubleArray DoubleArray) {

		Objects.requireNonNull(DoubleArray, "DoubleArray must not be null.");
		if (elementData.length != DoubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = DoubleArray.toArray();
		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] / secondArray[i];
		}

		return new DoubleArray(tempArray);
	}

	public DoubleArray merge(DoubleArray DoubleArray, DoubleBinaryOperator doubleBinaryOperator) {

		Objects.requireNonNull(DoubleArray);
		Objects.requireNonNull(doubleBinaryOperator);

		if (elementData.length != DoubleArray.toArray().length) {
			throw new IllegalArgumentException("Both arays must be of same size");
		}
		double[] resultant = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			resultant[i] = doubleBinaryOperator.applyAsDouble(elementData[i], DoubleArray.elementData[i]);
		}
		return new DoubleArray(resultant);
	}

	public DoubleArray sort() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		Arrays.sort(elementData);
		return this;
	}

	public DoubleArray distinct() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		double[] distinctElements = Arrays.stream(elementData).distinct().toArray();

		return new DoubleArray(distinctElements);
	}
	
	/**
	 * We are modifing the array. This is allowed as array is final and not
	 * array contents.
	 */
	public DoubleArray reverse() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		int l = elementData.length;
		for (int i = 0; i < l / 2; i++) {
			double temp = elementData[i];
			elementData[i] = elementData[l - i - 1];
			elementData[l - i - 1] = temp;
		}
		return new DoubleArray(elementData);
	}

	public OptionalDouble max() {
		return reduce(Math::max);
	}

	public OptionalDouble min() {
		return reduce(Math::min);
	}

	public OptionalDouble sum() {
		return reduce(Double::sum);
	}

	public OptionalDouble reduce(DoubleBinaryOperator operator) {

		if (elementData.length == 0) {
			return OptionalDouble.empty();
		} else if (elementData.length == 1) {
			return OptionalDouble.of(elementData[0]);
		}

		double result = elementData[0];
		for (int i = 1; i < elementData.length; i++) {
			result = operator.applyAsDouble(result, elementData[i]);
		}
		return OptionalDouble.of(result);
	}

	/**
	 * Take from https://www.mkyong.com/java/java-how-to-join-arrays/
	 */
	public static DoubleArray addAll(double[]... elementData) {

		Objects.requireNonNull(elementData);
		int length = 0;
		for (double[] array : elementData) {
			length += Objects.requireNonNull(array).length;
		}

		double[] result = new double[length];

		int offset = 0;
		for (double[] array : elementData) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}

		return DoubleArray.of(result);
	}

	public static DoubleArray addAll(DoubleArray... elementData) {

		Objects.requireNonNull(elementData);
		int length = 0;
		for (DoubleArray doubleArray : elementData) {
			length += Objects.requireNonNull(doubleArray).toArray().length;
		}

		double[] result = new double[length];

		int offset = 0;
		for (DoubleArray doubleArray : elementData) {
			System.arraycopy(doubleArray.toArray(), 0, result, offset, doubleArray.toArray().length);
			offset += doubleArray.toArray().length;
		}

		return DoubleArray.of(result);
	}

	/**
	 * Removing all elements which makes array length 0;
	 */
	public DoubleArray clear() {
		return new DoubleArray(new double[] {});
	}

	public double[] toArray() {
		return elementData;
	}

	public int size() {
		return elementData.length;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(double value) {

		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i] == value) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(DoubleArray DoubleArray) {

		Objects.requireNonNull(DoubleArray, "DoubleArray must not be null");

		List<Double> resultList = Arrays.stream(elementData).boxed().collect(Collectors.toList());
		List<Double> secondList = Arrays.stream(DoubleArray.toArray()).boxed().collect(Collectors.toList());

		return resultList.containsAll(secondList);
	}

	public boolean containsAll(DoubleArray DoubleArray) {
		return contains(DoubleArray);
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
		DoubleArray other = (DoubleArray) obj;
		if (!Arrays.equals(elementData, other.elementData))
			return false;
		return true;
	}

	public double get(int index) {
		if (index >= size()) {
			throw new IndexOutOfBoundsException("Index " + index + ", Size: " + size());
		}
		return elementData[index];
	}

	public int indexOf(double value) {

		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i] == value) {
				return i;
			}
		}

		return -1;
	}

	public int lastIndexOf(double value) {

		for (int i = elementData.length - 1; i >= 0; i--) {
			if (elementData[i] == value) {
				return i;
			}
		}

		return -1;
	}

	public Iterator<Double> iterator() {
		return new DoubleArrayIterator(elementData);
	}

	public ListIterator<Double> listIterator() {
		return new DoubleArrayListIterator(elementData, 0);
	}

	public ListIterator<Double> listIterator(int index) {

		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}

		return new DoubleArrayListIterator(elementData, index);
	}

	private class DoubleArrayIterator implements Iterator<Double> {

		private final double[] DoubleArray;
		private int cursor;

		private DoubleArrayIterator(double[] DoubleArray) {
			this.DoubleArray = DoubleArray;
			cursor = 0;
		}

		@Override
		public boolean hasNext() {
			return cursor != DoubleArray.length;
		}

		@Override
		public Double next() {
			if (hasNext()) {
				return Double.valueOf(elementData[cursor++]);
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}

	private class DoubleArrayListIterator extends DoubleArrayIterator implements ListIterator<Double> {

		private int cursor;

		private DoubleArrayListIterator(double[] doubleArray, int index) {
			super(doubleArray);
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
		public Double previous() {
			int i = cursor - 1;
			if (i < 0)
				throw new NoSuchElementException();

			double[] DoubleArray = DoubleArray.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i;
			return Double.valueOf(DoubleArray[i]);
		}

		@Override
		public void set(Double e) {
			throw new UnsupportedOperationException("set");
		}

		@Override
		public void add(Double e) {
			throw new UnsupportedOperationException("add");
		}
	}

	@Override
	public String toString() {
		return "DoubleArray [result=" + Arrays.toString(elementData) + "]";
	}

}