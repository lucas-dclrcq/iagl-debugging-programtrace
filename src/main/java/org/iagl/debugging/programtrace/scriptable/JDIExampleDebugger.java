package org.iagl.debugging.programtrace.scriptable;

public class JDIExampleDebugger {
    public static void main(String[] args) throws Exception {

        ScriptableDebugger debuggerInstance = new ScriptableDebugger();
        debuggerInstance.attachTo(JDIExampleDebuggee.class, 4);

    }
}

