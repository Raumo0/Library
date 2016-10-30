package com.netcracker.library.menu.command;

import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.ParsedCommand;
import com.netcracker.library.menu.command.create.*;
import com.netcracker.library.menu.command.create.ExitCommand;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by raumo0 on 21.10.16.
 */
public class CreateCommand implements Command {
    private Map<String, CreateEntityCommand> commands;

    public CreateCommand() {
        commands = new TreeMap<>();
        CreateEntityCommand cmd = new CreateTestExampleCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new CreateHelpCommand(this);
        commands.put(cmd.getName(), cmd);
        cmd = new BackCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new ExitCommand();
        commands.put(cmd.getName(), cmd);

    }

    @Override
    public boolean execute(String... args) {
        boolean result = true;
        Scanner scanner = new Scanner(System.in);
        Command cmd = commands.get(new ParsedCommand("HELP").getCommand().toUpperCase());
        cmd.execute(null);
        do {
            System.out.print("Create menu:> ");
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
        return "1";
    }

    @Override
    public String getDescription() {
        return String.format("Creates test data and persons for use library.");
    }

    public Map<String, CreateEntityCommand> getCommands() {
        return commands;
    }
}
