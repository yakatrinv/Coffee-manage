package it.academy.servlet.commands;

import it.academy.servlet.commands.address.CreateAddress;
import it.academy.servlet.commands.address.DeleteAddress;
import it.academy.servlet.commands.address.EditAddress;
import it.academy.servlet.commands.address.ListAddresses;
import it.academy.servlet.commands.address.SaveAddress;
import it.academy.servlet.commands.address.UpdateAddress;
import it.academy.servlet.commands.auth.GetLoginPage;
import it.academy.servlet.commands.auth.GetRegPage;
import it.academy.servlet.commands.auth.Login;
import it.academy.servlet.commands.auth.Logout;
import it.academy.servlet.commands.auth.Registration;

import java.util.HashMap;

import static it.academy.utils.Data.ADD_ADDRESS;
import static it.academy.utils.Data.COMMAND_ADDRESSES;
import static it.academy.utils.Data.DELETE_ADDRESS;
import static it.academy.utils.Data.EDIT_ADDRESS;
import static it.academy.utils.Data.GET_LOGIN_PAGE;
import static it.academy.utils.Data.GET_REG_PAGE;
import static it.academy.utils.Data.LOGIN_COMMAND;
import static it.academy.utils.Data.LOGOUT_COMMAND;
import static it.academy.utils.Data.REGISTRATION;
import static it.academy.utils.Data.SAVE_ADDRESS;
import static it.academy.utils.Data.UPDATE_ADDRESS;

public final class CommandFactory {
    private static CommandFactory instance;

    private static final HashMap<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        //auth
        commands.put(GET_LOGIN_PAGE, new GetLoginPage());
        commands.put(GET_REG_PAGE, new GetRegPage());
        commands.put(LOGIN_COMMAND, new Login());
        commands.put(LOGOUT_COMMAND, new Logout());
        commands.put(REGISTRATION, new Registration());

        //address
        commands.put(COMMAND_ADDRESSES, new ListAddresses());
        commands.put(ADD_ADDRESS, new CreateAddress());
        commands.put(SAVE_ADDRESS, new SaveAddress());
        commands.put(EDIT_ADDRESS, new EditAddress());
        commands.put(UPDATE_ADDRESS, new UpdateAddress());
        commands.put(DELETE_ADDRESS, new DeleteAddress());
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command getCommand(String value) {
        return commands.get(value);
    }
}
