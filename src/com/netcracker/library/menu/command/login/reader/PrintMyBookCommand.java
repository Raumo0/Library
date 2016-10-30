package com.netcracker.library.menu.command.login.reader;

import com.netcracker.library.entities.Cartulary;
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
        for (Cartulary cartulary : loginReaderCommand.getReader().getCartularies()) {
            System.out.println(cartulary.getBook() + "\t" + cartulary.getBook().getBookPosition().toString());
        }
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
