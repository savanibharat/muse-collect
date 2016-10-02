package com.codingopus.statistics.descriptive;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface IntStatistics {

    public OptionalInt min();

    public OptionalInt max();

    public OptionalDouble mean();

    public OptionalDouble median();

    public OptionalInt mode();

    public OptionalInt range();

}
