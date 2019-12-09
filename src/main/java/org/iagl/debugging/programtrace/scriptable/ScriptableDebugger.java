package org.iagl.debugging.programtrace.scriptable;

import com.sun.jdi.*;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.VMStartException;
import com.sun.jdi.event.*;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.ClassPrepareRequest;
import com.sun.jdi.request.StepRequest;
import org.iagl.debugging.programtrace.trace.debugger.DebuggerProgramTrace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ScriptableDebugger {

    private DebuggerProgramTrace programTrace;

    private Class debugClass;
    private ArrayList<Integer> breakPointLines;
    private VirtualMachine vm;
    private ScriptableDebuggerCommandLineInterface commandLineInterface;

    public ScriptableDebugger() {
        this.programTrace = DebuggerProgramTrace.getInstance();
    }

    public void addBreakPointAtLine(int lineNumber) {
        this.breakPointLines.add(lineNumber);
    }

    /**
     * Sets the debug class as the main argument in the connector and launches the VM
     *
     * @return VirtualMachine
     * @throws IOException
     * @throws IllegalConnectorArgumentsException
     * @throws VMStartException
     */
    public VirtualMachine connectAndLaunchVM() throws IOException, IllegalConnectorArgumentsException, VMStartException {
        LaunchingConnector launchingConnector = Bootstrap.virtualMachineManager().defaultConnector();
        Map<String, Connector.Argument> arguments = launchingConnector.defaultArguments();
        arguments.get("main").setValue(debugClass.getName());//debugClass.getName());
        VirtualMachine vm = launchingConnector.launch(arguments);
        return vm;
    }

    /**
     * Creates a request to prepare the debug class, add filter as the debug class and enables it
     *
     * @param vm
     */
    public void enableClassPrepareRequest(VirtualMachine vm) {
        ClassPrepareRequest classPrepareRequest = vm.eventRequestManager().createClassPrepareRequest();
        System.out.println(debugClass.getName());
        classPrepareRequest.addClassFilter(debugClass.getName());
        classPrepareRequest.enable();
    }

    /***********
     * DEBUGGER CONTROL INTERFACE
     * /***********

     /***
     *
     * @param lineNumber
     * @param className
     * @throws AbsentInformationException
     */

    public void setBreakPoint(String className, int lineNumber) throws AbsentInformationException {
        for (ReferenceType targetClass : vm.allClasses())
            if (targetClass.name().equals(className)) {
                Location location = targetClass.locationsOfLine(lineNumber).get(0);
                System.out.println("Setting breakpoint on: " + location.toString());
                BreakpointRequest bpReq = vm.eventRequestManager().createBreakpointRequest(location);
                bpReq.enable();
            }
    }

    /**
     * Enables step request for a break point
     *
     * @param event
     */
    public void enableStepRequest(LocatableEvent event) {
        StepRequest stepRequest = vm.eventRequestManager().createStepRequest(event.thread(), StepRequest.STEP_MIN, StepRequest.STEP_OVER);
        stepRequest.enable();

    }

    public void unknownCommandRequest(String commandName) {
        System.out.println("Error: unknown command " + commandName);
    }

    public Boolean executeCommand(DebugCommand cmd) {
        return cmd.executeOn(this);
    }

    /***********
     * DEBUGGER CREATION
     * ***********/
    public void attachTo(Class debuggeeClass, int attachPoint) {
        this.debugClass = debuggeeClass;
        this.breakPointLines = new ArrayList<>();

        try {
            vm = connectAndLaunchVM();

            addBreakPointAtLine(attachPoint);
            enableClassPrepareRequest(vm);

            startDebugger();

        } catch (VMStartException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (VMDisconnectedException e) {
            e.printStackTrace();
            System.out.println("Virtual Machine is disconnected: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startDebugger() throws VMDisconnectedException, InterruptedException {
        commandLineInterface = new ScriptableDebuggerCommandLineInterface(this);
        EventSet eventSet;
        while ((eventSet = vm.eventQueue().remove()) != null) {
            System.out.println(eventSet.toString());
            for (Event event : eventSet) {
                if (event instanceof ClassPrepareEvent) {
                    SetBreakpointDebugCommand setBPCommand = new SetBreakpointDebugCommand();
                    setBPCommand.setParameters(new String[]{"break", "6", debugClass.getName()});
                    executeCommand(setBPCommand);
                }

                if (event instanceof BreakpointEvent) {
                    commandLineInterface.waitForInput(event);
                }

                if (event instanceof StepEvent) {
                    event.request().disable();
                    System.out.println("Stopped at: " + ((StepEvent) event).location().toString());
                    commandLineInterface.waitForInput(event);
                }
                vm.resume();
            }
        }
    }

}
