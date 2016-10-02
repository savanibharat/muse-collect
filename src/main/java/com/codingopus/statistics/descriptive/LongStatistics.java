package com.codingopus.statistics.descriptive;

import java.util.OptionalDouble;
import java.util.OptionalLong;

public interface LongStatistics {

	public OptionalLong min();

	public OptionalLong max();

	public OptionalDouble mean();

	public OptionalDouble median();

	public OptionalLong mode();

	public OptionalLong range();

}
