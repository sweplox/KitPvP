package se.sweplox.kitpvp.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.command.Command;
import se.sweplox.kitpvp.player.PVPPlayer;
import se.sweplox.kitpvp.utils.DoubleUtil;

import java.util.ArrayList;
import java.util.List;

public class StatsCommand extends Command {

    private KitPvP instance;

    public StatsCommand(KitPvP instance) {
        super("stats");
        this.instance = instance;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public List<Command> getSubCommands() {
        return null;
    }

    @Override
    public String getPermission() {
        return "kitpvp.command.stats";
    }

    @Override
    public String getUsage() {
        return "/stats";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length > 0) {
            Player target = instance.getServer().getPlayer(args[0]);
            if(target != null) {
                PVPPlayer pvpPlayer = instance.getPlayerManager().getPlayerByUUID(target.getUniqueId());
                if(pvpPlayer != null) {
                    Configuration.STATS.forEach(string -> player.sendMessage(string.replace("%player%", target.getName())
                            .replace("%kills%", String.valueOf(pvpPlayer.getKills()))
                            .replace("%deaths%", String.valueOf(pvpPlayer.getDeaths()))
                            .replace("%kdr%", String.valueOf(DoubleUtil.round(pvpPlayer.getKdr(), 2)))));
                    return;
                }
            }
        }

        PVPPlayer pvpPlayer = instance.getPlayerManager().getPlayerByUUID(player.getUniqueId());
        if(pvpPlayer == null) return;

        Configuration.STATS.forEach(string -> player.sendMessage(string.replace("%player%", player.getName())
                .replace("%kills%", String.valueOf(pvpPlayer.getKills()))
                .replace("%deaths%", String.valueOf(pvpPlayer.getDeaths()))
                .replace("%kdr%", String.valueOf(DoubleUtil.round(pvpPlayer.getKdr(), 2)))));
    }
}
