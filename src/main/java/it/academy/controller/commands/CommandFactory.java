package it.academy.controller.commands;

import it.academy.controller.commands.address.CreateAddress;
import it.academy.controller.commands.address.DeleteAddress;
import it.academy.controller.commands.address.EditAddress;
import it.academy.controller.commands.address.ListAddresses;
import it.academy.controller.commands.address.SaveAddress;
import it.academy.controller.commands.address.UpdateAddress;
import it.academy.controller.commands.auth.GetLoginPage;
import it.academy.controller.commands.auth.GetRegPage;
import it.academy.controller.commands.auth.Login;
import it.academy.controller.commands.auth.Logout;
import it.academy.controller.commands.auth.Registration;
import it.academy.controller.commands.customer.CreateCustomer;
import it.academy.controller.commands.customer.DeleteCustomer;
import it.academy.controller.commands.customer.EditCustomer;
import it.academy.controller.commands.customer.ListCustomers;
import it.academy.controller.commands.customer.SaveCustomer;
import it.academy.controller.commands.customer.UpdateCustomer;
import it.academy.controller.commands.discount.CreateDiscount;
import it.academy.controller.commands.discount.DeleteDiscount;
import it.academy.controller.commands.discount.EditDiscount;
import it.academy.controller.commands.discount.ListDiscounts;
import it.academy.controller.commands.discount.SaveDiscount;
import it.academy.controller.commands.discount.UpdateDiscount;
import it.academy.controller.commands.machine.CreateMachine;
import it.academy.controller.commands.machine.DeleteMachine;
import it.academy.controller.commands.machine.EditMachine;
import it.academy.controller.commands.machine.ListMachines;
import it.academy.controller.commands.machine.SaveMachine;
import it.academy.controller.commands.machine.UpdateMachine;
import it.academy.controller.commands.machineProduct.CreateMachineProduct;
import it.academy.controller.commands.machineProduct.DeleteMachineProduct;
import it.academy.controller.commands.machineProduct.ListMachineProduct;
import it.academy.controller.commands.machineProduct.SaveMachineProduct;
import it.academy.controller.commands.model.CreateModel;
import it.academy.controller.commands.model.DeleteModel;
import it.academy.controller.commands.model.EditModel;
import it.academy.controller.commands.model.ListModels;
import it.academy.controller.commands.model.SaveModel;
import it.academy.controller.commands.model.UpdateModel;
import it.academy.controller.commands.product.CreateProduct;
import it.academy.controller.commands.product.DeleteProduct;
import it.academy.controller.commands.product.EditProduct;
import it.academy.controller.commands.product.ListProducts;
import it.academy.controller.commands.product.SaveProduct;
import it.academy.controller.commands.product.UpdateProduct;
import it.academy.controller.commands.purchase.CreatePurchase;
import it.academy.controller.commands.purchase.DeletePurchase;
import it.academy.controller.commands.purchase.EditPurchase;
import it.academy.controller.commands.purchase.ListPurchase;
import it.academy.controller.commands.purchase.SavePurchase;
import it.academy.controller.commands.purchase.UpdatePurchase;
import it.academy.controller.commands.role.CreateRole;
import it.academy.controller.commands.role.DeleteRole;
import it.academy.controller.commands.role.EditRole;
import it.academy.controller.commands.role.ListRoles;
import it.academy.controller.commands.role.SaveRole;
import it.academy.controller.commands.role.UpdateRole;
import it.academy.controller.commands.user.CreateUser;
import it.academy.controller.commands.user.EditPassUser;
import it.academy.controller.commands.user.EditRolesUser;
import it.academy.controller.commands.user.ListUsers;
import it.academy.controller.commands.user.SaveUser;
import it.academy.controller.commands.user.UpdatePassUser;
import it.academy.controller.commands.user.UpdateRolesUser;

import java.util.HashMap;

import static it.academy.utils.Data.ADD_ADDRESS;
import static it.academy.utils.Data.ADD_CUSTOMER;
import static it.academy.utils.Data.ADD_DISCOUNT;
import static it.academy.utils.Data.ADD_MACHINE;
import static it.academy.utils.Data.ADD_MACHINE_PRODUCT;
import static it.academy.utils.Data.ADD_MODEL;
import static it.academy.utils.Data.ADD_PRODUCT;
import static it.academy.utils.Data.ADD_PURCHASE;
import static it.academy.utils.Data.ADD_ROLE;
import static it.academy.utils.Data.ADD_USERS;
import static it.academy.utils.Data.COMMAND_ADDRESSES;
import static it.academy.utils.Data.COMMAND_CUSTOMERS;
import static it.academy.utils.Data.COMMAND_DISCOUNTS;
import static it.academy.utils.Data.COMMAND_MACHINES;
import static it.academy.utils.Data.COMMAND_MACHINE_PRODUCTS;
import static it.academy.utils.Data.COMMAND_MODELS;
import static it.academy.utils.Data.COMMAND_PRODUCTS;
import static it.academy.utils.Data.COMMAND_PURCHASES;
import static it.academy.utils.Data.COMMAND_ROLES;
import static it.academy.utils.Data.COMMAND_USERS;
import static it.academy.utils.Data.DELETE_ADDRESS;
import static it.academy.utils.Data.DELETE_CUSTOMER;
import static it.academy.utils.Data.DELETE_DISCOUNT;
import static it.academy.utils.Data.DELETE_MACHINE;
import static it.academy.utils.Data.DELETE_MACHINE_PRODUCT;
import static it.academy.utils.Data.DELETE_MODEL;
import static it.academy.utils.Data.DELETE_PRODUCT;
import static it.academy.utils.Data.DELETE_PURCHASE;
import static it.academy.utils.Data.DELETE_ROLE;
import static it.academy.utils.Data.EDIT_ADDRESS;
import static it.academy.utils.Data.EDIT_CUSTOMER;
import static it.academy.utils.Data.EDIT_DISCOUNT;
import static it.academy.utils.Data.EDIT_MACHINE;
import static it.academy.utils.Data.EDIT_MODEL;
import static it.academy.utils.Data.EDIT_PASS_USER;
import static it.academy.utils.Data.EDIT_PRODUCT;
import static it.academy.utils.Data.EDIT_PURCHASE;
import static it.academy.utils.Data.EDIT_ROLE;
import static it.academy.utils.Data.EDIT_ROLES_USER;
import static it.academy.utils.Data.GET_LOGIN_PAGE;
import static it.academy.utils.Data.GET_REG_PAGE;
import static it.academy.utils.Data.LOGIN_COMMAND;
import static it.academy.utils.Data.LOGOUT_COMMAND;
import static it.academy.utils.Data.REGISTRATION;
import static it.academy.utils.Data.SAVE_ADDRESS;
import static it.academy.utils.Data.SAVE_CUSTOMER;
import static it.academy.utils.Data.SAVE_DISCOUNT;
import static it.academy.utils.Data.SAVE_MACHINE;
import static it.academy.utils.Data.SAVE_MACHINE_PRODUCT;
import static it.academy.utils.Data.SAVE_MODEL;
import static it.academy.utils.Data.SAVE_PRODUCT;
import static it.academy.utils.Data.SAVE_PURCHASE;
import static it.academy.utils.Data.SAVE_ROLE;
import static it.academy.utils.Data.SAVE_USERS;
import static it.academy.utils.Data.UPDATE_ADDRESS;
import static it.academy.utils.Data.UPDATE_CUSTOMER;
import static it.academy.utils.Data.UPDATE_DISCOUNT;
import static it.academy.utils.Data.UPDATE_MACHINE;
import static it.academy.utils.Data.UPDATE_MODEL;
import static it.academy.utils.Data.UPDATE_PASS_USER;
import static it.academy.utils.Data.UPDATE_PRODUCT;
import static it.academy.utils.Data.UPDATE_PURCHASE;
import static it.academy.utils.Data.UPDATE_ROLE;
import static it.academy.utils.Data.UPDATE_ROLES_USER;

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
        commands.put(EDIT_PASS_USER, new EditPassUser());
        commands.put(UPDATE_PASS_USER, new UpdatePassUser());
        commands.put(EDIT_ROLES_USER, new EditRolesUser());
        commands.put(UPDATE_ROLES_USER, new UpdateRolesUser());

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

        //machines
        commands.put(COMMAND_MACHINES, new ListMachines());
        commands.put(ADD_MACHINE, new CreateMachine());
        commands.put(SAVE_MACHINE, new SaveMachine());
        commands.put(EDIT_MACHINE, new EditMachine());
        commands.put(UPDATE_MACHINE, new UpdateMachine());
        commands.put(DELETE_MACHINE, new DeleteMachine());

        //products machine
        commands.put(COMMAND_MACHINE_PRODUCTS, new ListMachineProduct());
        commands.put(ADD_MACHINE_PRODUCT, new CreateMachineProduct());
        commands.put(SAVE_MACHINE_PRODUCT, new SaveMachineProduct());
        commands.put(DELETE_MACHINE_PRODUCT, new DeleteMachineProduct());

        //discounts
        commands.put(COMMAND_DISCOUNTS, new ListDiscounts());
        commands.put(ADD_DISCOUNT, new CreateDiscount());
        commands.put(SAVE_DISCOUNT, new SaveDiscount());
        commands.put(EDIT_DISCOUNT, new EditDiscount());
        commands.put(UPDATE_DISCOUNT, new UpdateDiscount());
        commands.put(DELETE_DISCOUNT, new DeleteDiscount());

        //purchase
        commands.put(COMMAND_PURCHASES, new ListPurchase());
        commands.put(ADD_PURCHASE, new CreatePurchase());
        commands.put(SAVE_PURCHASE, new SavePurchase());
        commands.put(EDIT_PURCHASE, new EditPurchase());
        commands.put(UPDATE_PURCHASE, new UpdatePurchase());
        commands.put(DELETE_PURCHASE, new DeletePurchase());
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
