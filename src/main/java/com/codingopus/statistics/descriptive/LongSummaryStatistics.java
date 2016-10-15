package com.codingopus.statistics.descriptive;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.codingopus.collect.LongArray;

/**
 * Calculates min, max, mean, median, mode and range for {@code LongArray}
 */
public final class LongSummaryStatistics implements LongStatistics {

	private final LongArray longArray;

	private LongSummaryStatistics(LongArray longArray) {
		Objects.requireNonNull(longArray);
		this.longArray = longArray;
	}

	public static LongSummaryStatistics of(LongArray longArray) {
		return new LongSummaryStatistics(longArray);
	}

	@Override
	public OptionalLong min() {
		return longArray.min();
	}

	@Override
	public OptionalLong max() {
		return longArray.max();
	}

	@Override
	public OptionalDouble mean() {

		if (longArray.size() == 0) {
			return OptionalDouble.empty();
		} else if (longArray.size() == 1) {
			return OptionalDouble.of((double) longArray.get(0));
		}

		// We are sure that LongArray will have have values.
		BigDecimal summation = new BigDecimal(longArray.sum().getAsLong());
		BigDecimal divisor = new BigDecimal(longArray.size());
		BigDecimal quotient = summation.divide(divisor, 25, RoundingMode.HALF_UP);
		return OptionalDouble.of(quotient.doubleValue());
	}

	@Override
	public OptionalDouble median() {

		if (longArray.size() == 0) {
			OptionalDouble.empty();
		} else if (longArray.size() == 1) {
			OptionalDouble.of((double) longArray.get(0));
		}

		// Below code is valid for the array length >= 2.
		else if (longArray.size() % 2 != 0) {
			int medianIndex = longArray.size() / 2;
			return OptionalDouble.of((double) longArray.get(medianIndex));
		}

		int medianIndex1 = longArray.size() / 2;
		int medianIndex2 = medianIndex1 - 1;
		long sum = longArray.get(medianIndex1) + longArray.get(medianIndex2);
		BigDecimal divisor = new BigDecimal(2);
		double median = new BigDecimal(sum).divide(divisor, 25, RoundingMode.HALF_UP).doubleValue();
		return OptionalDouble.of(median);
	}

	@Override
	public OptionalLong mode() {

		if (longArray.size() == 0 || longArray.size() == 1) {
			return OptionalLong.empty();
		}

		Map<Long, Long> map = Arrays.stream(longArray.toArray()).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		// If frequency of all values is 1 then no mode found.
		Set<Long> set = new HashSet<Long>(map.values());
		if (set.size() == 1) {
			return OptionalLong.empty();
		}

		// Key with highest value is mode.
		Map.Entry<Long, Long> maxEntry = null;
		for (Map.Entry<Long, Long> entry : map.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return OptionalLong.of(maxEntry.getKey().intValue());
	}

	@Override
	public OptionalLong range() {

		if (longArray.size() == 0) {
			return OptionalLong.empty();
		} else if (longArray.size() == 1) {
			OptionalDouble.of((double) longArray.get(0));
		}

		// Below code is valid for the array length >= 2.
		long range = max().getAsLong() - min().getAsLong();
		return OptionalLong.of(range);
	}

}
