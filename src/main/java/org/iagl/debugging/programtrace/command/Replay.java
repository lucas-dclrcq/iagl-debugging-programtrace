package org.iagl.debugging.programtrace.command;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class Replay extends DebugCommand {
    @Override
    public boolean execute(ScriptableDebugger debugger, Event event, String... parameters) {
        if (parameters[0].equals("on")) {
            debugger.activateReplayMode();
            System.out.println("Activating replay mode");
        } else if (parameters[0].equals("off")) {
            debugger.deactivateReplayMode();
            System.out.println("Deactivating replay mode");
        } else {
            System.out.println("Unknown argument for replay command");
        }

        return false;
    }
}
