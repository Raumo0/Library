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
        return "2";
    }

    @Override
    public String getDescription() {
        return String.format("Librarians log into library to start administrate.");
    }
}
