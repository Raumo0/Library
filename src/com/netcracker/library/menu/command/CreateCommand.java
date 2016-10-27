package com.netcracker.library.menu.command;

import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.command.create.CreateEntityCommand;
import com.netcracker.library.menu.command.create.CreateTestExampleCommand;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by raumo0 on 21.10.16.
 */
public class CreateCommand implements Command {
    private Map<String, Command> commands;

    public CreateCommand() {
        commands = new TreeMap<>();
        CreateEntityCommand cmd = new CreateTestExampleCommand();
        commands.put(cmd.getName(), cmd);
    }

    @Override
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Write the necessary arguments.\n" + LibraryView.getMSG_DELIM() + "\n " + getDescription());
            System.out.println(LibraryView.getMSG_DELIM());
        } else {
            for (String cmd : args) {
                Command command = commands.get(cmd.toUpperCase());
                if (command == null) {
                    System.out.println(LibraryView.getMsgCommandNotFound());
                } else {
                    command.execute(null);
                }
                System.out.println(LibraryView.getMSG_DELIM());
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
        return "CREATE";
    }

    @Override
    public String getDescription() {
        return String.format("Creates test data for use library.\n" +
            "Use \"CREATE TEST\".");
    }
}
