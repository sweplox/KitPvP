package se.sweplox.kitpvp.arena.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.arena.command.subcommands.ArenaCreateCommand;
import se.sweplox.kitpvp.arena.command.subcommands.ArenaDeleteCommand;
import se.sweplox.kitpvp.arena.command.subcommands.ArenaSetLocationCommand;
import se.sweplox.kitpvp.arena.command.subcommands.ArenaSetSoupCommand;
import se.sweplox.kitpvp.command.Command;
import se.sweplox.kitpvp.player.PVPPlayer;

import java.util.Arrays;
import java.util.List;

public class ArenaCommand extends Command {

    private KitPvP instance;

    public ArenaCommand(KitPvP instance) {
        super("arena");
        this.instance = instance;
    }

    public boolean isPlayer() { return true; }

    public List<Command> getSubCommands() {
        return Arrays.asList(new ArenaCreateCommand(instance),
                new ArenaSetLocationCommand(instance),
                new ArenaDeleteCommand(instance),
                new ArenaSetSoupCommand(instance));
    }

    public String getPermission() {
        return "kitpvp.command.arena";
    }

    public String getUsage() {
        return "/arena";
    }

    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Configuration.ARENA_HELP.forEach(string -> player.sendMessage(string));
    }
}
