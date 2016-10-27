package com.netcracker.library.menu.command.login;

import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.command.Command;

/**
 * Created by raumo0 on 27.10.16.
 */
public class LoginHelpCommand implements Command {
    private LoginReaderCommand commandProcessor;

    public LoginHelpCommand(LoginReaderCommand commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Avaliable commands:\n" + LibraryView.getMSG_DELIM());
            for (Command cmd : commandProcessor.getCommands().values()) {
                System.out.println("\n" + cmd.getName() + ": " + cmd.getDescription());
            }
            System.out.println(LibraryView.getMSG_DELIM());
        } else {
            for (String cmd : args) {
                System.out.println("Help for command " + cmd + ":\n" + LibraryView.getMSG_DELIM());
                Command command = commandProcessor.getCommands().get(cmd.toUpperCase());
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
                "Use \"HELP <command>\".";
    }
}
