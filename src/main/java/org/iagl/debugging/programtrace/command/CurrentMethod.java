package org.iagl.debugging.programtrace.command;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.command.DebugCommand;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class CurrentMethod extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        return false;
    }
}
