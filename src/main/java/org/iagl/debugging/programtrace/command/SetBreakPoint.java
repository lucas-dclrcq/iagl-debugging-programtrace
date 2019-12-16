package org.iagl.debugging.programtrace.command;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class SetBreakPoint extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        try {
            debugger.setBreakPoint(debugger.getDebugClassName(), Integer.parseInt(parameters[0]));
        } catch (AbsentInformationException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
