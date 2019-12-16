package org.iagl.debugging.programtrace.command;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;
import org.iagl.debugging.programtrace.trace.debugger.DebuggerProgramTrace;

import java.util.Map;

public class Temporaries extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        final var locatableEvent = (LocatableEvent) event;
        Map<String, String> variables = null;

        if (debugger.isReplayModeActivated()) {
            variables = DebuggerProgramTrace.getInstance().current().getVariables();
        } else {
            try {
                variables = debugger.getFormattedVariables(locatableEvent.thread().frame(0));
            } catch (IncompatibleThreadStateException | AbsentInformationException e) {
                System.out.println("Could not retrieve frame's variables");
            }
        }

        if (variables != null) {
            variables.forEach((s, s2) -> System.out.println(s + " -> " + s2));

            if (variables.isEmpty()) {
                System.out.println("No variables in current frame");
            }
        } else {
            System.out.println("Could not retrieve variables");
        }


        return false;
    }
}
