package com.netcracker.library.commands;

import com.netcracker.library.commands.user.LogoutReaderCommand;

/**
 * Created by raumo0 on 24.11.16.
 */
public enum CommandType {
    LOGOUT;

    public Command getCurrentCommand() throws EnumConstantNotPresentException{
        switch(this){
            case LOGOUT:
                return new LogoutReaderCommand();
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }
}