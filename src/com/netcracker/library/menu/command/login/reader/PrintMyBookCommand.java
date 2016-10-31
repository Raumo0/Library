package com.netcracker.library.menu.command.login.reader;

import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.IssueBook;
import com.netcracker.library.menu.command.login.LoginReaderCommand;

/**
 * Created by raumo0 on 30.10.16.
 */
public class PrintMyBookCommand implements ReaderCommand {
    private LoginReaderCommand loginReaderCommand;

    public PrintMyBookCommand(LoginReaderCommand loginReaderCommand) {
        this.loginReaderCommand = loginReaderCommand;
    }

    @Override
    public boolean execute(String... args) {
        String read = "";
        String returned = "";
        String orders = "";
        String denied = "";
        for (Cartulary cartulary : loginReaderCommand.getReader().getCartularies()) {
            if (cartulary.getIssueBook() == IssueBook.BOOK_ORDERED){
                orders += String.format(cartulary.getBook() + "\t" + cartulary.getBook().getBookPosition().toString() +
                        "\tReceive date:" + cartulary.getReceiveDate() + "\n");
                continue;
            }
            if (cartulary.getIssueBook() == IssueBook.DENIED_ORDERING){
                denied += String.format(cartulary.getBook() + "\t" + cartulary.getBook().getBookPosition().toString() +
                        "\tReceive date:" + cartulary.getReceiveDate() + "\n");
                continue;
            }
            if ((cartulary.getBook().getBookPosition() == BookPosition.IN_READER ||
                            cartulary.getBook().getBookPosition() == BookPosition.IN_READING_ROOM) &&
                    cartulary.getReturnDate() == null) {
                read += String.format(cartulary.getBook() + "\t" + cartulary.getBook().getBookPosition().toString() +
                        "\tReceive date:" + cartulary.getReceiveDate() + "\n");
            } else {
                returned += String.format(cartulary.getBook() + "\t" + cartulary.getBook().getBookPosition().toString() +
                        "\tReceive date:" + cartulary.getReceiveDate() + "\tReturn date:" +
                        cartulary.getReturnDate() + "\n");
            }
        }
        System.out.println("Orders:\n" + orders + "\nDenied:\n" + denied + "\nRead:\n" + read + "\nReturned\n" + returned);
        return true;
    }

    @Override
    public void printHelp() {
        System.out.println(getDescription());
    }

    @Override
    public String getName() {
        return "4";
    }

    @Override
    public String getDescription() {
        return String.format("Print a list of books I have taken.");
    }
}
