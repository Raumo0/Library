package com.netcracker.library.menu;

/**
 * Created by raumo0 on 21.10.16.
 */
class ExitCommand implements Command {
    @Override
    public boolean execute(String... args) {
        System.out.println("Finishing command processor... done.");
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
