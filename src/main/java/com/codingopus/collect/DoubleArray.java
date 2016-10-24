package com.codingopus.collect;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import com.codingopus.math.Product;

/**
 * Categorize methods in Static factory, intermediate and terminal operations.
 */
public final class DoubleArray {

	private final double[] elementData;

	private static final DoubleArray EMPTY_DOUBLE_ARRAY = new DoubleArray(new double[] {});
	private static final double[] EMPTY_ARRAY = new double[] {};

	private static final Random random = new Random();

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

	public static DoubleArray random(int size) {

		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}

		else if (size == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementData = new double[size];
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = random.nextDouble();
		}

		return new DoubleArray(elementData);
	}

	public static DoubleArray random(int size, Random random) {

		Objects.requireNonNull(random);

		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}

		else if (size == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementData = new double[size];
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = random.nextInt();
		}

		return new DoubleArray(elementData);
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
		return new DoubleArray(tempArray);
	}

	public LongArray toLongArray() {

		Objects.requireNonNull(elementData);

		long[] longArray = new long[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			longArray[i] = (long) elementData[i];
		}

		return LongArray.of(longArray);
	}

	public IntArray toIntArray() {

		Objects.requireNonNull(elementData);

		int[] intArray = new int[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			intArray[i] = (int) elementData[i];
		}

		return IntArray.of(intArray);
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

		double[] result = DoubleStream.of(elementData).map(val -> Double.sum(val, 1d)).toArray();
		return new DoubleArray(result);
	}

	/**
	 * Increments every value by specified factor.
	 */
	public DoubleArray plus(int factor) {

		double[] result = DoubleStream.of(elementData).map(val -> Double.sum(val, factor)).toArray();
		return new DoubleArray(result);
	}

	/**
	 * Adds every value from this array to value in specified array.
	 */
	public DoubleArray plus(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray, "DoubleArray must not be null.");

		if (elementData.length != doubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = doubleArray.toArray();
		double[] tempArray = new double[elementData.length];

		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = Double.sum(elementData[i], secondArray[i]);
		}
		return new DoubleArray(tempArray);
	}

	public DoubleArray minus(double factor) {

		double[] result = Arrays.stream(elementData).map(val -> val - factor).toArray();
		return new DoubleArray(result);
	}

	public DoubleArray minus(DoubleArray doubleArray, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(doubleArray, "DoubleArray must not be null.");

		if (elementData.length != doubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = doubleArray.toArray();
		double[] tempArray = new double[elementData.length];

		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] - secondArray[i];
		}
		return new DoubleArray(tempArray);
	}

	/**
	 * What if factor is 0? Handle the condition.
	 */
	public DoubleArray multiply(int factor, ExceptionPredicate exceptionPredicate) {

		if (factor == 1) {
			return this;
		}

		double[] result = Arrays.stream(elementData).map(val -> val * factor).toArray();
		return new DoubleArray(result);
	}

	public DoubleArray multiply(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray, "DoubleArray must not be null.");

		if (elementData.length != doubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = doubleArray.toArray();
		double[] tempArray = new double[elementData.length];

		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] * secondArray[i];
		}
		return new DoubleArray(tempArray);

	}

	public OptionalDouble dotProduct(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray, "DoubleArray must not be null.");

		if (elementData.length != doubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		} else if (elementData.length == 0 && doubleArray.size() == 0) {
			return OptionalDouble.empty();
		}

		double[] secondArray = doubleArray.toArray();
		double sum = 0;
		for (int i = 0; i < elementData.length; i++) {
			sum = sum + Product.multiply(elementData[i], secondArray[i]);
		}
		return OptionalDouble.of(sum);
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
	public DoubleArray divide(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray, "DoubleArray must not be null.");
		if (elementData.length != doubleArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		double[] secondArray = doubleArray.toArray();
		double[] tempArray = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			if (secondArray[i] == 0) {
				throw new ArithmeticException("Division by 0 tends to INFINITY.");
			}
			tempArray[i] = elementData[i] / secondArray[i];
		}

		return new DoubleArray(tempArray);
	}

	public DoubleArray sin() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.sin(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray arcsin() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.asin(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray sinh() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.sinh(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray cos() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.cos(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray arccos() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.acos(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray cosh() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.cosh(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray tan() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.tan(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray arctan() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.atan(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray tanh() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.tanh(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray toRadians() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.toRadians(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray toDegrees() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.toDegrees(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray log() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.log(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray log10() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.log10(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray log1p() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.log1p(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray sqrt() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.sqrt(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray cbrt() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.cbrt(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray ceil() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.ceil(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray floor() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.floor(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray rint() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.rint(val)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray pow(double exp) {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).map(val -> Math.pow(val, exp)).toArray();
		return new DoubleArray(elementDataSin);
	}

	public DoubleArray exp() {
		if (elementData.length == 0) {
			return new DoubleArray(EMPTY_ARRAY);
		}

		double[] elementDataExp = Arrays.stream(elementData).map(val -> Math.exp(val)).toArray();
		return new DoubleArray(elementDataExp);
	}

	public DoubleArray abs() {
		if (elementData.length == 0) {
			return this;
		}

		double[] elementDataAbs = Arrays.stream(elementData).map(val -> Math.abs(val)).toArray();
		return new DoubleArray(elementDataAbs);
	}

	// TODO m should be double. Use BigDecimal for mod.
	public DoubleArray mod(int m) {
		if (elementData.length == 0) {
			return this;
		}
		BigInteger mod = BigInteger.valueOf((long) m);
		for (int i = 0; i < elementData.length; i++) {
			BigInteger bg1 = BigInteger.valueOf((long) elementData[i]);
			elementData[i] = bg1.mod(mod).intValue();
		}
		return new DoubleArray(elementData);

	}

	public DoubleArray union(DoubleArray doubleArray) {
		Objects.requireNonNull(doubleArray);
		if (elementData.length == 0 && doubleArray.size() == 0) {
			return this;
		} else if (elementData.length != 0 && doubleArray.size() == 0) {
			return this;
		} else if (elementData.length == 0 && doubleArray.size() != 0) {
			return doubleArray;
		}

		// both IntArray's has length > 1.
		Set<Double> set = new HashSet<>(this.toList());
		set.addAll(doubleArray.toList());
		return DoubleArray.of(set);
	}

	public DoubleArray intersection(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray);
		if (elementData.length == 0 || doubleArray.size() == 0) {
			return EMPTY_DOUBLE_ARRAY;
		}

		// both IntArray's has length > 1.
		Set<Double> set = new HashSet<>(this.toList());
		set.retainAll(doubleArray.toList());
		return DoubleArray.of(set);
	}

	/**
	 * https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html
	 */
	public DoubleArray asymmetricDifference(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray);
		if (elementData.length == 0 && doubleArray.size() == 0) {
			return EMPTY_DOUBLE_ARRAY;
		} else if (elementData.length != 0 && doubleArray.size() == 0) {
			return this;
		} else if (elementData.length == 0 && doubleArray.size() != 0) {
			return this;
		}

		// both IntArray's has length > 1.
		Set<Double> set = new HashSet<>(this.toList());
		set.removeAll(doubleArray.toList());
		return DoubleArray.of(set);
	}

	/**
	 * https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html
	 */
	public DoubleArray symmetricDifference(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray);
		if (elementData.length == 0 && doubleArray.size() == 0) {
			return EMPTY_DOUBLE_ARRAY;
		}

		List<Double> elementDataList1 = this.toList();
		List<Double> elementDataList2 = doubleArray.toList();
		Set<Double> symmetricDiff = new HashSet<Double>(elementDataList1);
		symmetricDiff.addAll(elementDataList2);
		Set<Double> tmp = new HashSet<Double>(elementDataList1);
		tmp.retainAll(elementDataList2);
		symmetricDiff.removeAll(tmp);
		return DoubleArray.of(symmetricDiff);
	}

	public OptionalDouble kthLargest(double k) {
		if (elementData.length == 0 || k <= 0) {
			return OptionalDouble.empty();
		}

		if (k > elementData.length) {
			throw new IllegalArgumentException("return the element at index k-1 in sorted array");
		}

		PriorityQueue<Double> queue = new PriorityQueue<Double>((int) k);
		for (int i = 0; i < elementData.length; i++) {
			queue.offer(Double.valueOf(elementData[i]));

			if (queue.size() > k) {
				queue.poll();
			}
		}
		return OptionalDouble.of(queue.peek());
	}

	public OptionalDouble kthSmallest(double k) {
		if (elementData.length == 0 || k <= 0) {
			return OptionalDouble.empty();
		}

		if (k > elementData.length) {
			throw new IllegalArgumentException("return the element at index k-1 in sorted array");
		}

		PriorityQueue<Double> queue = new PriorityQueue<>((int) k, Comparator.reverseOrder());
		for (int i = 0; i < elementData.length; i++) {
			queue.offer(Double.valueOf(elementData[i]));

			if (queue.size() > k) {
				queue.poll();
			}
		}
		return OptionalDouble.of(queue.peek());
	}

	public DoubleArray merge(DoubleArray doubleArray, DoubleBinaryOperator doubleBinaryOperator) {

		Objects.requireNonNull(doubleArray);
		Objects.requireNonNull(doubleBinaryOperator);

		if (elementData.length != doubleArray.toArray().length) {
			throw new IllegalArgumentException("Both arays must be of same size");
		}
		double[] resultant = new double[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			resultant[i] = doubleBinaryOperator.applyAsDouble(elementData[i], doubleArray.elementData[i]);
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

	public OptionalDouble product() {
		return reduce(Product::multiply);
	}

	public DoubleArray evens() {

		if (elementData.length == 0) {
			return this;
		}

		List<Double> evens = Arrays.stream(elementData).filter(NumericPredicates.DOUBLE_EVEN).boxed()
				.collect(Collectors.toList());

		return of(evens);
	}

	public DoubleArray odds() {

		if (elementData.length == 0) {
			return this;
		}

		List<Double> odds = Arrays.stream(elementData).filter(NumericPredicates.DOUBLE_ODD).boxed()
				.collect(Collectors.toList());

		return of(odds);
	}

	public DoubleArray shuffle() {
		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		int size = elementData.length;
		for (int i = size; i > 1; i--) {
			// swap(list, i-1, rnd.nextInt(i));

			int val = new Random().nextInt(i);
			double temp = elementData[i - 1];
			elementData[i - 1] = elementData[val];
			elementData[val] = temp;
		}
		return new DoubleArray(elementData);
	}

	public Set<DoubleArray> cartesianProduct(DoubleArray doubleArray) {

		Set<DoubleArray> list = doubleArray.toList().stream()
				.flatMap(i -> this.toList().stream().map(j -> new DoubleArray(new double[] { i, j })))
				.collect(Collectors.toSet());

		return list;
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
	public static DoubleArray of(double[]... elementData) {

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

		return new DoubleArray(result);
	}

	public static DoubleArray of(DoubleArray... elementData) {

		Objects.requireNonNull(elementData);
		int length = 0;
		for (DoubleArray doubleArray : elementData) {
			length += Objects.requireNonNull(doubleArray).toArray().length;
		}

		double[] result = new double[length];

		int offset = 0;
		for (DoubleArray intArray : elementData) {
			System.arraycopy(intArray.toArray(), 0, result, offset, intArray.toArray().length);
			offset += intArray.toArray().length;
		}

		return new DoubleArray(result);
	}

	/**
	 * Removing all elements which makes array length 0;
	 */
	public DoubleArray clear() {
		return new DoubleArray(EMPTY_ARRAY);
	}

	public double[] toArray() {
		if (elementData.length == 0) {
			return EMPTY_ARRAY;
		}
		double[] copy = new double[elementData.length];
		System.arraycopy(elementData, 0, copy, 0, elementData.length);
		return copy;
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

	public boolean contains(DoubleArray doubleArray) {

		Objects.requireNonNull(doubleArray, "DoubleArray must not be null");

		List<Double> resultList = Arrays.stream(elementData).boxed().collect(Collectors.toList());
		List<Double> secondList = Arrays.stream(doubleArray.toArray()).boxed().collect(Collectors.toList());

		return resultList.containsAll(secondList);
	}

	public boolean containsAll(DoubleArray intArray) {
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

		private final double[] doubleArray;
		private int cursor;

		private DoubleArrayIterator(double[] doubleArray) {
			this.doubleArray = doubleArray;
			cursor = 0;
		}

		@Override
		public boolean hasNext() {
			return cursor != doubleArray.length;
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

			double[] intArray = DoubleArray.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i;
			return Double.valueOf(intArray[i]);
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