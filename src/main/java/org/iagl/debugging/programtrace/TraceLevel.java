package org.iagl.debugging.programtrace;

import java.util.List;

public enum TraceLevel {
    FAIBLE,
    MOYEN,
    IMPORTANT,
    CRITIQUE;

    public boolean isEqualOrSuperiorTo(TraceLevel logTraceLevel) {
        final List<TraceLevel> traceLevelValues = List.of(TraceLevel.values());
        return traceLevelValues.indexOf(this) >= traceLevelValues.indexOf(logTraceLevel);
    }
}
