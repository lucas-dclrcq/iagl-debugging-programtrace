package org.iagl.debugging.programtrace.command;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;
import org.iagl.debugging.programtrace.trace.debugger.DebuggerProgramTrace;

import java.util.Map;

public class PrintVar extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        final var locatableEvent = (LocatableEvent) event;

        if (parameters.length != 1) {
            System.out.println("Wanted variable name is expected as argument");
            return false;
        }

        final var variableName = parameters[0];
        Map<String, String> formattedVariables = null;

        if (debugger.isReplayModeActivated()) {
            formattedVariables = DebuggerProgramTrace.getInstance().current().getVariables();
        } else {
            try {
                formattedVariables = debugger.getFormattedVariables(locatableEvent.thread().frame(0));
            } catch (IncompatibleThreadStateException | AbsentInformationException e) {
                System.out.println("Could not retrieve frame's variables");
            }
        }

        if (formattedVariables != null) {
            try {
                final var variableValue = formattedVariables.get(variableName);
                System.out.println(variableName + " => " + variableValue);
            } catch (NullPointerException e) {
                System.out.println("No variable with name " + variableName + " was found");
            }
        }

        return false;
    }
}
