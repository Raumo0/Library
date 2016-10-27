package com.netcracker.library.menu.command.login;

import com.netcracker.library.entities.Book;
import com.netcracker.library.menu.command.Command;
import com.netcracker.library.service.impl.BookServiceImpl;

/**
 * Created by raumo0 on 27.10.16.
 */
public class SearchBookCommand implements Command {
    BookServiceImpl bookService;

    public SearchBookCommand() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    public boolean execute(String... args) {
        for (Book book : bookService.getBooks()){
            System.out.println(book);
        }                
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "SEARCH_BOOK";
    }

    @Override
    public String getDescription() {
        return String.format("It shows the full list of available books.\n" +
                "Use \"SEARCH_BOOK\"");
    }
}
