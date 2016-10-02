package com.codingopus.statistics.descriptive;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.codingopus.java.collect.DoubleArray;

public class DoubleSummaryStatistics implements DoubleStatistics {

	private final DoubleArray doubleArray;

	private DoubleSummaryStatistics(DoubleArray doubleArray) {
		Objects.requireNonNull(doubleArray);
		this.doubleArray = doubleArray;
	}

	public static DoubleSummaryStatistics of(DoubleArray doubleArray) {
		return new DoubleSummaryStatistics(doubleArray);
	}

	@Override
	public OptionalDouble min() {
		return doubleArray.min();
	}

	@Override
	public OptionalDouble max() {
		return doubleArray.max();
	}

	@Override
	public OptionalDouble mean() {

		if (doubleArray.size() == 0) {
			return OptionalDouble.empty();
		} else if (doubleArray.size() == 1) {
			return OptionalDouble.of((double) doubleArray.get(0));
		}

		// We are sure that doubleArray will have have values.
		BigDecimal summation = new BigDecimal(doubleArray.sum().getAsDouble());
		BigDecimal divisor = new BigDecimal(doubleArray.size());
		BigDecimal quotient = summation.divide(divisor, 25, RoundingMode.HALF_UP);
		return OptionalDouble.of(quotient.doubleValue());
	}

	@Override
	public OptionalDouble median() {

		if (doubleArray.size() == 0) {
			return OptionalDouble.empty();
		} else if (doubleArray.size() == 1) {
			return OptionalDouble.of(doubleArray.get(0));
		}

		// Below code is valid for the array length >= 2.
		else if (doubleArray.size() % 2 != 0) {
			int medianIndex = doubleArray.size() / 2;
			return OptionalDouble.of(doubleArray.get(medianIndex));
		}

		int medianIndex1 = doubleArray.size() / 2;
		int medianIndex2 = medianIndex1 - 1;
		double sum = doubleArray.get(medianIndex1) + doubleArray.get(medianIndex2);
		BigDecimal divisor = new BigDecimal(2);
		double median = new BigDecimal(sum).divide(divisor, 25, RoundingMode.HALF_UP).doubleValue();
		return OptionalDouble.of(median);
	}

	@Override
	public OptionalDouble mode() {

		if (doubleArray.size() == 0 || doubleArray.size() == 1) {
			return OptionalDouble.empty();
		}

		Map<Double, Long> map = Arrays.stream(doubleArray.toArray())
									  .boxed()
									  .collect(
											  Collectors.groupingBy(Function.identity(), 
													  				Collectors.counting()));

		// If frequency of all values is 1 then no mode found.
		Set<Long> set = new HashSet<Long>(map.values());
		if (set.size() == 1) {
			return OptionalDouble.empty();
		}

		// Key with highest value is mode.
		Map.Entry<Double, Long> maxEntry = null;
		for (Map.Entry<Double, Long> entry : map.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		return OptionalDouble.of(maxEntry.getKey().intValue());
	}

	@Override
	public OptionalDouble range() {

		if (doubleArray.size() == 0) {
			return OptionalDouble.empty();
		} else if (doubleArray.size() == 1) {
			return OptionalDouble.of((double) doubleArray.get(0));
		}

		// Below code is valid for the array length >= 2.
		double range = max().getAsDouble() - min().getAsDouble();
		return OptionalDouble.of(range);
	}

}
