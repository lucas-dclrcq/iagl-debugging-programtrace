package org.iagl.debugging.programtrace.trace.debugger;

import com.sun.jdi.event.Event;
import com.sun.jdi.event.StepEvent;
import org.iagl.debugging.programtrace.trace.basic.Trace;
import org.iagl.debugging.programtrace.trace.basic.TraceLevel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DebuggerProgramTrace {
    private static DebuggerProgramTrace INSTANCE;

    private final List<DebugTrace> traces;

    /**
     * Load traces from a file
     *
     * @param filename The path to a file containing serialized traces
     * @return An instance of ProgramTrace
     */
    public static DebuggerProgramTrace fromFile(String filename) {
        return new DebuggerProgramTrace(filename);
    }

    /**
     * Return the current instance of DebugProgramTrace, it will create one if it does not exists
     *
     * @return An instance of ProgramTrace
     */
    public static DebuggerProgramTrace getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DebuggerProgramTrace();
        }

        return INSTANCE;
    }

    private DebuggerProgramTrace() {
        this.traces = new ArrayList<>();
    }

    private DebuggerProgramTrace(String filename) {
        final Path file = new File(filename).toPath();

        try (final var lines = Files.lines(file)) {
            this.traces = lines
                    .map(DebugTrace::fromLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void trace(String fileName, String methodName, Long lineNumber, Map<String, String> variables) {
        final var debugTrace = new DebugTrace(fileName, methodName, lineNumber, variables);
        traces.add(debugTrace);
    }

    public Trace next() {
        return null;
    }

    public Trace previous() {
        return null;
    }

    public List<DebugTrace> getTraces() {
        return traces;
    }
}
