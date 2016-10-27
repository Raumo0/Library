package com.netcracker.library.menu.command.login;

/**
 * Created by raumo0 on 27.10.16.
 */
public class LoginLibrarianCommand implements LoginPersonCommand {
    @Override
    public boolean execute(String... args) {
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "LIBRARIAN";
    }

    @Override
    public String getDescription() {
        return String.format("Log into library to start administrate\n" +
                "Use \"LOGIN LIBRARIAN\".");
    }
}
