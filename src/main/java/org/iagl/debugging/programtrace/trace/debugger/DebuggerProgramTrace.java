package org.iagl.debugging.programtrace.trace.debugger;

import org.iagl.debugging.programtrace.exception.CannotMoveCursorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DebuggerProgramTrace {
    private static DebuggerProgramTrace INSTANCE;

    private final List<DebugTrace> traces;
    private int cursor;

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
        this.cursor = 0;
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

        this.cursor = -1;
    }

    /**
     * Add a trace
     *
     * @param fileName
     * @param methodName
     * @param lineNumber
     * @param variables
     */
    public void trace(String fileName, String methodName, Long lineNumber, Map<String, String> variables) {
        final var debugTrace = new DebugTrace(fileName, methodName, lineNumber, variables);
        traces.add(debugTrace);
    }

    public DebugTrace next() throws CannotMoveCursorException {
        if (cursorCanGoForward()) {
            cursor++;
        } else {
            throw new CannotMoveCursorException("No trace going forward");
        }

        return getTraceAtCursor();
    }

    public DebugTrace current() {
        return getTraceAtCursor();
    }

    public DebugTrace previous() throws CannotMoveCursorException {
        if (cursorCanGoBackward()) {
            cursor--;
        } else {
            throw new CannotMoveCursorException("No trace going backward");
        }

        return getTraceAtCursor();
    }

    public DebugTrace reset() throws CannotMoveCursorException {
        checkTracesIsNotEmpty();
        this.cursor = 0;
        return getTraceAtCursor();
    }

    public List<DebugTrace> getTraces() {
        return traces;
    }

    int getCursor() {
        return cursor;
    }

    DebugTrace getTraceAtCursor() {
        return traces.get(cursor);
    }

    boolean cursorCanGoForward() throws CannotMoveCursorException {
        checkTracesIsNotEmpty();
        return cursor <= traces.size() - 2;
    }

    boolean cursorCanGoBackward() throws CannotMoveCursorException {
        checkTracesIsNotEmpty();
        return cursor >= 1;
    }

    void checkTracesIsNotEmpty() throws CannotMoveCursorException {
        if (traces.isEmpty()) {
            throw new CannotMoveCursorException("No traces present.");
        }
    }
}
