package org.iagl.debugging.programtrace.scriptable;

import com.sun.jdi.event.LocatableEvent;
import org.iagl.debugging.programtrace.command.DebugCommand;

public class StepDebugCommand extends DebugCommand {
    @Override
    public boolean executeOn(ScriptableDebugger dbg) {
        dbg.enableStepRequest((LocatableEvent) getEvent());
        return true;
    }
}
