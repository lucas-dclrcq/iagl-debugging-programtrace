package org.iagl.debugging.programtrace.command;

import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;


public class Step extends DebugCommand {

    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        LocatableEvent locatableEvent = (LocatableEvent) event;
        debugger.enableStepRequest(locatableEvent);
        return true;
    }
}
