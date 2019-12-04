package org.iagl.debugging.programtrace.trace;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ProgramTrace {
    private static ProgramTrace INSTANCE;

    private final List<Trace> traces;
    private final ListIterator<Trace> tracesIterator;
    private final TraceLevel configuredTraceTraceLevel;

    /**
     * Load traces from a file
     *
     * @param filename The path to a file containing serialized traces
     * @return An instance of ProgramTrace
     */
    public static ProgramTrace fromFile(String filename) {
        return new ProgramTrace(filename);
    }

    /**
     * Return the current instance of ProgramTrace, it will create one if it does not exists
     *
     * @return An instance of ProgramTrace
     */
    public static ProgramTrace getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProgramTrace();
        }

        return INSTANCE;
    }

    private ProgramTrace() {
        this.traces = new ArrayList<>();
        this.tracesIterator = traces.listIterator();
        this.configuredTraceTraceLevel = readTraceLevelFromProperties().orElse(TraceLevel.FAIBLE);
    }

    private ProgramTrace(String filename) {
        this.configuredTraceTraceLevel = readTraceLevelFromProperties().orElse(TraceLevel.FAIBLE);
        final Path file = new File(filename).toPath();

        try {
            this.traces = Files.lines(file)
                    .map(Trace::fromLine)
                    .collect(Collectors.toList());
            this.tracesIterator = traces.listIterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toFile(String filename) {

    }

    public void trace(TraceLevel traceLevel, String value) {
        if (traceLevel.isEqualOrSuperiorTo(configuredTraceTraceLevel)) {
            final String name = Thread.currentThread().getStackTrace()[2].getMethodName();
            final Trace trace = new Trace(traceLevel, LocalDateTime.now(), name, value);
            traces.add(trace);
        }
    }

    public Trace next() {
        if (this.tracesIterator.hasNext()) {
            return this.tracesIterator.next();
        }

        return null;
    }

    public Trace previous() {
        if (this.tracesIterator.hasPrevious()) {
            return this.tracesIterator.previous();
        }

        return null;
    }

    Optional<TraceLevel> readTraceLevelFromProperties() {
        try (InputStream fis = ProgramTrace.class.getClassLoader().getResourceAsStream("application.properties")) {
            final Properties properties = new Properties();
            properties.load(fis);
            final Optional<String> logLevel = Optional.ofNullable(properties.getProperty("log.level"));
            return logLevel.map(TraceLevel::valueOf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Could not read properties file");
    }


    public List<Trace> getTraces() {
        return traces;
    }
}
