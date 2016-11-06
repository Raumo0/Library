package com.netcracker.library.menu.command.login.reader;

import com.netcracker.library.entities.Book;
import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.entities.Reader;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.enums.IssueBook;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.command.login.LoginReaderCommand;
import com.netcracker.library.service.impl.BookServiceImpl;

import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 27.10.16.
 */
public class ReturnBookCommand implements ReaderCommand {
    private LoginReaderCommand loginReaderCommand;

    public ReturnBookCommand(LoginReaderCommand loginReaderCommand) {
        this.loginReaderCommand = loginReaderCommand;
    }

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
                    if (book.getBookPosition().equals(BookPosition.IN_STORE) || book.getCartularies().size() == 0 ||
                            book.getCartularies().getLast().getIssueBook() == IssueBook.BOOK_ORDERED) {
                        System.out.println("The book " + cmd + " is already in the repository");
                    } else {
                        Reader reader = loginReaderCommand.getReader();
                        Cartulary cartulary = book.getCartularies().getLast();
                        if (book.getCartularies().getLast().getReader().getId() == reader.getId()) {
                            cartulary.setComment("Nice book!");
                            book.setBookState(BookState.BAD);
                            cartulary.setAfter(book.getBookState());
                            cartulary.setReturnDate(GregorianCalendar.getInstance().getTime());
                            book.setBookPosition(BookPosition.IN_STORE);
                            System.out.println("The book " + cmd + " returned");
                        } else {
                            System.out.println("The book " + cmd + " was not taken by you");
                        }
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
        return String.format("Returns read book.\n" +
                "Use \"<COMMAND> <ID>\", where ID is the ID of \n" +
                "the book that you want to return.\".");
    }
}
