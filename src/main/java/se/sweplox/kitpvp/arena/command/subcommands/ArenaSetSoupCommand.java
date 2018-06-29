package se.sweplox.kitpvp.arena.command.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.command.Command;

import java.util.List;

public class ArenaSetSoupCommand extends Command {

    private KitPvP instance;

    public ArenaSetSoupCommand(KitPvP instance) {
        super("setsoup");
        this.instance = instance;
    }

    public boolean isPlayer() {
        return false;
    }

    public List<Command> getSubCommands() {
        return null;
    }

    public String getPermission() {
        return "kitpvp.command.arena.setsoup";
    }

    public String getUsage() {
        return "/arena setsoup <arena> <true:false>";
    }

    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length < 3) {
            player.sendMessage(Configuration.USAGE.replace("%usage%", getUsage()));
            return;
        }

        Arena arena = instance.getArenaManager().getArenaByName(args[1]);

        if(arena == null) {
            player.sendMessage(Configuration.ARENA_DOES_NOT_EXIST.replace("%arena%", args[1]));
            return;
        }

        if(!args[2].equalsIgnoreCase("true") && !args[2].equalsIgnoreCase("false")) {
            player.sendMessage(Configuration.NOT_A_BOOLEAN.replace("%boolean%", args[2]));
            return;
        }

        boolean bool = Boolean.parseBoolean(args[2]);

        arena.setSoup(bool);
        player.sendMessage(Configuration.ARENA_SOUP_SET.replace("%arena%", arena.getName()).replace("%boolean%", String.valueOf(bool)));
    }
}
