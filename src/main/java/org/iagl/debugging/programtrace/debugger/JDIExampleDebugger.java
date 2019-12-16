package org.iagl.debugging.programtrace.debugger;

import org.iagl.debugging.programtrace.scriptable.ScriptableDebugger;

public class JDIExampleDebugger {
    public static void main(String[] args) {

        ScriptableDebugger debuggerInstance = new ScriptableDebugger();
        debuggerInstance.attachTo(JDIExampleDebuggee.class, 4);

    }
}

