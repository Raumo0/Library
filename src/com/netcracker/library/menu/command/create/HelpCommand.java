package com.netcracker.library.menu.command.create;

import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.command.CreateCommand;

/**
 * Created by raumo0 on 30.10.16.
 */
public class HelpCommand implements CreateEntityCommand {
    private CreateCommand commandProcessor;

    public HelpCommand(CreateCommand commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Available commands:\n" + LibraryView.getMSG_DELIM());
            for (CreateEntityCommand cmd : commandProcessor.getCommands().values()) {
                System.out.println("\n" + cmd.getName() + ": " + cmd.getDescription());
            }
            System.out.println(LibraryView.getMSG_DELIM());
        } else {
            for (String cmd : args) {
                System.out.println("Help for command " + cmd + ":\n" + LibraryView.getMSG_DELIM());
                CreateEntityCommand command = commandProcessor.getCommands().get(cmd.toUpperCase());
                if (command == null) {
                    System.out.println(LibraryView.getMsgCommandNotFound());
                } else {
                    command.printHelp();
                }
                System.out.println(LibraryView.getMSG_DELIM());
            }
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "HELP";
    }

    @Override
    public String getDescription() {
        return "Prints list of available commands.\n" +
                "Use \"HELP <command>\" or \"HELP\" for\n" +
                "print all available command list.";
    }
}
