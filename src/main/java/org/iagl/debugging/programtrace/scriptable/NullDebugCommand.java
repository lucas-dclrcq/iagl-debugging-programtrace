package org.iagl.debugging.programtrace.scriptable;

public class NullDebugCommand extends DebugCommand {

    @Override
    public Boolean executeOn(ScriptableDebugger dbg) {
        dbg.unknownCommandRequest(getParameters()[0]);
	return false;
    }
}
