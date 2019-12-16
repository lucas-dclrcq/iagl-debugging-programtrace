package org.iagl.debugging.programtrace.trace.debugger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.iagl.debugging.programtrace.trace.basic.Trace;

import java.util.Base64;
import java.util.Map;
import java.util.Objects;

public class DebugTrace {
    private String fileName;
    private String methodName;
    private Long lineNumber;
    private Map<String, String> variables;

    public DebugTrace(String fileName, String methodName, Long lineNumber, Map<String, String> variables) {
        this.fileName = fileName;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.variables = variables;
    }

    public static DebugTrace fromLine(String line) {
        final String[] splittedLine = line.split(Trace.TRACE_LINE_SEPARATOR);
        return new DebugTrace(
                splittedLine[0],
                splittedLine[1],
                Long.parseLong(splittedLine[2]),
                DebugTrace.getDeserializedVariables(splittedLine[3])
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DebugTrace)) return false;
        DebugTrace that = (DebugTrace) o;
        return Objects.equals(fileName, that.fileName) &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(lineNumber, that.lineNumber) &&
                Objects.equals(variables, that.variables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, methodName, lineNumber, variables);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%d,%s", fileName, methodName, lineNumber, getSerializedVariables());
    }

    String getSerializedVariables() {
        var gson = new Gson();
        final var json = gson.toJson(variables);
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    static Map<String, String> getDeserializedVariables(String serializedVariables) {
        var gson = new Gson();
        final var decodedSerializedVariables = new String(Base64.getDecoder().decode(serializedVariables));
        return gson.fromJson(decodedSerializedVariables, new TypeToken<Map<String, String>>() {}.getType());
    }

    public String getFileName() {
        return fileName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Long getLineNumber() {
        return lineNumber;
    }
}
