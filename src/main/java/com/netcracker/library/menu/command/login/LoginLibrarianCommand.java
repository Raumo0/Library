package com.netcracker.library.menu.command.login;

import com.netcracker.library.entities.Librarian;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.ParsedCommand;
import com.netcracker.library.menu.command.login.librarian.DeniedBook;
import com.netcracker.library.menu.command.login.librarian.GetBook;
import com.netcracker.library.menu.command.login.librarian.IssueBook;
import com.netcracker.library.menu.command.login.librarian.LibrarianCommand;
import com.netcracker.library.service.impl.LibrarianServiceImpl;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by raumo0 on 27.10.16.
 */
public class LoginLibrarianCommand implements LoginPersonCommand {
    private Map<String, LibrarianCommand> commands;
    private LibrarianServiceImpl librarianService;
    private Librarian librarian;

    public LoginLibrarianCommand() {
        commands = new TreeMap<>();
        LibrarianCommand cmd = new com.netcracker.library.menu.command.login.librarian.HelpCommand(this);
        commands.put(cmd.getName(), cmd);
        cmd = new com.netcracker.library.menu.command.login.librarian.ExitCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new com.netcracker.library.menu.command.login.librarian.BackCommand();
        commands.put(cmd.getName(), cmd);
        cmd = new IssueBook();
        commands.put(cmd.getName(), cmd);
        cmd = new GetBook();
        commands.put(cmd.getName(), cmd);
        cmd = new DeniedBook();
        commands.put(cmd.getName(), cmd);
        librarianService = new LibrarianServiceImpl();
    }

    @Override
    public boolean execute(String... args) {
        librarian = librarianService.getById(1);
        if (librarian == null) {
            System.out.println("Librarians do not exist\n");
            return true;
        }
        boolean result = true;
        Scanner scanner = new Scanner(System.in);
        LibrarianCommand cmd = commands.get(new ParsedCommand("HELP").getCommand().toUpperCase());
        cmd.execute(null);
        do {
            System.out.print("Librarian menu:> ");
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
        return String.format("Librarians log into library to start administrate.");
    }

    public Map<String, LibrarianCommand> getCommands() {
        return commands;
    }
}
