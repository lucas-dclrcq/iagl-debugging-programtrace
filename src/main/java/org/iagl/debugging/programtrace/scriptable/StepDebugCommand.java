package org.iagl.debugging.programtrace.scriptable;

import com.sun.jdi.event.LocatableEvent;

public class StepDebugCommand extends DebugCommand {
    @Override
    public Boolean executeOn(ScriptableDebugger dbg) {
        dbg.enableStepRequest((LocatableEvent) getEvent());
        return true;
    }
}
