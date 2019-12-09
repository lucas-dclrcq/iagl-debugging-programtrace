package org.iagl.debugging.programtrace.scriptable;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.command.DebugCommand;
import org.iagl.debugging.programtrace.command.NullDebugCommand;


import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ScriptableDebuggerCommandLineInterface {

    private ScriptableDebugger dbg;
    private Scanner commandScanner;
    private Map<String, DebugCommand> commandMap;

    public ScriptableDebuggerCommandLineInterface(ScriptableDebugger controlledDebugger) {
        this.dbg = controlledDebugger;
        commandScanner = new Scanner(System.in);
        commandMap = new HashMap<>();
//        commandMap.put("step", new StepDebugCommand());
    }

//    public void waitForInput(Event ev) {
//        DebugCommand cmd = scanCommand(ev);
//        if(!dbg.executeCommand(cmd))
//          waitForInput(ev);
//    }

    public DebugCommand scanCommand(Event ev) {
        System.out.println(ev.toString());
        String[] inputCommand = commandScanner.nextLine().split(" ");
        System.out.println(inputCommand.toString());
        DebugCommand cmd = getCommandFromInput(inputCommand[0]);
        System.out.println(cmd.toString());
        return cmd;
    }

    private DebugCommand getCommandFromInput(String inputCommand) {
        return commandMap.containsKey(inputCommand) ? commandMap.get(inputCommand) : new NullDebugCommand();
    }
}
