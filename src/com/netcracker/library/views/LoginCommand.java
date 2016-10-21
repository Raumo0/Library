package com.netcracker.library.views;

/**
 * Created by raumo0 on 21.10.16.
 */
public class LoginCommand implements Command {
    public LoginCommand() {
    }

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
        return "LOGIN";
    }

    @Override
    public String getDescription() {
        return String.format("Log into library to start sharing\n" +
                "and connecting. Use \"LOGIN <person>\", where\n" +
                "person id READER or LIBRARIAN.");    }
}
