package com.netcracker.library.menu;

import com.netcracker.library.menu.command.*;
import com.netcracker.library.menu.command.CreateCommand;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by raumo0 on 17.10.16.
 */
public class LibraryView {
    private static final String MSG_COMMAND_NOT_FOUND = "Command not found";
    private static final String MSG_DELIM = "==========================================";

    private Map<String, Command> commands;

    private String consoleEncoding;

    public LibraryView(String consoleEncoding) {
        commands = new TreeMap<>();
        Command cmd = new HelpCommand(this);
        commands.put(cmd.getName(), cmd);
        cmd = new ExitCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new CreateCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new LoginCommand();
        commands.put(cmd.getName(), cmd);
        this.consoleEncoding = consoleEncoding;
    }

    public void execute() {
        boolean result = true;
        Scanner scanner = new Scanner(System.in, consoleEncoding);
        Command cmd = commands.get(new ParsedCommand("HELP").getCommand().toUpperCase());
        cmd.execute(null);
        do {
            System.out.print("Main menu:" + "> ");
            String fullCommand = scanner.nextLine();
            ParsedCommand pc = new ParsedCommand(fullCommand);
            if (pc.getCommand() == null || "".equals(pc.getCommand())) {
                continue;
            }
            cmd = commands.get(pc.getCommand().toUpperCase());
            if (cmd == null) {
                System.out.println(MSG_COMMAND_NOT_FOUND);
                continue;
            }
            result = cmd.execute(pc.getArgs());
        } while (result);
    }

    public static String getMsgCommandNotFound() {
        return MSG_COMMAND_NOT_FOUND;
    }

    public static String getMSG_DELIM() {
        return MSG_DELIM;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
