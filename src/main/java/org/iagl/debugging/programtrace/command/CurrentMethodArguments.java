package org.iagl.debugging.programtrace.command;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.Method;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class CurrentMethodArguments extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {

        if (debugger.isReplayModeActivated()) {
            System.err.println("You cannot use argument command in replay mode");
            return false;
        } else {
            final var locatableEvent = (LocatableEvent) event;

            try {
                final var stackFrame = locatableEvent.thread().frame(0);
                final Method method = stackFrame.location().method();
                method.arguments()
                        .forEach(localVariable -> {
                            System.out.println(localVariable.name() + " -> " + stackFrame.getValue(localVariable).toString());
                        });
            } catch (IncompatibleThreadStateException | AbsentInformationException e) {
                System.out.println("Could not retrieve current method arguments");
            }

            return false;
        }
    }
}
