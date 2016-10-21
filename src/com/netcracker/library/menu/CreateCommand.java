package com.netcracker.library.menu;

import com.netcracker.library.entities.BookEdition;

import java.util.ArrayList;

/**
 * Created by raumo0 on 21.10.16.
 */
public class CreateCommand implements Command {
    @Override
    public boolean execute(String... args) {
        //TODO
        ArrayList<BookEdition> a = new ArrayList<>();
        a.add(new BookEdition(9, "My first step."));
        a.add(new BookEdition(3, "Wow, not bad!"));
        a.add(new BookEdition(5, "My beautiful mom."));
        for (BookEdition val : a){
            System.out.println(val);
        }
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
