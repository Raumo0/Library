package com.netcracker.library.commands;

import com.netcracker.library.commands.user.LogoutCommand;
import com.netcracker.library.commands.user.RegistrationCommand;

/**
 * Created by raumo0 on 24.11.16.
 */
public enum CommandType {
    LOGOUT, REGISTRATION;

    public Command getCurrentCommand() throws EnumConstantNotPresentException{
        switch(this){
            case LOGOUT:
                return new LogoutCommand();
            case REGISTRATION:
                return new RegistrationCommand();
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }
}