package com.netcracker.library.menu.command;

import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.ParsedCommand;
import com.netcracker.library.menu.command.login.*;
import com.netcracker.library.menu.command.login.ExitCommand;
import com.netcracker.library.menu.command.login.HelpCommand;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by raumo0 on 21.10.16.
 */
public class LoginCommand implements Command {
    private Map<String, LoginPersonCommand> commands;

    public LoginCommand() {
        commands = new TreeMap<>();
        LoginPersonCommand cmd = new LoginReaderCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new LoginLibrarianCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new HelpCommand(this);
        commands.put(cmd.getName(), cmd);
        cmd = new ExitCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new BackCommand();
        commands.put(cmd.getName(), cmd);
    }

    @Override
    public boolean execute(String... args) {
        boolean result = true;
        Scanner scanner = new Scanner(System.in);
        LoginPersonCommand cmd = commands.get(new ParsedCommand("HELP").getCommand().toUpperCase());
        cmd.execute(null);
        do {
            System.out.print("Login menu:> ");
            String fullCommand = scanner.nextLine();
            ParsedCommand pc = new ParsedCommand(fullCommand);
            if (pc.getCommand() == null || "".equals(pc.getCommand())) {
                continue;
            }
            cmd = commands.get(pc.getCommand().toUpperCase());
            if (cmd == null) {
                System.out.println(LibraryView.getMsgCommandNotFound());
                continue;
            }
            result = cmd.execute(pc.getArgs());
        } while (result);
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "2";
    }

    @Override
    public String getDescription() {
        return String.format("Log into library to start sharing\n" +
                "and connecting. Use \"LOGIN <person>\", where\n" +
                "person id READER or LIBRARIAN.");    }

    public Map<String, LoginPersonCommand> getCommands() {
        return commands;
    }
}
