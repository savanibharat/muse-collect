package com.codingopus.statistics.descriptive;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.codingopus.java.collect.IntArray;

/**
 * Calculates min, max, mean, median, mode and range for {@code IntArray}
 */
public final class IntSummaryStatistics implements IntStatistics {

	private final IntArray intArray;

	private IntSummaryStatistics(IntArray intArray) {
		Objects.requireNonNull(intArray);
		this.intArray = intArray;
	}

	public static IntSummaryStatistics of(IntArray intArray) {
		return new IntSummaryStatistics(intArray);
	}

	@Override
	public OptionalInt min() {
		return intArray.min();
	}

	@Override
	public OptionalInt max() {
		return intArray.max();
	}

	/**
	 * Division is based on BigDecimal with scale of 25 and
	 * {@code RoundingMode#HALF_UP}.
	 */
	@Override
	public OptionalDouble mean() {

		if (intArray.size() == 0) {
			return OptionalDouble.empty();
		} else if (intArray.size() == 1) {
			return OptionalDouble.of((double) intArray.get(0));
		}

		// We are sure that IntArray will have have values.
		BigDecimal summation = new BigDecimal(intArray.sum().getAsInt());
		BigDecimal divisor = new BigDecimal(intArray.size());
		BigDecimal quotient = summation.divide(divisor, 25, RoundingMode.HALF_UP);
		return OptionalDouble.of(quotient.doubleValue());
	}

	@Override
	public OptionalDouble median() {

		if (intArray.size() == 0) {
			return OptionalDouble.empty();
		} else if (intArray.size() == 1) {
			return OptionalDouble.of((double) intArray.get(0));
		}

		// Below code is valid for the array length >= 2.
		else if (intArray.size() % 2 != 0) {
			int medianIndex = intArray.size() / 2;
			return OptionalDouble.of((double) intArray.get(medianIndex));
		}

		int medianIndex1 = intArray.size() / 2;
		int medianIndex2 = medianIndex1 - 1;
		int sum = intArray.get(medianIndex1) + intArray.get(medianIndex2);
		BigDecimal divisor = new BigDecimal(2);
		double median = new BigDecimal(sum).divide(divisor, 25, RoundingMode.HALF_UP).doubleValue();
		return OptionalDouble.of(median);
	}

	@Override
	public OptionalInt mode() {

		if (intArray.size() == 0 || intArray.size() == 1) {
			return OptionalInt.empty();
		}

		Map<Integer, Long> map = Arrays.stream(intArray.toArray()).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// If frequency of all values is 1 then no mode found.
		Set<Long> set = new HashSet<Long>(map.values());
		if (set.size() == 1) {
			return OptionalInt.empty();
		}

		// Key with highest value is mode.
		Map.Entry<Integer, Long> maxEntry = null;
		for (Map.Entry<Integer, Long> entry : map.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return OptionalInt.of(maxEntry.getKey().intValue());
	}

	@Override
	public OptionalInt range() {

		if (intArray.size() == 0) {
			return OptionalInt.empty();
		} else if (intArray.size() == 1) {
			return OptionalInt.of(intArray.get(0));
		}

		// Below code is valid for the array length >= 2.
		int range = max().getAsInt() - min().getAsInt();
		return OptionalInt.of(range);
	}
}
