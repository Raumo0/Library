package com.netcracker.library.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by raumo0 on 17.10.16.
 */
public class LibraryView {
    static final String MSG_COMMAND_NOT_FOUND = "Command not found";
    static final String MSG_DELIM = "==========================================";

    Map<String, Command> commands;

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
        do {
            System.out.print("> ");
            String fullCommand = scanner.nextLine();
            ParsedCommand pc = new ParsedCommand(fullCommand);
            if (pc.command == null || "".equals(pc.command)) {
                continue;
            }
            Command cmd = commands.get(pc.command.toUpperCase());
            if (cmd == null) {
                System.out.println(MSG_COMMAND_NOT_FOUND);
                continue;
            }
            result = cmd.execute(pc.args);
        } while (result);
    }
}
