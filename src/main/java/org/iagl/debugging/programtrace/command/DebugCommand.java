package org.iagl.debugging.programtrace.command;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public abstract class DebugCommand {
    public abstract boolean execute(ScriptableDebugger debugger, Event event, String... parameters);
}
