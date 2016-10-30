package com.netcracker.library.menu.command.login.reader;

import com.netcracker.library.entities.Book;
import com.netcracker.library.service.impl.BookServiceImpl;

/**
 * Created by raumo0 on 27.10.16.
 */
public class SearchBookCommand implements ReaderCommand {
    BookServiceImpl bookService;

    public SearchBookCommand() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    public boolean execute(String... args) {
        for (Book book : bookService.getBooks()){
            System.out.println(book + "\t" + book.getBookPosition().toString());
        }                
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "1";
    }

    @Override
    public String getDescription() {
        return String.format("Print all librarian's books.\n" +
                "It shows the full list of available books.");
    }
}
