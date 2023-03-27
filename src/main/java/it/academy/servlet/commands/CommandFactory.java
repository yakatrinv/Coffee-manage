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
import it.academy.servlet.commands.customer.CreateCustomer;
import it.academy.servlet.commands.customer.DeleteCustomer;
import it.academy.servlet.commands.customer.EditCustomer;
import it.academy.servlet.commands.customer.ListCustomers;
import it.academy.servlet.commands.customer.SaveCustomer;
import it.academy.servlet.commands.customer.UpdateCustomer;
import it.academy.servlet.commands.user.CreateUser;
import it.academy.servlet.commands.user.EditPassUser;
import it.academy.servlet.commands.user.ListUsers;
import it.academy.servlet.commands.user.SaveUser;
import it.academy.servlet.commands.user.UpdatePassUser;

import java.util.HashMap;

import static it.academy.utils.Data.ADD_ADDRESS;
import static it.academy.utils.Data.ADD_CUSTOMER;
import static it.academy.utils.Data.ADD_USERS;
import static it.academy.utils.Data.COMMAND_ADDRESSES;
import static it.academy.utils.Data.COMMAND_CUSTOMERS;
import static it.academy.utils.Data.COMMAND_USERS;
import static it.academy.utils.Data.DELETE_ADDRESS;
import static it.academy.utils.Data.DELETE_CUSTOMER;
import static it.academy.utils.Data.EDIT_ADDRESS;
import static it.academy.utils.Data.EDIT_CUSTOMER;
import static it.academy.utils.Data.EDIT_PASS_USERS;
import static it.academy.utils.Data.GET_LOGIN_PAGE;
import static it.academy.utils.Data.GET_REG_PAGE;
import static it.academy.utils.Data.LOGIN_COMMAND;
import static it.academy.utils.Data.LOGOUT_COMMAND;
import static it.academy.utils.Data.REGISTRATION;
import static it.academy.utils.Data.SAVE_ADDRESS;
import static it.academy.utils.Data.SAVE_CUSTOMER;
import static it.academy.utils.Data.SAVE_USERS;
import static it.academy.utils.Data.UPDATE_ADDRESS;
import static it.academy.utils.Data.UPDATE_CUSTOMER;
import static it.academy.utils.Data.UPDATE_PASS_USERS;

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

        //user
        commands.put(COMMAND_USERS, new ListUsers());
        commands.put(ADD_USERS, new CreateUser());
        commands.put(SAVE_USERS, new SaveUser());
        commands.put(EDIT_PASS_USERS, new EditPassUser());
        commands.put(UPDATE_PASS_USERS, new UpdatePassUser());

        //address
        commands.put(COMMAND_ADDRESSES, new ListAddresses());
        commands.put(ADD_ADDRESS, new CreateAddress());
        commands.put(SAVE_ADDRESS, new SaveAddress());
        commands.put(EDIT_ADDRESS, new EditAddress());
        commands.put(UPDATE_ADDRESS, new UpdateAddress());
        commands.put(DELETE_ADDRESS, new DeleteAddress());

        //customer
        commands.put(COMMAND_CUSTOMERS, new ListCustomers());
        commands.put(ADD_CUSTOMER, new CreateCustomer());
        commands.put(SAVE_CUSTOMER, new SaveCustomer());
        commands.put(EDIT_CUSTOMER, new EditCustomer());
        commands.put(UPDATE_CUSTOMER, new UpdateCustomer());
        commands.put(DELETE_CUSTOMER, new DeleteCustomer());
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
