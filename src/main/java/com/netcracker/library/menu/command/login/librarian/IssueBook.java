package com.netcracker.library.menu.command.login.librarian;

import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.service.impl.CartularyServiceImpl;

/**
 * Created by raumo0 on 31.10.16.
 */
public class IssueBook implements LibrarianCommand {
    private CartularyServiceImpl cartularyService;

    public IssueBook() {
        this.cartularyService = new CartularyServiceImpl();
    }

    @Override
    public boolean execute(String... args) {
        for (Cartulary cartulary : cartularyService.getCartularies()){
            if (cartulary.getIssueBook() == com.netcracker.library.enums.IssueBook.BOOK_ORDERED){
                System.out.println(cartulary.getReader() + "\t" + cartulary.getBook() + "\t" +
                        cartulary.getBook().getBookPosition());
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
        return "1";
    }

    @Override
    public String getDescription() {
        return "Print books ordered by readers.";
    }
}
