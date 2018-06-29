package se.sweplox.kitpvp.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.Configuration;

import java.util.List;

public abstract class Command implements CommandExecutor {

    private String name;

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isPlayer();
    public abstract List<Command> getSubCommands();
    public abstract String getPermission();
    public abstract String getUsage();
    public abstract void execute(CommandSender sender, String[] args);

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String string, String[] args) {
        if(isPlayer() && !(sender instanceof Player)) {
            sender.sendMessage(Configuration.NOT_A_PLAYER);
            return true;
        }

        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(Configuration.NO_PERMISSION);
            return true;
        }

        if(args.length > 0) {
            for(Command subCommand : getSubCommands()) {
                if(args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.execute(sender, args);
                    return true;
                }
            }
        }

        execute(sender, args);
        return false;
    }
}
