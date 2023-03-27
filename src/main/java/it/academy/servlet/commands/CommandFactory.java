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
import it.academy.servlet.commands.model.CreateModel;
import it.academy.servlet.commands.model.DeleteModel;
import it.academy.servlet.commands.model.EditModel;
import it.academy.servlet.commands.model.ListModels;
import it.academy.servlet.commands.model.SaveModel;
import it.academy.servlet.commands.model.UpdateModel;
import it.academy.servlet.commands.product.CreateProduct;
import it.academy.servlet.commands.product.DeleteProduct;
import it.academy.servlet.commands.product.EditProduct;
import it.academy.servlet.commands.product.ListProducts;
import it.academy.servlet.commands.product.SaveProduct;
import it.academy.servlet.commands.product.UpdateProduct;
import it.academy.servlet.commands.role.CreateRole;
import it.academy.servlet.commands.role.DeleteRole;
import it.academy.servlet.commands.role.EditRole;
import it.academy.servlet.commands.role.ListRoles;
import it.academy.servlet.commands.role.SaveRole;
import it.academy.servlet.commands.role.UpdateRole;
import it.academy.servlet.commands.user.CreateUser;
import it.academy.servlet.commands.user.EditPassUser;
import it.academy.servlet.commands.user.ListUsers;
import it.academy.servlet.commands.user.SaveUser;
import it.academy.servlet.commands.user.UpdatePassUser;

import java.util.HashMap;

import static it.academy.utils.Data.ADD_ADDRESS;
import static it.academy.utils.Data.ADD_CUSTOMER;
import static it.academy.utils.Data.ADD_MODEL;
import static it.academy.utils.Data.ADD_PRODUCT;
import static it.academy.utils.Data.ADD_ROLE;
import static it.academy.utils.Data.ADD_USERS;
import static it.academy.utils.Data.COMMAND_ADDRESSES;
import static it.academy.utils.Data.COMMAND_CUSTOMERS;
import static it.academy.utils.Data.COMMAND_MODELS;
import static it.academy.utils.Data.COMMAND_PRODUCTS;
import static it.academy.utils.Data.COMMAND_ROLES;
import static it.academy.utils.Data.COMMAND_USERS;
import static it.academy.utils.Data.DELETE_ADDRESS;
import static it.academy.utils.Data.DELETE_CUSTOMER;
import static it.academy.utils.Data.DELETE_MODEL;
import static it.academy.utils.Data.DELETE_PRODUCT;
import static it.academy.utils.Data.DELETE_ROLE;
import static it.academy.utils.Data.EDIT_ADDRESS;
import static it.academy.utils.Data.EDIT_CUSTOMER;
import static it.academy.utils.Data.EDIT_MODEL;
import static it.academy.utils.Data.EDIT_PASS_USERS;
import static it.academy.utils.Data.EDIT_PRODUCT;
import static it.academy.utils.Data.EDIT_ROLE;
import static it.academy.utils.Data.GET_LOGIN_PAGE;
import static it.academy.utils.Data.GET_REG_PAGE;
import static it.academy.utils.Data.LOGIN_COMMAND;
import static it.academy.utils.Data.LOGOUT_COMMAND;
import static it.academy.utils.Data.REGISTRATION;
import static it.academy.utils.Data.SAVE_ADDRESS;
import static it.academy.utils.Data.SAVE_CUSTOMER;
import static it.academy.utils.Data.SAVE_MODEL;
import static it.academy.utils.Data.SAVE_PRODUCT;
import static it.academy.utils.Data.SAVE_ROLE;
import static it.academy.utils.Data.SAVE_USERS;
import static it.academy.utils.Data.UPDATE_ADDRESS;
import static it.academy.utils.Data.UPDATE_CUSTOMER;
import static it.academy.utils.Data.UPDATE_MODEL;
import static it.academy.utils.Data.UPDATE_PASS_USERS;
import static it.academy.utils.Data.UPDATE_PRODUCT;
import static it.academy.utils.Data.UPDATE_ROLE;

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

        //role
        commands.put(COMMAND_ROLES, new ListRoles());
        commands.put(ADD_ROLE, new CreateRole());
        commands.put(SAVE_ROLE, new SaveRole());
        commands.put(EDIT_ROLE, new EditRole());
        commands.put(UPDATE_ROLE, new UpdateRole());
        commands.put(DELETE_ROLE, new DeleteRole());

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

        //model
        commands.put(COMMAND_MODELS, new ListModels());
        commands.put(ADD_MODEL, new CreateModel());
        commands.put(SAVE_MODEL, new SaveModel());
        commands.put(EDIT_MODEL, new EditModel());
        commands.put(UPDATE_MODEL, new UpdateModel());
        commands.put(DELETE_MODEL, new DeleteModel());

        //product
        commands.put(COMMAND_PRODUCTS, new ListProducts());
        commands.put(ADD_PRODUCT, new CreateProduct());
        commands.put(SAVE_PRODUCT, new SaveProduct());
        commands.put(EDIT_PRODUCT, new EditProduct());
        commands.put(UPDATE_PRODUCT, new UpdateProduct());
        commands.put(DELETE_PRODUCT, new DeleteProduct());
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
