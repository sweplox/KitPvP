package se.sweplox.kitpvp.arena.command.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.command.Command;
import se.sweplox.kitpvp.utils.LocationUtil;

import java.util.List;

public class ArenaSetLocationCommand extends Command {

    private KitPvP instance;

    public ArenaSetLocationCommand(KitPvP instance) {
        super("setlocation");
        this.instance = instance;
    }

    public boolean isPlayer() { return false; }

    public List<Command> getSubCommands() {
        return null;
    }

    public String getPermission() {
        return "kitpvp.command.arena.setlocation";
    }

    public String getUsage() {
        return "/arena setlocation <arena>";
    }

    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1) {
            player.sendMessage(getUsage());
            return;
        }

        String name = args[1];
        Arena arena = instance.getArenaManager().getArenaByName(name);

        if(arena == null) {
            player.sendMessage(Configuration.ARENA_DOES_NOT_EXIST.replace("%arena%", name));
            return;
        }

        arena.setLocation(player.getLocation());
        player.sendMessage(Configuration.ARENA_LOCATION_SET.replace("%arena%", arena.getName()));
    }
}
