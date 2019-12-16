package org.iagl.debugging.programtrace.cli;

import org.iagl.debugging.programtrace.command.*;


import java.util.Arrays;

public enum CommandType {
    REPLAY(new Replay()),
    PREVIOUS(new Previous()),
    CONTINUE(new Continue()),
    FRAME(new Frame()),
    PRINT_VAR(new PrintVar()),
    RECEIVER(new Receiver()),
    RECEIVER_VARIABLES(new ReceiverVariables()),
    METHOD(new CurrentMethod()),
    ARGUMENTS(new CurrentMethodArguments()),
    SENDER(new Sender()),
    BREAKPOINTS(new ListBreakPoints()),
    BREAK_ONCE(new SetBreakOnce()),
    BREAK_ON_COUNT(new SetBreakOnCount()),
    BREAK_BEFORE_METHOD_CALL(new SetBreakBeforeCall()),
    BREAK(new SetBreakPoint()),
    STACK(new Stack()),
    STACK_TOP(new StackTop()),
    STEP(new Step()),
    STEP_OVER(new StepOver()),
    TEMPORARIES(new Temporaries());

    private DebugCommand command;

    CommandType(DebugCommand debugCommand) {
        this.command = debugCommand;
    }

    public static DebugCommand fromInput(String strCommand) {
        String replaced = strCommand.replace("-", "_");
        return Arrays.stream(values())
                     .filter(commandType -> commandType.name().equalsIgnoreCase(replaced))
                     .findFirst()
                     .map(CommandType::getCommand)
                     .orElse(new NullDebugCommand());
    }

    public DebugCommand getCommand() {
        return command;
    }
}
