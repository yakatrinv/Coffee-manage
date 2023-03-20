package it.academy.servlet.commands;

import it.academy.servlet.commands.address.ListAddresses;

import java.util.HashMap;

import static it.academy.utils.DataUI.COMMAND_ADDRESSES;

public final class CommandFactory {
    private static CommandFactory instance;
    private static final HashMap<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(COMMAND_ADDRESSES,new ListAddresses());
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
