package org.iagl.debugging.programtrace.command;

import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.StackFrame;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.command.DebugCommand;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;
import org.iagl.debugging.programtrace.trace.debugger.DebuggerProgramTrace;

public class CurrentMethod extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        if (debugger.isReplayModeActivated()) {
            try {
                System.out.println("Current method name : " + DebuggerProgramTrace.getInstance().current().getMethodName());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You should first start execution using step");
            }

            return false;
        } else {
            final var locatableEvent = (LocatableEvent) event;

            try {
                final var methodName = locatableEvent.thread().frame(0).location().method().name();
                System.out.println("Current method name : " + methodName);
            } catch (IncompatibleThreadStateException e) {
                System.out.println("Could not retrieve current method name");
            }

            return false;
        }
    }
}
