package org.iagl.debugging.programtrace;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ProgramTrace {
    private static ProgramTrace INSTANCE;

    private List<Trace> traces;
    private TraceLevel configuredTraceTraceLevel;

    public static ProgramTrace fromFile(String filename) {
        return new ProgramTrace(filename);
    }

    public static ProgramTrace getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProgramTrace();
        }

        return INSTANCE;
    }

    private ProgramTrace() {
        this.traces = new ArrayList<>();
        this.configuredTraceTraceLevel = readTraceLevelFromProperties();
    }

    private ProgramTrace(String filename) {
        final Path file = new File(filename).toPath();
        try {
            this.traces = Files.lines(file)
                    .map(Trace::fromLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void trace(TraceLevel traceLevel, String value) {
        if (traceLevel.isEqualOrSuperiorTo(configuredTraceTraceLevel)) {
            final String name = Thread.currentThread().getStackTrace()[2].getMethodName();
            final Trace trace = new Trace(traceLevel, LocalDateTime.now(), name, value);
            traces.add(trace);
            System.out.println(trace.toString());
        }
    }

    private TraceLevel readTraceLevelFromProperties() {
        try (InputStream fis = ProgramTrace.class.getClassLoader().getResourceAsStream("application.properties")) {
            final Properties properties = new Properties();
            properties.load(fis);
            final String logLevel = properties.getProperty("log.level");
            return TraceLevel.valueOf(logLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Could not read properties file");
    }

    public List<Trace> getTraces() {
        return traces;
    }
}
