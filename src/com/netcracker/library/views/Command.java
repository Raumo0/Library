package com.netcracker.library.views;

/**
 * Created by raumo0 on 21.10.16.
 */
public interface Command {
    boolean execute(String... args);

    void printHelp();

    String getName();

    String getDescription();
}
