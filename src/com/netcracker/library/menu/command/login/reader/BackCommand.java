package com.netcracker.library.menu.command.login.reader;

/**
 * Created by raumo0 on 27.10.16.
 */
public class BackCommand implements ReaderCommand {
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
