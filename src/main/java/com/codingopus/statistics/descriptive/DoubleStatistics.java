package com.codingopus.statistics.descriptive;

import java.util.OptionalDouble;

public interface DoubleStatistics {

    public OptionalDouble min();

    public OptionalDouble max();

    public OptionalDouble mean();

    public OptionalDouble median();

    public OptionalDouble mode();

    public OptionalDouble range();
}
