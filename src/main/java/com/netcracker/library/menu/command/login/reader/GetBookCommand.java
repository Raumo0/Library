package com.netcracker.library.menu.command.login.reader;

import com.netcracker.library.entities.Book;
import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.entities.Reader;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.IssueBook;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.ParsedCommand;
import com.netcracker.library.menu.command.login.LoginReaderCommand;
import com.netcracker.library.service.impl.BookServiceImpl;
import com.netcracker.library.service.impl.CartularyServiceImpl;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by raumo0 on 27.10.16.
 */
public class GetBookCommand implements ReaderCommand {
    private LoginReaderCommand loginReaderCommand;

    public GetBookCommand(LoginReaderCommand loginReaderCommand) {
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
                    if (book.getBookPosition().equals(BookPosition.IN_STORE) && (book.getCartularies().size() == 0 ||
                            book.getCartularies().getLast().getIssueBook() != IssueBook.BOOK_ORDERED)) {
                        Reader reader = loginReaderCommand.getReader();
                        Cartulary cartulary = new Cartulary(1, book, reader, book.getBookState(),
                                GregorianCalendar.getInstance().getTime());
                        CartularyServiceImpl cartularyService = new CartularyServiceImpl();
                        cartularyService.insertCartulary(cartulary);
                        cartulary.setIssueBook(IssueBook.BOOK_ORDERED);
                        reader.getCartularies().add(cartulary);
                        book.getCartularies().add(cartulary);
                        book.setBookPosition(selectQuery());
                        System.out.println("Book " + cmd + " has been ordered.\n" +
                            "Contact your librarian for get book.");
                    } else {
                        System.out.println("The book " + cmd + " is not in the repository.");
                    }
                }
                System.out.println(LibraryView.getMSG_DELIM());
            }
        }
        return true;
    }

    private BookPosition selectQuery() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Type \"1\" for give at reading room or \"2\" for give at home.");
            String fullCommand = scanner.nextLine();
            ParsedCommand pc = new ParsedCommand(fullCommand);
            if ("1".equals(pc.getCommand())){
                return BookPosition.IN_READING_ROOM;
            }
            if ("2".equals(pc.getCommand())){
                return BookPosition.IN_READER;
            }
            System.out.println("Wrong arguments.");
        } while (true);
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
        return String.format("It gives the book to read at home.");
    }
}
