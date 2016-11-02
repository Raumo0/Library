package com.netcracker.library.menu.command.create;

import com.netcracker.library.menu.command.Command;

/**
 * Created by raumo0 on 25.10.16.
 */
public interface CreateEntityCommand extends Command {
    boolean execute(String... args);

    void printHelp();

    String getName();

    String getDescription();
}
