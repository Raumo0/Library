package com.netcracker.library.views;

/**
 * Created by raumo0 on 21.10.16.
 */
public class CreateCommand implements Command {
    @Override
    public boolean execute(String... args) {
        //TODO
        return false;
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
        return String.format("Creates person for use library.\n" +
            "Use \"CREATE <entity>\", where entity is READER,\n" +
                "LIBRARIAN, AUTHOR, BOOK or BOOK_EDITION.");
    }
}
