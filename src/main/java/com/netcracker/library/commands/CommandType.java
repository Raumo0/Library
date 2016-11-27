package com.netcracker.library.commands;

import com.netcracker.library.commands.book.BookEditionCommand;
import com.netcracker.library.commands.library.AboutCommand;
import com.netcracker.library.commands.library.ContactCommand;
import com.netcracker.library.commands.library.HomeCommand;
import com.netcracker.library.commands.user.LoginCommand;
import com.netcracker.library.commands.user.LogoutCommand;
import com.netcracker.library.commands.user.RegistrationCommand;
import com.netcracker.library.commands.user.UserPageCommand;
import com.netcracker.library.exceptions.CommandException;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by raumo0 on 24.11.16.
 */
public enum CommandType {
    LOGOUT, REGISTRATION, LOGIN, USER_PAGE, BOOK_EDITION_PAGE, ABOUT, CONTACT, HOME;

    private static Map<CommandType, Class> commands;
    static {
        commands = new TreeMap<>();
        commands.put(CommandType.LOGOUT, LogoutCommand.class);
        commands.put(CommandType.REGISTRATION, RegistrationCommand.class);
        commands.put(CommandType.LOGIN, LoginCommand.class);
        commands.put(CommandType.USER_PAGE, UserPageCommand.class);
        commands.put(CommandType.BOOK_EDITION_PAGE, BookEditionCommand.class);
        commands.put(CommandType.ABOUT, AboutCommand.class);
        commands.put(CommandType.CONTACT, ContactCommand.class);
        commands.put(CommandType.HOME, HomeCommand.class);
    }

    public Command getCurrentCommand() throws CommandException {
        Class commandClass = commands.get(this);
        try {
            return (Command) commandClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CommandException(e);
        }
    }
}