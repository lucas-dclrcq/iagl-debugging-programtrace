package org.iagl.debugging.programtrace.scriptable;

import com.sun.jdi.event.Event;

public abstract class DebugCommand {

    private String[] parameters;
    private Event event;

    public abstract Boolean executeOn(ScriptableDebugger dbg);

    public void setParameters(String[] inputParameters) {
        this.parameters = inputParameters;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
