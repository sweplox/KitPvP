package se.sweplox.kitpvp;

import org.bukkit.Location;
import org.bukkit.Material;
import se.sweplox.kitpvp.utils.LocationUtil;
import se.sweplox.kitpvp.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private KitPvP instance;

    public static String MYSQL_USERNAME;
    public static String MYSQL_PASSWORD;
    public static String MYSQL_IP;
    public static int MYSQL_PORT;
    public static String MYSQL_DATABASE;

    public static String KIT_SELECTOR_NAME;
    public static Material KIT_SELECTOR_MATERIAL;
    public static int KIT_SELECTOR_SLOT;
    public static String KIT_SELECTOR_TITLE;
    public static int KIT_SELECTOR_SIZE;
    public static String KIT_SHOP_NAME;
    public static Material KIT_SHOP_MATERIAL;
    public static int KIT_SHOP_SLOT;
    public static String KIT_SHOP_TITLE;
    public static int KIT_SHOP_SIZE;
    public static String KIT_OWNED_NAME;
    public static String KIT_NOT_OWNED_NAME;
    public static List<String> KIT_OWNED_LORE;
    public static List<String> KIT_NOT_OWNED_LORE;
    public static String KIT_BUY_TITLE;
    public static int KIT_BUY_SIZE;
    public static Material KIT_BUY_CONFIRM_MATERIAL;
    public static String KIT_BUY_CONFIRM_NAME;
    public static int KIT_BUY_CONFIRM_DURABILITY;
    public static int KIT_BUY_CONFIRM_SLOT;
    public static List<String> KIT_BUY_CONFIRM_LORE;
    public static Material KIT_BUY_CANCEL_MATERIAL;
    public static String KIT_BUY_CANCEL_NAME;
    public static int KIT_BUY_CANCEL_DURABILITY;
    public static int KIT_BUY_CANCEL_SLOT;
    public static List<String> KIT_BUY_CANCEL_LORE;
    public static String SIGN_LINE_ONE;
    public static String SIGN_LINE_TWO;

    public static String NO_PERMISSION;
    public static String NOT_A_PLAYER;
    public static String USAGE;
    public static List<String> ARENA_HELP;
    public static String ARENA_ALREADY_EXISTS;
    public static String ARENA_ONLY_LETTERS;
    public static String ARENA_CREATED;
    public static String ARENA_DOES_NOT_EXIST;
    public static String ARENA_DELETED;
    public static String ARENA_LOCATION_SET;
    public static String ARENA_LOCATION_NOT_SET;
    public static String LOBBY_LOCATION_SET;
    public static String JOINED_ARENA;
    public static String NOT_ENOUGH_MONEY;
    public static String BOUGHT_KIT;
    public static String SELECTED_KIT;
    public static List<String> STATS;


    public Configuration(KitPvP instance) {
        this.instance = instance;

        //MySQL
        MYSQL_USERNAME = instance.getConfig().getString("mysql.username");
        MYSQL_PASSWORD = instance.getConfig().getString("mysql.password");
        MYSQL_IP = instance.getConfig().getString("mysql.ip");
        MYSQL_PORT = instance.getConfig().getInt("mysql.port");
        MYSQL_DATABASE = instance.getConfig().getString("mysql.database");

        //Configuration
        KIT_SELECTOR_NAME = StringUtil.color(instance.getConfig().getString("kit_selector.name"));
        KIT_SELECTOR_MATERIAL = Material.valueOf(instance.getConfig().getString("kit_selector.material"));
        KIT_SELECTOR_SLOT = instance.getConfig().getInt("kit_selector.slot");
        KIT_SELECTOR_TITLE = StringUtil.color(instance.getConfig().getString("kit_selector.title"));
        KIT_SELECTOR_SIZE = instance.getConfig().getInt("kit_selector.size");
        KIT_SHOP_NAME = StringUtil.color(instance.getConfig().getString("kit_shop.name"));
        KIT_SHOP_MATERIAL = Material.valueOf(instance.getConfig().getString("kit_shop.material"));
        KIT_SHOP_SLOT = instance.getConfig().getInt("kit_shop.slot");
        KIT_SHOP_TITLE = StringUtil.color(instance.getConfig().getString("kit_shop.title"));
        KIT_SHOP_SIZE = instance.getConfig().getInt("kit_shop.size");
        KIT_OWNED_NAME = StringUtil.color(instance.getConfig().getString("kit_owned.name"));
        KIT_NOT_OWNED_NAME = StringUtil.color(instance.getConfig().getString("kit_not_owned.name"));
        KIT_OWNED_LORE = instance.getConfig().getStringList("kit_owned.lore");
        KIT_NOT_OWNED_LORE = instance.getConfig().getStringList("kit_not_owned.lore");
        KIT_BUY_TITLE = StringUtil.color(instance.getConfig().getString("kit_buy.title"));
        KIT_BUY_SIZE = instance.getConfig().getInt("kit_buy.size");
        KIT_BUY_CONFIRM_MATERIAL = Material.valueOf(instance.getConfig().getString("kit_buy_confirm.material"));
        KIT_BUY_CONFIRM_NAME = StringUtil.color(instance.getConfig().getString("kit_buy_confirm.name"));
        KIT_BUY_CONFIRM_DURABILITY = instance.getConfig().getInt("kit_buy_confirm.durability");
        KIT_BUY_CONFIRM_SLOT = instance.getConfig().getInt("kit_buy_confirm.slot");
        KIT_BUY_CONFIRM_LORE = new ArrayList<>();
        instance.getConfig().getStringList("kit_buy_confirm.lore").forEach(string -> KIT_BUY_CONFIRM_LORE.add(StringUtil.color(string)));
        KIT_BUY_CANCEL_MATERIAL = Material.valueOf(instance.getConfig().getString("kit_buy_cancel.material"));
        KIT_BUY_CANCEL_NAME = StringUtil.color(instance.getConfig().getString("kit_buy_cancel.name"));
        KIT_BUY_CANCEL_DURABILITY = instance.getConfig().getInt("kit_buy_cancel.durability");
        KIT_BUY_CANCEL_SLOT = instance.getConfig().getInt("kit_buy_cancel.slot");
        KIT_BUY_CANCEL_LORE = new ArrayList<>();
        instance.getConfig().getStringList("kit_buy_cancel.lore").forEach(string -> KIT_BUY_CANCEL_LORE.add(StringUtil.color(string)));
        SIGN_LINE_ONE = StringUtil.color(instance.getConfig().getString("sign.line_one"));
        SIGN_LINE_TWO = StringUtil.color(instance.getConfig().getString("sign.line_two"));

        //Messages
        NO_PERMISSION = StringUtil.color(instance.getConfig().getString("NO_PERMISSION"));
        NOT_A_PLAYER = StringUtil.color(instance.getConfig().getString("NOT_A_PLAYER"));
        USAGE = StringUtil.color(instance.getConfig().getString("USAGE"));
        ARENA_HELP = new ArrayList<>();
        instance.getConfig().getStringList("ARENA_HELP").forEach(string -> ARENA_HELP.add(StringUtil.color(string)));
        ARENA_ALREADY_EXISTS = StringUtil.color(instance.getConfig().getString("ARENA_ALREADY_EXISTS"));
        ARENA_ONLY_LETTERS = StringUtil.color(instance.getConfig().getString("ARENA_ONLY_LETTERS"));
        ARENA_CREATED = StringUtil.color(instance.getConfig().getString("ARENA_CREATED"));
        ARENA_DOES_NOT_EXIST = StringUtil.color(instance.getConfig().getString("ARENA_DOES_NOT_EXIST"));
        ARENA_DELETED = StringUtil.color(instance.getConfig().getString("ARENA_DELETED"));
        ARENA_LOCATION_SET = StringUtil.color(instance.getConfig().getString("ARENA_LOCATION_SET"));
        ARENA_LOCATION_NOT_SET = StringUtil.color(instance.getConfig().getString("ARENA_LOCATION_NOT_SET"));
        LOBBY_LOCATION_SET = StringUtil.color(instance.getConfig().getString("LOBBY_LOCATION_SET"));
        JOINED_ARENA = StringUtil.color(instance.getConfig().getString("JOINED_ARENA"));
        NOT_ENOUGH_MONEY = StringUtil.color(instance.getConfig().getString("NOT_ENOUGH_MONEY"));
        BOUGHT_KIT = StringUtil.color(instance.getConfig().getString("BOUGHT_KIT"));
        SELECTED_KIT = StringUtil.color(instance.getConfig().getString("SELECTED_KIT"));
        STATS = new ArrayList<>();
        instance.getConfig().getStringList("STATS").forEach(string -> STATS.add(StringUtil.color(string)));

        instance.getServer().getConsoleSender().sendMessage(StringUtil.color("&6[KitPvP] &aThe configuration has been loaded"));
    }
}
