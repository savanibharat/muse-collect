package com.codingopus.collect;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalLong;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

import com.codingopus.math.Factorial;
import com.codingopus.math.Fibonacci;
import com.codingopus.math.Product;

/**
 * Categorize methods in Static factory, intermediate and terminal operations.
 */
public final class LongArray {

	private final long[] elementData;

	private static final LongArray EMPTY_LONG_ARRAY = new LongArray(new long[] {});
	private static final double[] EMPTY_DOUBLE_ARRAY = new double[] {};
	private static final Random random = new Random();

	private LongArray(final long[] input) {
		this.elementData = input;
	}

	public static LongArray of(int a0) {

		return new LongArray(new long[] { a0 });
	}

	public static LongArray of(int a0, int... an) {

		Objects.requireNonNull(an);
		long[] resultant = new long[1 + an.length];
		resultant[0] = a0;
		int j = 1;
		for (int i = 0; i < an.length; i++) {
			resultant[j++] = an[i];
		}
		return new LongArray(resultant);
	}

	public static LongArray of(int a[]) {

		Objects.requireNonNull(a, "Input array must not be null.");

		long[] result = new long[a.length];
		System.arraycopy(a, 0, result, 0, a.length);

		return new LongArray(result);
	}

	public static LongArray of(Collection<? extends Number> c) {

		Objects.requireNonNull(c, "Collection must not be null.");

		if (c.size() == 0) {
			return EMPTY_LONG_ARRAY;
		}

		long[] res = c.stream().filter(val -> val != null).mapToLong(val -> val.longValue()).toArray();

		return new LongArray(res);
	}

	public static LongArray random(int size) {

		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}

		else if (size == 0) {
			return new LongArray(new long[] {});
		}

		long[] elementData = new long[size];
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = random.nextInt();
		}

		return new LongArray(elementData);
	}

	public static LongArray random(int size, Random random) {

		Objects.requireNonNull(random);

		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}

		else if (size == 0) {
			return new LongArray(new long[] {});
		}

		long[] elementData = new long[size];
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = random.nextInt();
		}

		return new LongArray(elementData);
	}

	public static LongArray randomWithBound(int size, Random random, int bound) {

		Objects.requireNonNull(random);

		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}

		else if (size == 0) {
			return new LongArray(new long[] {});
		}

		long[] elementData = new long[size];
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = random.nextInt(bound);
		}

		return new LongArray(elementData);
	}

	public static LongArray zeros(int length) {
		return ofValue(0, length);
	}

	public static LongArray ones(int length) {
		return ofValue(1, length);
	}

	public static LongArray ofValue(int value, int length) {

		if (length < 0) {
			throw new IllegalArgumentException("Length cannot be negative");
		}

		long[] result = new long[length];
		Arrays.fill(result, value);

		return new LongArray(result);
	}

	/**
	 * Appends the specified element to the end of this array.
	 */
	public LongArray add(int value) {

		long[] tempArray = new long[elementData.length + 1];
		System.arraycopy(elementData, 0, tempArray, 0, elementData.length);
		tempArray[elementData.length] = value;

		return new LongArray(tempArray);
	}

	/**
	 * Inserts the specified element at the specified position in this array.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 */
	public LongArray add(int index, int value) {

		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		long[] tempArray = new long[elementData.length + 1];
		System.arraycopy(elementData, 0, tempArray, 0, index);
		tempArray[index] = value;
		System.arraycopy(elementData, index, tempArray, index + 1, elementData.length - index);

		return new LongArray(tempArray);
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this array.
	 */
	public LongArray addAll(Collection<? extends Number> c) {

		Objects.requireNonNull(c);

		if (c.size() == 0)
			return this;

		long[] tempArray = c.stream().filter(val -> val != null).mapToLong(val -> val.longValue()).toArray();
		return of(this, LongArray.of(tempArray));
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

	public List<Long> toList() {

		List<Long> list = new ArrayList<Long>();

		for (int i = 0; i < elementData.length; i++) {
			list.add(Long.valueOf(elementData[i]));
		}

		return Collections.unmodifiableList(list);
	}

	/**
	 * Increments every value by 1.
	 */
	public LongArray plusOne(ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(exceptionPredicate);

		if (exceptionPredicate.throwException()) {
			return new LongArray(addExact(1));
		}
		return new LongArray(addValues(1));
	}

	/**
	 * Increments every value by specified factor.
	 */
	public LongArray plus(int factor, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(exceptionPredicate);

		if (exceptionPredicate.throwException()) {
			return new LongArray(addExact(factor));
		}
		return new LongArray(addValues(factor));
	}

	/**
	 * Adds every value from this array to value in specified array.
	 */
	public LongArray plus(LongArray longArray, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(longArray, "LongArray must not be null.");
		Objects.requireNonNull(exceptionPredicate);

		if (elementData.length != longArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		long[] secondArray = longArray.toArray();
		long[] tempArray = new long[elementData.length];

		if (exceptionPredicate.throwException()) {
			for (int i = 0; i < elementData.length; i++) {
				tempArray[i] = Math.addExact(elementData[i], secondArray[i]);
			}
			return new LongArray(tempArray);
		}
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] + secondArray[i];
		}
		return new LongArray(tempArray);
	}

	public LongArray minus(int factor, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(exceptionPredicate);

		if (exceptionPredicate.throwException()) {
			return new LongArray(subtractExact(factor));
		}
		return new LongArray(subtractValues(factor));
	}

	public LongArray minus(LongArray LongArray, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(LongArray, "LongArray must not be null.");
		Objects.requireNonNull(exceptionPredicate);

		if (elementData.length != LongArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		long[] secondArray = LongArray.toArray();
		long[] tempArray = new long[elementData.length];

		if (exceptionPredicate.throwException()) {
			for (int i = 0; i < elementData.length; i++) {
				tempArray[i] = Math.subtractExact(elementData[i], secondArray[i]);
			}
			return new LongArray(tempArray);
		}
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] - secondArray[i];
		}
		return new LongArray(tempArray);
	}

	/**
	 * What if factor is 0? Handle the condition.
	 */
	public LongArray multiply(int factor, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(exceptionPredicate);

		if (exceptionPredicate.throwException()) {
			return new LongArray(multiplyExact(factor));
		}
		return new LongArray(multiplyValues(factor));
	}

	public LongArray multiply(LongArray longArray, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(longArray, "LongArray must not be null.");
		Objects.requireNonNull(exceptionPredicate);

		if (elementData.length != longArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		long[] secondArray = longArray.toArray();
		long[] tempArray = new long[elementData.length];

		if (exceptionPredicate.throwException()) {
			for (int i = 0; i < elementData.length; i++) {
				tempArray[i] = Math.multiplyExact(elementData[i], secondArray[i]);
			}
			return new LongArray(tempArray);
		}
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = Product.multiply(elementData[i], secondArray[i]);
		}
		return new LongArray(tempArray);

	}

	public OptionalLong dotProduct(LongArray longArray, ExceptionPredicate exceptionPredicate) {

		Objects.requireNonNull(longArray, "LongArray must not be null.");
		Objects.requireNonNull(exceptionPredicate);

		if (elementData.length != longArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		} else if (elementData.length == 0 && longArray.size() == 0) {
			return OptionalLong.empty();
		}

		long[] secondArray = longArray.toArray();
		long sum = 0;
		if (exceptionPredicate.throwException()) {
			for (int i = 0; i < elementData.length; i++) {
				sum = sum + Math.multiplyExact(elementData[i], secondArray[i]);
			}
			return OptionalLong.of(sum);
		}
		for (int i = 0; i < elementData.length; i++) {
			sum = sum + Product.multiply(elementData[i], secondArray[i]);
		}
		return OptionalLong.of(sum);
	}

	/**
	 * What if factor is 0? Handle the condition.
	 */
	public LongArray divide(int factor) {

		if (factor == 0) {
			throw new ArithmeticException("Division by 0 tends to INFINITY.");
		}

		if (factor == 1)
			return this;

		long[] tempArray = new long[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			tempArray[i] = elementData[i] / factor;
		}

		return new LongArray(tempArray);
	}

	/**
	 * Handle condition if any value is 0.
	 */
	public LongArray divide(LongArray longArray) {

		Objects.requireNonNull(longArray, "LongArray must not be null.");
		if (elementData.length != longArray.toArray().length) {
			throw new IllegalArgumentException("Arrays must of same size.");
		}

		long[] secondArray = longArray.toArray();
		long[] tempArray = new long[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			if (secondArray[i] == 0) {
				throw new ArithmeticException("Division by 0 tends to INFINITY.");
			}
			tempArray[i] = elementData[i] / secondArray[i];
		}

		return new LongArray(tempArray);
	}

	public DoubleArray sin() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.sin((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray arcsin() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.asin((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray sinh() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.sinh((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray cos() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.cos((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray arccos() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.acos((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray cosh() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.cosh((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray tan() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.tan((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray arctan() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.atan((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray tanh() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.tanh((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray toRadians() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.toRadians((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray toDegrees() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.toDegrees((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray log() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.log((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray log10() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.log10((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray log1p() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.log1p((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray sqrt() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.sqrt((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray cbrt() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.cbrt((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray ceil() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.ceil((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray floor() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.floor((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray rint() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.rint((double) val)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray pow(double exp) {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataSin = Arrays.stream(elementData).mapToDouble(val -> Math.pow((double) val, exp)).toArray();
		return DoubleArray.of(elementDataSin);
	}

	public DoubleArray exp() {
		if (elementData.length == 0) {
			return DoubleArray.of(EMPTY_DOUBLE_ARRAY);
		}

		double[] elementDataExp = Arrays.stream(elementData).mapToDouble(val -> Math.exp((double) val)).toArray();
		return DoubleArray.of(elementDataExp);
	}

	public LongArray abs() {
		if (elementData.length == 0) {
			return this;
		}

		long[] elementDataAbs = Arrays.stream(elementData).map(val -> Math.abs(val)).toArray();
		return LongArray.of(elementDataAbs);
	}

	public LongArray mod(int m) {
		if (elementData.length == 0) {
			return this;
		}
		BigInteger mod = BigInteger.valueOf((long) m);
		for (int i = 0; i < elementData.length; i++) {
			BigInteger bg1 = BigInteger.valueOf((long) elementData[i]);
			elementData[i] = bg1.mod(mod).intValue();
		}
		return new LongArray(elementData);

	}

	public LongArray union(LongArray longArray) {
		Objects.requireNonNull(longArray);
		if (elementData.length == 0 && longArray.size() == 0) {
			return this;
		} else if (elementData.length != 0 && longArray.size() == 0) {
			return this;
		} else if (elementData.length == 0 && longArray.size() != 0) {
			return longArray;
		}

		// both LongArray's has length > 1.
		Set<Long> set = new HashSet<>(this.toList());
		set.addAll(longArray.toList());
		return LongArray.of(set);
	}

	public LongArray intersection(LongArray longArray) {

		Objects.requireNonNull(longArray);
		if (elementData.length == 0 || longArray.size() == 0) {
			return EMPTY_LONG_ARRAY;
		}

		// both LongArray's has length > 1.
		Set<Long> set = new HashSet<>(this.toList());
		set.retainAll(longArray.toList());
		return LongArray.of(set);
	}

	/**
	 * https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html
	 */
	public LongArray asymmetricDifference(LongArray longArray) {

		Objects.requireNonNull(longArray);
		if (elementData.length == 0 && longArray.size() == 0) {
			return EMPTY_LONG_ARRAY;
		} else if (elementData.length != 0 && longArray.size() == 0) {
			return this;
		} else if (elementData.length == 0 && longArray.size() != 0) {
			return this;
		}

		// both LongArray's has length > 1.
		Set<Long> set = new HashSet<>(this.toList());
		set.removeAll(longArray.toList());
		return LongArray.of(set);
	}

	/**
	 * https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html
	 */
	public LongArray symmetricDifference(LongArray longArray) {

		Objects.requireNonNull(longArray);
		if (elementData.length == 0 && longArray.size() == 0) {
			return EMPTY_LONG_ARRAY;
		}

		List<Long> elementDataList1 = this.toList();
		List<Long> elementDataList2 = longArray.toList();
		Set<Long> symmetricDiff = new HashSet<Long>(elementDataList1);
		symmetricDiff.addAll(elementDataList2);
		Set<Long> tmp = new HashSet<Long>(elementDataList1);
		tmp.retainAll(elementDataList2);
		symmetricDiff.removeAll(tmp);
		return LongArray.of(symmetricDiff);
	}

	/**
	 * Will return nth fibonacci of value in array. For input {1, 2, 3, 4, 5, 6,
	 * 7, 8 } result is [1, 1, 2, 3, 5, 8, 13, 21]
	 */
	public Collection<BigInteger> nthFibonacci() {
		if (elementData.length == 0) {
			return new ArrayList<>();
		}

		Collection<BigInteger> coll = Arrays.stream(elementData).mapToObj(val -> Fibonacci.fibonacci(val))
				.collect(Collectors.toList());
		return coll;
	}

	public OptionalLong kthLargest(int k) {
		if (elementData.length == 0 || k <= 0) {
			return OptionalLong.empty();
		}

		if (k > elementData.length) {
			throw new IllegalArgumentException("return the element at index k-1 in sorted array");
		}

		PriorityQueue<Long> queue = new PriorityQueue<>(k);
		for (int i = 0; i < elementData.length; i++) {
			queue.offer(Long.valueOf(elementData[i]));

			if (queue.size() > k) {
				queue.poll();
			}
		}
		return OptionalLong.of(queue.peek());
	}

	public OptionalLong kthSmallest(int k) {
		if (elementData.length == 0 || k <= 0) {
			return OptionalLong.empty();
		}

		if (k > elementData.length) {
			throw new IllegalArgumentException("return the element at index k-1 in sorted array");
		}

		PriorityQueue<Long> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
		for (int i = 0; i < elementData.length; i++) {
			queue.offer(Long.valueOf(elementData[i]));

			if (queue.size() > k) {
				queue.poll();
			}
		}
		return OptionalLong.of(queue.peek());
	}

	public LongArray merge(LongArray LongArray, LongBinaryOperator longBinaryOperator) {

		Objects.requireNonNull(LongArray);
		Objects.requireNonNull(longBinaryOperator);

		if (elementData.length != LongArray.toArray().length) {
			throw new IllegalArgumentException("Both arays must be of same size");
		}
		long[] resultant = new long[elementData.length];
		for (int i = 0; i < elementData.length; i++) {
			resultant[i] = longBinaryOperator.applyAsLong(elementData[i], LongArray.elementData[i]);
		}
		return new LongArray(resultant);
	}

	public LongArray sort() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		Arrays.sort(elementData);
		return this;
	}

	public LongArray distinct() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		long[] distinctElements = Arrays.stream(elementData).distinct().toArray();

		return new LongArray(distinctElements);
	}

	/**
	 * We are modifing the array. This is allowed as array is final and not
	 * array contents.
	 */
	public LongArray reverse() {

		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		int l = elementData.length;
		for (int i = 0; i < l / 2; i++) {
			long temp = elementData[i];
			elementData[i] = elementData[l - i - 1];
			elementData[l - i - 1] = temp;
		}
		return new LongArray(elementData);
	}

	public OptionalLong max() {
		return reduce(Math::max);
	}

	public OptionalLong min() {
		return reduce(Math::min);
	}

	public OptionalLong sum() {
		return reduce(Long::sum);
	}

	public OptionalLong product() {
		return reduce(Product::multiply);
	}

	public LongArray evens() {

		if (elementData.length == 0) {
			return this;
		}

		List<Long> evens = Arrays.stream(elementData).filter(NumericPredicates.LONG_EVEN).boxed()
				.collect(Collectors.toList());

		return of(evens);
	}

	public LongArray odds() {

		if (elementData.length == 0) {
			return this;
		}

		List<Long> odds = Arrays.stream(elementData).filter(NumericPredicates.LONG_ODD).boxed()
				.collect(Collectors.toList());

		return of(odds);
	}

	public LongArray shuffle() {
		if (elementData.length == 0 || elementData.length == 1) {
			return this;
		}

		int size = elementData.length;
		for (int i = size; i > 1; i--) {
			// swap(list, i-1, rnd.nextInt(i));

			int val = new Random().nextInt(i);
			long temp = elementData[i - 1];
			elementData[i - 1] = elementData[val];
			elementData[val] = temp;
		}
		return new LongArray(elementData);
	}

	public Map<Long, BigInteger> factorial() {

		if (elementData.length == 0) {
			return new HashMap<>();
		}

		Map<Long, BigInteger> map = new LinkedHashMap<>();
		for (int i = 0; i < elementData.length; i++) {
			map.put(Long.valueOf(elementData[i]), Factorial.of(elementData[i]));
		}
		return map;
	}

	public Set<LongArray> cartesianProduct(LongArray LongArray) {

		Set<LongArray> list = LongArray.toList().stream()
				.flatMap(i -> this.toList().stream().map(j -> new LongArray(new long[] { i, j })))
				.collect(Collectors.toSet());

		return list;
	}

	public OptionalLong reduce(LongBinaryOperator operator) {

		if (elementData.length == 0) {
			return OptionalLong.empty();
		} else if (elementData.length == 1) {
			return OptionalLong.of(elementData[0]);
		}

		long result = elementData[0];
		for (int i = 1; i < elementData.length; i++) {
			result = operator.applyAsLong(result, elementData[i]);
		}
		return OptionalLong.of(result);
	}

	/**
	 * Take from https://www.mkyong.com/java/java-how-to-join-arrays/
	 */
	public static LongArray of(long[]... elementData) {

		Objects.requireNonNull(elementData);
		int length = 0;
		for (long[] array : elementData) {
			length += Objects.requireNonNull(array).length;
		}

		long[] result = new long[length];

		int offset = 0;
		for (long[] array : elementData) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}

		return LongArray.of(result);
	}

	public static LongArray of(LongArray... elementData) {

		Objects.requireNonNull(elementData);
		int length = 0;
		for (LongArray LongArray : elementData) {
			length += Objects.requireNonNull(LongArray).toArray().length;
		}

		long[] result = new long[length];

		int offset = 0;
		for (LongArray LongArray : elementData) {
			System.arraycopy(LongArray.toArray(), 0, result, offset, LongArray.toArray().length);
			offset += LongArray.toArray().length;
		}

		return LongArray.of(result);
	}

	/**
	 * Removing all elements which makes array length 0;
	 */
	public LongArray clear() {
		return new LongArray(new long[] {});
	}

	public long[] toArray() {
		if (elementData.length == 0) {
			return new long[] {};
		}
		long[] copy = new long[elementData.length];
		System.arraycopy(elementData, 0, copy, 0, elementData.length);
		return copy;
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

	public boolean contains(LongArray longArray) {

		Objects.requireNonNull(longArray, "LongArray must not be null");

		List<Long> resultList = Arrays.stream(elementData).boxed().collect(Collectors.toList());
		List<Long> secondList = Arrays.stream(longArray.toArray()).boxed().collect(Collectors.toList());

		return resultList.containsAll(secondList);
	}

	public boolean containsAll(LongArray LongArray) {
		return contains(LongArray);
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
		LongArray other = (LongArray) obj;
		if (!Arrays.equals(elementData, other.elementData))
			return false;
		return true;
	}

	public long get(int index) {
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

	public Iterator<Long> iterator() {
		return new LongArrayIterator(elementData);
	}

	public ListIterator<Long> listIterator() {
		return new LongArrayListIterator(elementData, 0);
	}

	public ListIterator<Long> listIterator(int index) {

		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}

		return new LongArrayListIterator(elementData, index);
	}

	private class LongArrayIterator implements Iterator<Long> {

		private final long[] longArray;
		private int cursor;

		private LongArrayIterator(long[] LongArray) {
			this.longArray = LongArray;
			cursor = 0;
		}

		@Override
		public boolean hasNext() {
			return cursor != longArray.length;
		}

		@Override
		public Long next() {
			if (hasNext()) {
				return Long.valueOf(elementData[cursor++]);
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}

	private class LongArrayListIterator extends LongArrayIterator implements ListIterator<Long> {

		private int cursor;

		private LongArrayListIterator(long[] longArray, int index) {
			super(longArray);
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
		public Long previous() {
			int i = cursor - 1;
			if (i < 0)
				throw new NoSuchElementException();

			long[] longArray = LongArray.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i;
			return Long.valueOf(longArray[i]);
		}

		@Override
		public void set(Long e) {
			throw new UnsupportedOperationException("set");
		}

		@Override
		public void add(Long e) {
			throw new UnsupportedOperationException("add");
		}
	}

	@Override
	public String toString() {
		return "LongArray [result=" + Arrays.toString(elementData) + "]";
	}

	private long[] addExact(long factor) {
		return Arrays.stream(elementData).map(val -> Math.addExact(val, factor)).toArray();
	}

	private long[] addValues(long factor) {
		return Arrays.stream(elementData).map(val -> Long.sum(val, factor)).toArray();
	}

	private long[] subtractExact(long factor) {
		return Arrays.stream(elementData).map(val -> Math.subtractExact(val, factor)).toArray();
	}

	private long[] subtractValues(long factor) {
		return Arrays.stream(elementData).map(val -> (val - factor)).toArray();
	}

	private long[] multiplyExact(long factor) {
		return Arrays.stream(elementData).map(val -> Math.multiplyExact(val, factor)).toArray();
	}

	private long[] multiplyValues(long factor) {
		return Arrays.stream(elementData).map(val -> (Product.multiply(val, factor))).toArray();
	}

}