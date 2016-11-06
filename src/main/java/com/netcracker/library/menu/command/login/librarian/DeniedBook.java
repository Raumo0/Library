package com.netcracker.library.menu.command.login.librarian;

import com.netcracker.library.entities.Book;
import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.enums.*;
import com.netcracker.library.enums.IssueBook;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.service.impl.BookServiceImpl;

/**
 * Created by raumo0 on 31.10.16.
 */
public class DeniedBook implements LibrarianCommand {
    @Override
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Write the necessary arguments.\n" + LibraryView.getMSG_DELIM() +
                    "\n " + getDescription());
            System.out.println(LibraryView.getMSG_DELIM());
        } else {
            BookServiceImpl bookService = new BookServiceImpl();
            for (String cmd : args) {
                Book book = bookService.getById(Long.parseLong(cmd));
                if (book == null) {
                    System.out.println(cmd + " " + LibraryView.getMsgCommandNotFound());
                } else {
                    Cartulary cartulary = book.getCartularies().getLast();
                    if (cartulary.getIssueBook() == IssueBook.BOOK_ORDERED){
                        cartulary.setIssueBook(IssueBook.DENIED_ORDERING);
                        if (!book.getBookPosition().equals(BookPosition.IN_STORE)){
                            book.setBookPosition(BookPosition.IN_STORE);
                        }
                        System.out.println("Issue book " + cmd + " was denied.");
                    } else {
                        System.out.println("New order books " + cmd + " not found.");
                    }
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
        return "3";
    }

    @Override
    public String getDescription() {
        return "Refuse to issue a book reader.";
    }
}
