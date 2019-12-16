package org.iagl.debugging.programtrace.command;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

import java.util.ArrayList;

public class ListBreakPoints extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        final var breakPointLines = debugger.getBreakPointLines();

        System.out.println(breakPointLines);

        return false;
    }
}
