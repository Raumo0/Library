package com.netcracker.library.menu.command.create;

/**
 * Created by raumo0 on 30.10.16.
 */
public class ExitCommand implements CreateEntityCommand {
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
