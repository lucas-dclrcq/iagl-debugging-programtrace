package org.iagl.debugging.programtrace.command;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.StackFrame;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;


import java.util.Map;

public class Frame extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        LocatableEvent locatableEvent = (LocatableEvent) event;

        try {
            StackFrame stackFrame = locatableEvent.thread().frame(0);
            System.out.println("Line : "+ stackFrame.location().lineNumber());
            System.out.println("Class => " + stackFrame.location().sourceName());
            System.out.println("Method => " + stackFrame.location().method().name());
            Map<String, String> formattedVariables = debugger.getFormattedVariables(stackFrame);
            System.out.println("Vars :");
            formattedVariables.forEach((name, value) -> System.out.println("\t" + name + " => " + value));
        } catch (IncompatibleThreadStateException | AbsentInformationException e) {
            e.printStackTrace();
        }

        return false;
    }
}
