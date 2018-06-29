package se.sweplox.kitpvp.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.sweplox.kitpvp.Configuration;
import se.sweplox.kitpvp.KitPvP;
import se.sweplox.kitpvp.command.Command;
import se.sweplox.kitpvp.lobby.Lobby;

import java.util.List;

public class SetLobbyCommand extends Command {

    private KitPvP instance;

    public SetLobbyCommand(KitPvP instance) {
        super("setlobby");
        this.instance = instance;
    }

    public boolean isPlayer() { return true; }

    public List<Command> getSubCommands() {
        return null;
    }

    public String getPermission() {
        return "kitpvp.command.setlobby";
    }

    public String getUsage() {
        return "/setlobby";
    }

    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Lobby lobby = new Lobby(player.getLocation());
        instance.getLobbyManager().setLobby(lobby);
        player.sendMessage(Configuration.LOBBY_LOCATION_SET);
    }
}
