package org.iagl.debugging.programtrace.trace.debugger;

import org.iagl.debugging.programtrace.trace.debugger.DebugTrace;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class DebugTraceTest {
    @Test
    void getSerializedVariables_works() {
        // ARRANGE
        final var variables = new HashMap<String, String>();
        variables.put("var1", "2");
        variables.put("var2", "pouet");
        final var debugTrace = new DebugTrace(
                "myFile.java",
                "someMethod",
                45L,
                variables
        );

        // ACT
        final var serializedVariables = debugTrace.getSerializedVariables();

        // ASSERT
        assertThat(serializedVariables).isEqualTo("eyJ2YXIyIjoicG91ZXQiLCJ2YXIxIjoiMiJ9");
    }

    @Test
    void getDeserializedVariables_works() {
        // ARRANGE
        final var serializedVariables = "eyJ2YXIyIjoicG91ZXQiLCJ2YXIxIjoiMiJ9";

        // ACT
        final var deserializedVariables = DebugTrace.getDeserializedVariables(serializedVariables);

        // ASSERT
        assertThat(deserializedVariables)
                .contains(
                        new AbstractMap.SimpleEntry<>("var1", "2"),
                        new AbstractMap.SimpleEntry<>("var2", "pouet"));
    }

    @Test
    void toString_works() {
        // ARRANGE
        final var variables = new HashMap<String, String>();
        variables.put("var1", "2");
        variables.put("var2", "pouet");
        final var debugTrace = new DebugTrace(
                "myFile.java",
                "someMethod",
                45L,
                variables
        );

        // ACT
        final var toString = debugTrace.toString();

        // ASSERT
        assertThat(toString).isEqualTo("myFile.java,someMethod,45,eyJ2YXIyIjoicG91ZXQiLCJ2YXIxIjoiMiJ9");
    }

    @Test
    void fromLine_works() {
        // ARRANGE
        final var variables = new HashMap<String, String>();
        variables.put("var1", "2");
        variables.put("var2", "pouet");
        final var debugTrace = new DebugTrace(
                "myFile.java",
                "someMethod",
                45L,
                variables
        );
        final var string = "myFile.java,someMethod,45,eyJ2YXIyIjoicG91ZXQiLCJ2YXIxIjoiMiJ9";

        // ACT
        final var parsedDebugTrace = DebugTrace.fromLine(string);

        // ARRANGE

        assertThat(parsedDebugTrace).isEqualToComparingFieldByField(debugTrace);
    }
}
