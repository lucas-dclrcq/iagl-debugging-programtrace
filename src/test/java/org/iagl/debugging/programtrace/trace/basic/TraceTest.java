package org.iagl.debugging.programtrace.trace.basic;

import org.iagl.debugging.programtrace.trace.basic.Trace;
import org.iagl.debugging.programtrace.trace.basic.TraceLevel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class TraceTest {
    @Test
    void given_trace_to_string_should_be_formatted() {
        // ARRANGE
        final Trace trace = new Trace(
                TraceLevel.CRITIQUE,
                LocalDateTime.parse("2019-12-04T10:04:01.158468"),
                "someMethod",
                "364"
        );

        // ACT
        final String formattedTrace = trace.toString();

        // ASSERT
        assertThat(formattedTrace).isEqualTo("2019-12-04T10:04:01.158468,CRITIQUE,someMethod,364");
    }

    @Test
    void given_to_identical_traces_equals_should_return_true() {
        // ARRANGE
        final Trace trace1 = new Trace(
                TraceLevel.CRITIQUE,
                LocalDateTime.parse("2019-12-04T10:04:01.158468"),
                "someMethod",
                "364"
        );
        final Trace trace2 = new Trace(
                TraceLevel.CRITIQUE,
                LocalDateTime.parse("2019-12-04T10:04:01.158468"),
                "someMethod",
                "364"
        );

        // ACT
        final boolean equalitude = trace1.equals(trace2);

        // ASSERT
        assertThat(equalitude).isTrue();
    }
}
