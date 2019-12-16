package org.iagl.debugging.programtrace.debugger;

public class JDIExampleDebuggee {

    public static void main(String[] args) {
        String jpda = "Java Platform Debugger Architecture";
        nested();
        System.out.println("Hi Everyone, Welcome to " + jpda); //add a break point here
        double i = Math.pow(2, 2) + 1;

        String jdi = "Java Debug Interface"; //add a break point here and also stepping in here
        String text = "Today, we'll dive into " + jdi;
        System.out.println(text);
    }

    public static void nested() {
        System.out.println("I am nested");
    }

}
