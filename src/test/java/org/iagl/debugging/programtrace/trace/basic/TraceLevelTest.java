package org.iagl.debugging.programtrace.trace.basic;

import org.iagl.debugging.programtrace.trace.basic.TraceLevel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TraceLevelTest {

    @Test
    void given_other_level_is_superior_should_return_true() {
        // ARRANGE
        final TraceLevel faible = TraceLevel.FAIBLE;
        final TraceLevel critique = TraceLevel.CRITIQUE;

        // ACT
        final boolean equalOrSuperiorTo = critique.isEqualOrSuperiorTo(faible);

        // ASSERT
        assertThat(equalOrSuperiorTo).isTrue();
    }

    @Test
    void given_other_level_is_equal_should_return_true() {
        // ARRANGE
        final TraceLevel faible = TraceLevel.FAIBLE;
        final TraceLevel otherFaible = TraceLevel.FAIBLE;

        // ACT
        final boolean equalOrSuperiorTo = otherFaible.isEqualOrSuperiorTo(faible);

        // ASSERT
        assertThat(equalOrSuperiorTo).isTrue();
    }

    @Test
    void given_other_level_is_inferior_should_return_false() {
        // ARRANGE
        final TraceLevel faible = TraceLevel.FAIBLE;
        final TraceLevel critique = TraceLevel.CRITIQUE;

        // ACT
        final boolean equalOrSuperiorTo = faible.isEqualOrSuperiorTo(critique);

        // ASSERT
        assertThat(equalOrSuperiorTo).isFalse();
    }
}
