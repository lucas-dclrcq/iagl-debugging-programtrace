package org.iagl.debugging.programtrace.trace.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BasicProgramTraceTest {
    @Test
    void given_valid_file_should_create_program_trace(@TempDir Path tempDir) throws IOException {
        // ARRANGE
        final List<String> traceLines = Arrays.asList(
                "2019-12-04T10:04:01.158468,CRITIQUE,someMethod,364",
                "2019-12-05T17:10:01.158468,FAIBLE,someOtherMethod,abcd"
        );
        final Path traceFile = tempDir.resolve("test.txt");
        Files.write(traceFile, traceLines);

        // ACT
        final BasicProgramTrace programTrace = BasicProgramTrace.fromFile(traceFile.toFile().getAbsolutePath());

        // ASSERT
        assertThat(programTrace.getTraces())
                .containsExactly(
                        new Trace(TraceLevel.CRITIQUE, LocalDateTime.parse("2019-12-04T10:04:01.158468"), "someMethod", "364"),
                        new Trace(TraceLevel.FAIBLE, LocalDateTime.parse("2019-12-05T17:10:01.158468"), "someOtherMethod", "abcd")
                );
    }
}
