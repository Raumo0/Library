package com.netcracker.library.commands;

import com.netcracker.library.constants.Parameters;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raumo0 on 24.11.16.
 */
public class CommandFactory {
    private CommandFactory() {}

    public static CommandFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Command defineCommand(HttpServletRequest request){
        Command current = null;
        String commandName = request.getParameter(Parameters.ACTION);
        try{
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = type.getCurrentCommand();
        }
        catch(NullPointerException e){
            //todo
        }
        catch(IllegalArgumentException e){
            //todo
        }
        return current;
    }

    private static class SingletonHolder{
        private static final CommandFactory INSTANCE = new CommandFactory();
    }
}
