package org.iagl.debugging.programtrace.command;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class Temporaries extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        final var locatableEvent = (LocatableEvent) event;

        try {
            final var formattedVariables = ScriptableDebugger.getFormattedVariables(locatableEvent.thread().frame(0));

            formattedVariables.forEach((key, value) -> {
                System.out.println(key + " -> " + value);
            });

        } catch (IncompatibleThreadStateException | AbsentInformationException e) {
            System.out.println("Could not retrieve frame's variables");
        }

        return false;
    }
}
