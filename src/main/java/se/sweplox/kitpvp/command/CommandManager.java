package se.sweplox.kitpvp.command;

import se.sweplox.kitpvp.KitPvP;

import java.util.HashMap;

public class CommandManager {

    private KitPvP instance;

    private HashMap<String, Command> commands = new HashMap<String, Command>();

    public CommandManager(KitPvP instance) {
        this.instance = instance;
    }

    public void register(Command command) {
        commands.put(command.getName().toLowerCase(), command);
        instance.getCommand(command.getName()).setExecutor(command);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}
