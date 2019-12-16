package org.iagl.debugging.programtrace.command;

import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.exception.CannotMoveCursorException;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;
import org.iagl.debugging.programtrace.trace.debugger.DebugTrace;
import org.iagl.debugging.programtrace.trace.debugger.DebuggerProgramTrace;


public class Step extends DebugCommand {

    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        if (debugger.isReplayModeActivated()) {
            try {
                final var next = DebuggerProgramTrace.getInstance().next();
                System.out.println("Stopped at: " + next.getFileName() + ":" + next.getLineNumber());
            } catch (CannotMoveCursorException e) {
                System.out.println(e);
            }
            return false;
        } else {
            LocatableEvent locatableEvent = (LocatableEvent) event;
            debugger.enableStepRequest(locatableEvent);
            return true;
        }
    }
}
