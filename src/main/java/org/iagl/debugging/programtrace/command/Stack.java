package org.iagl.debugging.programtrace.command;

import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.StackFrame;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class Stack extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        LocatableEvent locatableEvent = (LocatableEvent) event;
        try {
            locatableEvent.thread().frames().stream()
                          .map(StackFrame::location)
                          .forEach(location -> System.out.println(location.method()));
        } catch (IncompatibleThreadStateException e) {
            e.printStackTrace();
        }
        return false;
    }
}
