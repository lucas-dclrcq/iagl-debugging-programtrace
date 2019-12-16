package org.iagl.debugging.programtrace.trace.debugger;

import org.iagl.debugging.programtrace.exception.CannotMoveCursorException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DebuggerProgramTraceTest {

    @AfterEach
    void cleanup() throws NoSuchFieldException, IllegalAccessException {
        Field field = DebuggerProgramTrace.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(DebuggerProgramTrace.class, null);
    }

    @Test
    void given_new_instance_then_list_of_traces_should_not_be_null() {
        // ARRANGE
        final var instance = DebuggerProgramTrace.getInstance();

        // ACT
        final var traces = instance.getTraces();

        assertThat(traces).isNotNull();
    }

    @Test
    void given_new_instance_then_cursor_should_be_at_minus_one() {
        // ARRANGE
        final var instance = DebuggerProgramTrace.getInstance();

        // ACT
        final var cursor = instance.getCursor();

        assertThat(cursor).isEqualTo(-1);
    }

    @Test
    void given_two_traces_cursor_in_initial_state_next_should_return_first_trace() throws CannotMoveCursorException {
        // ARRANGE
        final var instance = DebuggerProgramTrace.getInstance();
        instance.trace("FILENAME", "METHOD_NAME", 88L, Map.of("VAR1", "VAL1"));

        // ACT
        final var next = instance.next();

        // ASSERT
        assertThat(instance.getCursor()).isEqualTo(0);
        assertThat(next.getFileName()).isEqualTo("FILENAME");
    }

    @Test
    void adding_trace_works() {
        // ARRANGE
        final var instance = DebuggerProgramTrace.getInstance();

        // ACT
        instance.trace("FILENAME", "METHOD_NAME", 88L, Map.of("VAR1", "VAL1"));

        // ASSERT
        assertThat(instance.getTraces())
                .containsExactly(new DebugTrace("FILENAME", "METHOD_NAME", 88L, Map.of("VAR1", "VAL1")));
    }
}
