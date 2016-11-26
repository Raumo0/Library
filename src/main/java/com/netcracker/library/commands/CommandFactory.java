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
        Command command;
        try {
            command = type.getCurrentCommand();
        } catch (EnumConstantNotPresentException e){
            throw new CommandException(e);
        }
        return command;
    }

    public Command defineCommand(HttpServletRequest request) throws CommandException{
        Command current;
        String commandName = request.getParameter(Parameters.ACTION);
        try{
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = type.getCurrentCommand();
        }
        catch(NullPointerException | IllegalArgumentException e){
            throw new CommandException(e);
        }
        return current;
    }

    private static class SingletonHolder{
        private static final CommandFactory INSTANCE = new CommandFactory();
    }
}
