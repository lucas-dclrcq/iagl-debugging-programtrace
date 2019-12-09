package org.iagl.debugging.programtrace.cli;

import org.iagl.debugging.programtrace.command.*;


import java.util.Arrays;

public enum CommandType {
    CONTINUE(new Continue()),
    FRAME(new Frame()),
    LIST_BREAKPOINT(new ListBreakPoints()),
    PRINT_VAR(new PrintVar()),
    RECEIVER(new Receiver()),
    RECEIVER_VARIABLES(new ReceiverVariables()),
    METHOD(new CurrentMethod()),
    ARGUMENTS(new CurrentMethodArguments()),
    SENDER(new Sender()),
    BREAK_ONCE(new SetBreakOnce()),
    BREAK_ON_COUNT(new SetBreakOnCount()),
    BREAK_BEFORE_CALL(new SetBreakBeforeCall()),
    BREAK(new SetBreakPoint()),
    STACK(new Stack()),
    STACK_TOP(new StackTop()),
    STEP(new StepCommand()),
    STEP_OVER(new StepOver()),
    TEMPORARIES(new Temporaries());

    private DebugCommand command;

    CommandType(DebugCommand debugCommand) {
        this.command = debugCommand;
    }

    private DebugCommand fromInput(String strCommand) {
        return Arrays.stream(values())
                     .filter(commandType -> commandType.equals(valueOf(strCommand)))
                     .findFirst()
                     .map(commandType -> this.command)
                     .orElse(new NullDebugCommand());
    }
}
