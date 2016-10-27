package com.netcracker.library.menu.command.login;

import com.netcracker.library.entities.Book;
import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.entities.Reader;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.menu.LibraryView;
import com.netcracker.library.menu.command.Command;
import com.netcracker.library.service.impl.BookServiceImpl;

/**
 * Created by raumo0 on 27.10.16.
 */
public class ReturnBookCommand implements Command {
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
                    if (book.inStore()) {
                        System.out.println("The book " + cmd + " is already in the repository");
//                        Reader reader = loginReaderCommand.getReader();
//                        Cartulary cartulary = new Cartulary(1, book, reader, book.getBookState());
//                        reader.getCartularies().add(cartulary);
//                        book.getCartularies().add(cartulary);
                    } else {
                        Reader reader = loginReaderCommand.getReader();
                        Cartulary cartulary = book.getCartularies().getLast();
                        if (book.getCartularies().getLast().getReader().getId() == reader.getId()) {
                            cartulary.setComment("Nice book!");
                            book.setBookState(BookState.BAD);
                            cartulary.setAfter(book.getBookState());
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
        return "RETURN_BOOK";
    }

    @Override
    public String getDescription() {
        return String.format("Returns read book.\n" +
                "Use \"RETURN_BOOK <ID>\", where id is your book id.\".");
    }
}
