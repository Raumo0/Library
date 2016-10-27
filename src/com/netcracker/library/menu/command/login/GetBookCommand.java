package com.netcracker.library.menu.command.login;

import com.netcracker.library.entities.Book;
import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.entities.Reader;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.command.Command;
import com.netcracker.library.service.impl.BookServiceImpl;

/**
 * Created by raumo0 on 27.10.16.
 */
public class GetBookCommand implements Command {
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
                    if (book.inStore()) {
                        Reader reader = loginReaderCommand.getReader();
                        Cartulary cartulary = new Cartulary(1, book, reader, book.getBookState());
                        reader.getCartularies().add(cartulary);
                        book.getCartularies().add(cartulary);
                        book.setBookPosition(BookPosition.IN_READER);
                        System.out.println("Book " + cmd + " has been given");
                    } else {
                        System.out.println("The book " + cmd + " is not in the repository");
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
        return "GET_BOOK";
    }

    @Override
    public String getDescription() {
        return String.format("It gives the book to read.\n" +
                "Use \"GET_BOOK <ID>\", where id is book id.");
    }
}
