package com.netcracker.library.menu.command.login;

import com.netcracker.library.menu.command.Command;

/**
 * Created by raumo0 on 27.10.16.
 */
public class LogoutCommand implements Command {
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
        return "LOGOUT";
    }

    @Override
    public String getDescription() {
        return String.format("Log out library to main menu.\n" +
                "Use \"LOGOUT\".");
    }
}
