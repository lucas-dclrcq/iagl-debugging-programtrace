package org.iagl.debugging.programtrace.command;

import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.StackFrame;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.command.DebugCommand;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class CurrentMethod extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        final var locatableEvent = (LocatableEvent) event;
        try {
            final var methodName = locatableEvent.thread().frame(0).location().method().name();
            System.out.println("Current method name : " + methodName);
        } catch (IncompatibleThreadStateException e) {
            System.out.println("Could not retrieve current method name");
        }
        debugger.enableStepRequest(locatableEvent);
        return true;
    }
}
