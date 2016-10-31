package com.netcracker.library.menu.command.login.librarian;

/**
 * Created by raumo0 on 31.10.16.
 */
public class BackCommand implements LibrarianCommand {
    @Override
    public boolean execute(String... args) {
        return false;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "BACK";
    }

    @Override
    public String getDescription() {
        return String.format("Log out library to the login menu.");
    }
}
