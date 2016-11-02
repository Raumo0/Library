package com.netcracker.library.menu.command.login;

import com.netcracker.library.entities.Reader;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.ParsedCommand;
import com.netcracker.library.menu.command.login.reader.*;
import com.netcracker.library.menu.command.login.reader.BackCommand;
import com.netcracker.library.menu.command.login.reader.ExitCommand;
import com.netcracker.library.menu.command.login.reader.HelpCommand;
import com.netcracker.library.service.impl.ReaderServiceImpl;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by raumo0 on 27.10.16.
 */
public class LoginReaderCommand implements LoginPersonCommand {
    private Map<String, ReaderCommand> commands;
    private ReaderServiceImpl readerService;
    private Reader reader;

    public LoginReaderCommand() {
        commands = new TreeMap<>();
        ReaderCommand cmd = new ReturnBookCommand(this);
        commands.put(cmd.getName(), cmd);
        cmd = new SearchBookCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new GetBookCommand(this);
        commands.put(cmd.getName(), cmd);
        cmd = new ExitCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new BackCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new HelpCommand(this);
        commands.put(cmd.getName(), cmd);
        cmd = new PrintMyBookCommand(this);
        commands.put(cmd.getName(), cmd);
        readerService = new ReaderServiceImpl();
    }

    @Override
    public boolean execute(String... args) {
        reader = readerService.getById(1);
        if (reader == null) {
            System.out.println("Readers do not exist\n");
            return true;
        }
        boolean result = true;
        Scanner scanner = new Scanner(System.in);
        ReaderCommand cmd = commands.get(new ParsedCommand("HELP").getCommand().toUpperCase());
        cmd.execute(null);
        do {
            System.out.print("Reader menu:> ");
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
        return String.format("Readers log into library to start sharing\n" +
                "and connecting.");
    }

    public Map<String, ReaderCommand> getCommands() {
        return commands;
    }

    public Reader getReader() {
        return reader;
    }
}
