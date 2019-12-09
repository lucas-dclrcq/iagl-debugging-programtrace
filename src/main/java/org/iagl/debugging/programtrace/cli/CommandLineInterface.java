package org.iagl.debugging.programtrace.cli;

import org.iagl.debugging.programtrace.command.DebugCommand;


import java.util.Scanner;

public class CommandLineInterface {

    public static void main(String[] args) {
        Scanner commandScanner = new Scanner(System.in);

        System.out.println();
        String[] inputCommand = commandScanner.nextLine().split(" ");
        DebugCommand command = CommandType.fromInput(inputCommand[0]);
        System.out.println(command.toString());
    }

}
