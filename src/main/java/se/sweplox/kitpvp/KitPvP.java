package se.sweplox.kitpvp;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import se.sweplox.kitpvp.arena.ArenaManager;
import se.sweplox.kitpvp.command.CommandManager;
import se.sweplox.kitpvp.arena.command.ArenaCommand;
import se.sweplox.kitpvp.command.commands.SetLobbyCommand;
import se.sweplox.kitpvp.command.commands.StatsCommand;
import se.sweplox.kitpvp.kit.KitManager;
import se.sweplox.kitpvp.kit.kits.ArcherKit;
import se.sweplox.kitpvp.kit.kits.FighterKit;
import se.sweplox.kitpvp.kit.kits.FishermanKit;
import se.sweplox.kitpvp.listeners.ArenaListener;
import se.sweplox.kitpvp.listeners.InventoryListener;
import se.sweplox.kitpvp.listeners.KitListener;
import se.sweplox.kitpvp.listeners.PlayerListener;
import se.sweplox.kitpvp.listeners.kit.FishermanListener;
import se.sweplox.kitpvp.lobby.LobbyManager;
import se.sweplox.kitpvp.player.PlayerManager;
import se.sweplox.kitpvp.storage.Storage;
import se.sweplox.kitpvp.storage.type.YAMLStorage;

public class KitPvP extends JavaPlugin {

    private PlayerManager playerManager;
    private KitManager kitManager;
    private ArenaManager arenaManager;
    private CommandManager commandManager;
    private LobbyManager lobbyManager;

    private Storage storage;
    private Economy economy;

    public void onEnable() {
        saveDefaultConfig();
        new Configuration(this);

        registerManagers();
        registerKits();
        registerCommands();
        registerListeners();

        loadStorage();
        loadPlayers();
        loadEconomy();
    }

    public void onDisable() {
        save();
    }

    private void registerManagers() {
        playerManager = new PlayerManager(this);
        kitManager = new KitManager();
        arenaManager = new ArenaManager();
        commandManager = new CommandManager(this);
        lobbyManager = new LobbyManager();
    }

    private void registerCommands() {
        commandManager.register(new ArenaCommand(this));
        commandManager.register(new SetLobbyCommand(this));
        commandManager.register(new StatsCommand(this));
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new InventoryListener(this), this);
        pm.registerEvents(new ArenaListener(), this);
        pm.registerEvents(new KitListener(), this);

        //Kits
        pm.registerEvents(new FishermanListener(this), this);
    }

    private void registerKits() {
        kitManager.register(new FighterKit());
        kitManager.register(new ArcherKit());
        kitManager.register(new FishermanKit());
    }

    private void loadPlayers() {
        boolean lobby = lobbyManager.getLobby() != null;

        getServer().getOnlinePlayers().forEach(player -> {
            playerManager.load(player);

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));

            if(lobby) player.teleport(lobbyManager.getLobby().getLocation());
        });
    }

    private void loadStorage() {
        storage = new YAMLStorage(this);
        storage.init();
    }

    private void loadEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) economy = economyProvider.getProvider();
    }

    private void save() {
        arenaManager.getArenas().values().forEach(arena -> storage.save(arena));
        playerManager.getPlayers().values().forEach(player -> storage.save(player));
        storage.saveLobby();
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public Storage getStorage() {
        return storage;
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public Economy getEconomy() {
        return economy;
    }
}
