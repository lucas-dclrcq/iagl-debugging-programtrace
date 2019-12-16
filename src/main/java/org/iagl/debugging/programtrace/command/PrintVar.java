package org.iagl.debugging.programtrace.command;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class PrintVar extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        final var locatableEvent = (LocatableEvent) event;

        if (parameters.length != 1) {
            System.out.println("Wanted variable name is expected as argument");
            return false;
        }

        final var variableName = parameters[0];

        try {
            final var formattedVariables = ScriptableDebugger.getFormattedVariables(locatableEvent.thread().frame(0));
            final var variableValue = formattedVariables.get(variableName);

            System.out.println(variableName + " => " + variableValue);

        } catch (IncompatibleThreadStateException | AbsentInformationException e) {
            System.out.println("Could not retrieve frame's variables");
        } catch (NullPointerException e) {
            System.out.println("No variable with name=" + variableName + " was found.");
        }

        return false;
    }
}
