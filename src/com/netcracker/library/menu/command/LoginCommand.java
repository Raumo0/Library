package com.netcracker.library.menu.command;

import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.command.login.LoginLibrarianCommand;
import com.netcracker.library.menu.command.login.LoginPersonCommand;
import com.netcracker.library.menu.command.login.LoginReaderCommand;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by raumo0 on 21.10.16.
 */
public class LoginCommand implements Command {
    private Map<String, Command> commands;

    public LoginCommand() {
        commands = new TreeMap<>();
        LoginPersonCommand cmd = new LoginReaderCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new LoginLibrarianCommand();
        commands.put(cmd.getName(), cmd);
    }

    @Override
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Write the necessary arguments.\n" + LibraryView.getMSG_DELIM() + "\n " + getDescription());
            System.out.println(LibraryView.getMSG_DELIM());
        } else {
            for (String cmd : args) {
                Command command = commands.get(cmd.toUpperCase());
                if (command == null) {
                    System.out.println(LibraryView.getMsgCommandNotFound());
                } else {
                    command.execute(null);
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
        return "LOGIN";
    }

    @Override
    public String getDescription() {
        return String.format("Log into library to start sharing\n" +
                "and connecting. Use \"LOGIN <person>\", where\n" +
                "person id READER or LIBRARIAN.");    }
}
