package com.netcracker.library.commands;

import com.netcracker.library.commands.user.LoginCommand;
import com.netcracker.library.commands.user.LogoutCommand;
import com.netcracker.library.commands.user.RegistrationCommand;
import com.netcracker.library.commands.user.UserPageCommand;

/**
 * Created by raumo0 on 24.11.16.
 */
public enum CommandType {
    LOGOUT, REGISTRATION, LOGIN, USER_PAGE;

    public Command getCurrentCommand() throws EnumConstantNotPresentException{
        switch(this){
            case LOGOUT:
                return new LogoutCommand();
            case REGISTRATION:
                return new RegistrationCommand();
            case LOGIN:
                return new LoginCommand();
            case USER_PAGE:
                return new UserPageCommand();
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }
}