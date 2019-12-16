package org.iagl.debugging.programtrace.scriptable;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.cli.CommandType;
import org.iagl.debugging.programtrace.command.DebugCommand;


import java.util.Scanner;

public class ScriptableDebuggerCommandLineInterface {

    private ScriptableDebugger dbg;
    private Scanner commandScanner;

    public ScriptableDebuggerCommandLineInterface(ScriptableDebugger controlledDebugger) {
        this.dbg = controlledDebugger;
        commandScanner = new Scanner(System.in);
    }

    public DebugCommand waitForInput(Event ev, String... args) {
        DebugCommand command = scanCommand(ev);

        if(!command.execute(this.dbg, ev, args)) {
            return waitForInput(ev);
        }

        return command;
    }

    public DebugCommand scanCommand(Event ev) {
        System.out.println(ev.toString());
        String[] inputCommand = commandScanner.nextLine().split(" ");
        System.out.println(inputCommand.toString());
        return  CommandType.fromInput(inputCommand[0]);
    }

}
