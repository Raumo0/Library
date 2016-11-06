package com.netcracker.library.menu.command.login.librarian;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.IssueBook;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.service.impl.BookServiceImpl;


/**
 * Created by raumo0 on 31.10.16.
 */
public class GetBook implements LibrarianCommand {
    @Override
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Write the necessary arguments.\n" + LibraryView.getMSG_DELIM() + "\n " + getDescription());
            System.out.println(LibraryView.getMSG_DELIM());
        } else {
            BookServiceImpl bookService = new BookServiceImpl();
            for (String cmd : args) {
                Book book = bookService.getById(Long.parseLong(cmd));
                if (book == null) {
                    System.out.println(cmd + " " + LibraryView.getMsgCommandNotFound());
                } else {
                    if (book.getCartularies().size() != 0 &&
                            book.getCartularies().getLast().getIssueBook() == IssueBook.BOOK_ORDERED){
                        book.getCartularies().getLast().setIssueBook(IssueBook.BOOK_ISSUED);
                        if (book.getBookPosition().equals(BookPosition.IN_STORE)){
                            book.setBookPosition(BookPosition.IN_READING_ROOM);
                        }
                        System.out.println("Book " + cmd + " has been given");
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
        return "2";
    }

    @Override
    public String getDescription() {
        return "Give the book to the reader.";
    }
}
