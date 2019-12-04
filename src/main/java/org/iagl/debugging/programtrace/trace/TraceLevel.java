package org.iagl.debugging.programtrace.trace;

import java.util.Arrays;
import java.util.List;

public enum TraceLevel {
    FAIBLE,
    MOYEN,
    IMPORTANT,
    CRITIQUE;

    public boolean isEqualOrSuperiorTo(TraceLevel logTraceLevel) {
        final List<TraceLevel> traceLevelValues = Arrays.asList(TraceLevel.values());
        return traceLevelValues.indexOf(this) >= traceLevelValues.indexOf(logTraceLevel);
    }
}
