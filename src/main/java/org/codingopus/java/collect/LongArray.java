package org.codingopus.java.collect;

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
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

/**
 * Categorize methods in Static factory, intermediate and terminal operations.
 */
public final class LongArray {

    private final long[] elementData;

    private static final LongArray EMPTY_LONG_ARRAY = new LongArray(new long[] {});

    private LongArray(final long[] input) {
	this.elementData = input;
    }

    public static LongArray of(long a0) {

	return new LongArray(new long[] { a0 });
    }

    public static LongArray of(long a0, long... an) {

	Objects.requireNonNull(an);
	long[] resultant = new long[1 + an.length];
	resultant[0] = a0;
	int j = 1;
	for (int i = 0; i < an.length; i++) {
	    resultant[j++] = an[i];
	}
	return new LongArray(resultant);
    }

    public static LongArray of(long a[]) {

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

	long[] res = c.stream().mapToLong(val -> val.longValue()).toArray();

	return new LongArray(res);
    }

    public static LongArray zeros(int length) {
	return ofValue(0l, length);
    }

    public static LongArray ones(int length) {
	return ofValue(1l, length);
    }

    public static LongArray ofValue(long value, int length) {

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
    public LongArray add(long value) {

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
    public LongArray add(int index, long value) {

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

	long[] tempArray = c.stream().mapToLong(val -> val.longValue()).toArray();
	return addAll(this, LongArray.of(tempArray));
    }

    public IntArray toIntArray() {

	Objects.requireNonNull(elementData);

	int[] intArray = new int[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    intArray[i] = (int) elementData[i];
	}

	return IntArray.of(intArray);
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
    public LongArray plusOne() {

	long[] tempArray = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    tempArray[i] = elementData[i] + 1L;
	}

	return new LongArray(tempArray);
    }

    /**
     * Increments every value by specified factor.
     */
    public LongArray plus(long factor) {

	if (factor == 1)
	    return plusOne();

	long[] tempArray = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    tempArray[i] = elementData[i] + factor;
	}

	return new LongArray(tempArray);
    }

    /**
     * Adds every value from this array to value in specified array.
     */
    public LongArray plus(LongArray longArray) {

	Objects.requireNonNull(longArray, "LongArray must not be null.");
	if (elementData.length != longArray.toArray().length) {
	    throw new IllegalArgumentException("Arrays must of same size.");
	}

	long[] secondArray = longArray.toArray();
	long[] tempArray = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    tempArray[i] = elementData[i] + secondArray[i];
	}

	return new LongArray(tempArray);
    }

    public LongArray minus(long factor) {

	if (factor == 0)
	    return this;

	long[] tempArray = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    tempArray[i] = elementData[i] - factor;
	}

	return new LongArray(tempArray);
    }

    public LongArray minus(LongArray longArray) {

	Objects.requireNonNull(longArray, "LongArray must not be null.");
	if (elementData.length != longArray.toArray().length) {
	    throw new IllegalArgumentException("Arrays must of same size.");
	}

	long[] secondArray = longArray.toArray();
	long[] tempArray = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    tempArray[i] = elementData[i] - secondArray[i];
	}

	return new LongArray(tempArray);
    }

    /**
     * What if factor is 0? Handle the condition.
     */
    public LongArray multiply(long factor) {

	if (factor == 1)
	    return this;

	long[] tempArray = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    tempArray[i] = elementData[i] * factor;
	}

	return new LongArray(tempArray);
    }

    public LongArray multiply(LongArray longArray) {

	Objects.requireNonNull(longArray, "LongArray must not be null.");
	if (elementData.length != longArray.toArray().length) {
	    throw new IllegalArgumentException("Arrays must of same size.");
	}

	long[] secondArray = longArray.toArray();
	long[] tempArray = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    tempArray[i] = elementData[i] * secondArray[i];
	}

	return new LongArray(tempArray);
    }

    /**
     * What if factor is 0? Handle the condition.
     */
    public LongArray divide(long factor) {

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
	    tempArray[i] = elementData[i] / secondArray[i];
	}

	return new LongArray(tempArray);
    }

    public LongArray merge(LongArray longArray, LongBinaryOperator longBinaryOperator) {

	Objects.requireNonNull(longArray);
	Objects.requireNonNull(longBinaryOperator);

	if (elementData.length != longArray.toArray().length) {
	    throw new IllegalArgumentException("Both arays must be of same size");
	}
	long[] resultant = new long[elementData.length];
	for (int i = 0; i < elementData.length; i++) {
	    resultant[i] = longBinaryOperator.applyAsLong(elementData[i], longArray.elementData[i]);
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

    public OptionalLong max() {
	return reduce(Math::max);
    }

    public OptionalLong min() {
	return reduce(Math::min);
    }

    public OptionalLong sum() {
	return reduce(Long::sum);
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
    public static LongArray addAll(long[]... elementData) {

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

    public static LongArray addAll(LongArray... elementData) {

	Objects.requireNonNull(elementData);
	int length = 0;
	for (LongArray intArray : elementData) {
	    length += Objects.requireNonNull(intArray).toArray().length;
	}

	long[] result = new long[length];

	int offset = 0;
	for (LongArray intArray : elementData) {
	    System.arraycopy(intArray.toArray(), 0, result, offset, intArray.toArray().length);
	    offset += intArray.toArray().length;
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
	return elementData;
    }

    public int size() {
	return elementData.length;
    }

    public boolean isEmpty() {
	return size() == 0;
    }

    public boolean contains(long value) {

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
	result = prime * result + Arrays.hashCode(elementData);
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

    public int indexOf(long value) {

	for (int i = 0; i < elementData.length; i++) {
	    if (elementData[i] == value) {
		return i;
	    }
	}

	return -1;
    }

    public int lastIndexOf(long value) {

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

	private final long[] LongArray;
	private int cursor;

	private LongArrayIterator(long[] LongArray) {
	    this.LongArray = LongArray;
	    cursor = 0;
	}

	@Override
	public boolean hasNext() {
	    return cursor != LongArray.length;
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

	private LongArrayListIterator(long[] LongArray, int index) {
	    super(LongArray);
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

	    long[] LongArray = LongArray.this.elementData;
	    if (i >= elementData.length)
		throw new ConcurrentModificationException();
	    cursor = i;
	    return Long.valueOf(LongArray[i]);
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

}