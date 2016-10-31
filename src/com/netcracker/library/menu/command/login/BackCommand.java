package com.netcracker.library.menu.command.login;

/**
 * Created by raumo0 on 30.10.16.
 */
public class BackCommand implements LoginPersonCommand {
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
        return "Returns to the main menu.";
    }
}