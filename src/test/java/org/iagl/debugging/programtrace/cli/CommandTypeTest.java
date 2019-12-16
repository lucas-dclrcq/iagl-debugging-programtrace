package org.iagl.debugging.programtrace.cli;

import org.iagl.debugging.programtrace.command.DebugCommand;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.assertj.core.api.Assertions.assertThat;

class CommandTypeTest {

    @ParameterizedTest
    @CsvSource(value = {
            "step:Step", "step-over:StepOver", "arguments:CurrentMethodArguments",
            "continue:Continue", "frame:Frame", "temporaries:Temporaries", "stack:Stack",
            "stack-top:StackTop", "receiver:Receiver", "sender:Sender",
            "receiver-variables:ReceiverVariables", "method:CurrentMethod","print-var:PrintVar",
            "break:SetBreakPoint", "breakpoints:ListBreakPoints", "break-once:SetBreakOnce",
            "break-on-count:SetBreakOnCount", "break-before-method-call:SetBreakBeforeCall" },
            delimiter = ':')
    void arguments(String input, String commandClass) {
        // act
        DebugCommand argumentsCommand = CommandType.fromInput(input);

        // assert
        assertThat(argumentsCommand).extracting(clazz -> clazz.getClass().getSimpleName()).isEqualTo(commandClass);

    }
}
