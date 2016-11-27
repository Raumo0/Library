package com.netcracker.library.commands;

import com.netcracker.library.constants.Parameters;
import com.netcracker.library.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 24.11.16.
 */
public class CommandFactory {
    private CommandFactory() {}

    public static CommandFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Command defineCommand(CommandType type) throws CommandException {
        return type.getCurrentCommand();
    }

    public Command defineCommand(HttpServletRequest request) throws CommandException {
        Command current;
        String commandName = request.getParameter(Parameters.ACTION);
        CommandType type = CommandType.valueOf(commandName.toUpperCase());
        current = type.getCurrentCommand();

        return current;
    }

    private static class SingletonHolder{
        private static final CommandFactory INSTANCE = new CommandFactory();
    }
}
