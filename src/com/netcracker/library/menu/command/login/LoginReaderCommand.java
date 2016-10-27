package com.netcracker.library.menu.command.login;

/**
 * Created by raumo0 on 27.10.16.
 */
public class LoginReaderCommand implements LoginPersonCommand {
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
        return "READER";
    }

    @Override
    public String getDescription() {
        return String.format("Log into library to start sharing\n" +
                "and connecting. Use \"LOGIN READER\".");
    }
}
