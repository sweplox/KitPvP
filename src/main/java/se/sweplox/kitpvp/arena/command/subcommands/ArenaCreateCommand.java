package se.sweplox.kitpvp.arena.command.subcommands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.arena.Arena;
import se.sweplox.kitpvp.command.Command;

import java.util.List;

public class ArenaCreateCommand extends Command {

    private KitPvP instance;

    public ArenaCreateCommand(KitPvP instance) {
        super("create");
        this.instance = instance;
    }

    public boolean isPlayer() { return false; }

    public List<Command> getSubCommands() { return null; }

    public String getPermission() {
        return "kitpvp.command.arena.create";
    }

    public String getUsage() { return "/arena create <name>"; }

    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1) {
            player.sendMessage(Configuration.USAGE.replace("%usage%", getUsage()));
            return;
        }

        String name = args[1];

        if(instance.getArenaManager().getArenaByName(name) != null) {
            player.sendMessage(Configuration.ARENA_ALREADY_EXISTS.replace("%arena%", name));
            return;
        }

        if(!StringUtils.isAlpha(name)) {
            player.sendMessage(Configuration.ARENA_ONLY_LETTERS);
            return;
        }

        Arena arena = new Arena(name);
        arena.setKits(instance.getKitManager().getKits());
        instance.getArenaManager().load(arena);
        player.sendMessage(Configuration.ARENA_CREATED.replace("%arena%", name));
    }
}
