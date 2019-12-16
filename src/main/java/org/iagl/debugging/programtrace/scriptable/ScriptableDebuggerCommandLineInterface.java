package org.iagl.debugging.programtrace.scriptable;

import com.sun.jdi.event.Event;
import org.iagl.debugging.programtrace.cli.CommandType;
import org.iagl.debugging.programtrace.command.DebugCommand;

import java.util.Arrays;
import java.util.Scanner;

public class ScriptableDebuggerCommandLineInterface {

    private ScriptableDebugger dbg;
    private Scanner commandScanner;

    public ScriptableDebuggerCommandLineInterface(ScriptableDebugger controlledDebugger) {
        this.dbg = controlledDebugger;
        commandScanner = new Scanner(System.in);
    }

    public DebugCommand waitForInput(Event ev) {
        final var inputCommand = commandScanner.nextLine().split(" ");
        final var debugCommand = CommandType.fromInput(inputCommand[0]);
        final var arguments = Arrays.copyOfRange(inputCommand, 1, inputCommand.length);

        if(!debugCommand.execute(this.dbg, ev, arguments)) {
            return waitForInput(ev);
        }

        return debugCommand;
    }
}
