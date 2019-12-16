package org.iagl.debugging.programtrace.trace.basic;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Trace {
    public final static String TRACE_LINE_SEPARATOR = ",";

    private TraceLevel traceLevel;
    private LocalDateTime horodatage;
    private String methodName;
    private String value;

    public Trace(TraceLevel traceLevel, LocalDateTime horodatage, String methodName, String value) {
        this.traceLevel = traceLevel;
        this.horodatage = horodatage;
        this.methodName = methodName;
        this.value = value;
    }

    public static Trace fromLine(String line) {
        final List<String> traceElements = Arrays.stream(line.split(TRACE_LINE_SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());

        return new Trace(
                TraceLevel.valueOf(traceElements.get(1)),
                LocalDateTime.parse(traceElements.get(0)),
                traceElements.get(2),
                traceElements.get(3)
        );
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", horodatage, traceLevel, methodName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trace)) return false;
        Trace trace = (Trace) o;
        return traceLevel == trace.traceLevel &&
                horodatage.equals(trace.horodatage) &&
                methodName.equals(trace.methodName) &&
                value.equals(trace.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traceLevel, horodatage, methodName, value);
    }
}
