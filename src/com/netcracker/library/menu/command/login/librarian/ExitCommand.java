package com.netcracker.library.menu.command.login.librarian;

/**
 * Created by raumo0 on 31.10.16.
 */
public class ExitCommand implements LibrarianCommand {
    @Override
    public boolean execute(String... args) {
        System.out.println("Finishing command processor... done.");
        System.exit(0);
        return false;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "EXIT";
    }

    @Override
    public String getDescription() {
        return "Exits from command processor";
    }
}
