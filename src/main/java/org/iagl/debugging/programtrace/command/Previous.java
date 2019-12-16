package org.iagl.debugging.programtrace.command;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.exception.CannotMoveCursorException;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;
import org.iagl.debugging.programtrace.trace.debugger.DebugTrace;
import org.iagl.debugging.programtrace.trace.debugger.DebuggerProgramTrace;

public class Previous extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        if (!debugger.isReplayModeActivated()) {
            System.out.println("Previous command only works in replay mode. Type 'replay on' to activate it");
        } else {
            try {
                final var previous = DebuggerProgramTrace.getInstance().previous();
                System.out.println("Stopped at: " + previous.getFileName() + ":" + previous.getLineNumber());
            } catch (CannotMoveCursorException e) {
                System.out.println(e);
            }

        }

        return false;
    }
}
