package com.netcracker.library.views;

/**
 * Created by raumo0 on 21.10.16.
 */
class HelpCommand implements Command {

    private LibraryView commandProcessor;

    public HelpCommand(LibraryView commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Avaliable commands:\n" + LibraryView.MSG_DELIM);
            for (Command cmd : commandProcessor.commands.values()) {
                System.out.println("\n" + cmd.getName() + ": " + cmd.getDescription());
            }
            System.out.println(LibraryView.MSG_DELIM);
        } else {
            for (String cmd : args) {
                System.out.println("Help for command " + cmd + ":\n" + LibraryView.MSG_DELIM);
                Command command = commandProcessor.commands.get(cmd.toUpperCase());
                if (command == null) {
                    System.out.println(LibraryView.MSG_COMMAND_NOT_FOUND);
                } else {
                    command.printHelp();
                }
                System.out.println(LibraryView.MSG_DELIM);
            }
        }
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "HELP";
    }

    @Override
    public String getDescription() {
        return "Prints list of available commands.\n" +
                "Use \"HELP <command>\".";
    }
}