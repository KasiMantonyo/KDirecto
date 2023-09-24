package org.kasimantonyo.kdirecto;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import comandos.ComandoAdmin;
import comandos.ComandoDirecto;
import utils.CC;
import utils.CooldownManager;

public final class Main extends JavaPlugin {

    PluginDescriptionFile pdffile = getDescription();
    public String version = pdffile.getVersion();
    public String autor = String.join(", ", pdffile.getAuthors());
    private ComandoDirecto ComandoDirecto;
    private ComandoAdmin ComandoAdmin;
    public static Pattern ComprobarLink;
    public static String invalidLink;
    public static String Configrecargada;
    public static String cooldownMsg;
    public static String Usocorrecto;
    public static String sonido;
    public static CooldownManager cooldownManager;
    public static String SinPermiso;
    public static List<String> anuncio;
    public static int cooldownTime;
    public static FileConfiguration config;

    ConsoleCommandSender c = getServer().getConsoleSender();

    @Override
    public void onEnable() {
        saveDefaultConfig();


        ComprobarLink = Pattern.compile("^https?://(?:www\\.)?(?:twitch\\.tv|youtube\\.com|instagram\\.com|kick\\.com|tiktok\\.com|trovo\\.live)/\\S+$");

        ComandoDirecto = new ComandoDirecto(this);
        ComandoAdmin = new ComandoAdmin(this);

        getCommand("directo").setExecutor(ComandoDirecto);
        getCommand("kdirecto").setExecutor(ComandoAdmin);


        c.sendMessage(CC.translate("&e      &aKDirecto  "));
        c.sendMessage(CC.translate("&e"));
        c.sendMessage(CC.translate("&e¡KDirecto ha sido habilitado!"));
        c.sendMessage(CC.translate("&e"));
        c.sendMessage(CC.translate("&eVersion: " + version)); // version
        c.sendMessage(CC.translate("&eAuthor: " + autor)); // autor
        c.sendMessage(CC.translate("&e"));
        c.sendMessage(CC.translate("&e¡Gracias por utilizar el plugin!"));
        c.sendMessage(CC.translate("&eReporta errores en discord: KasiGames#6059"));
        c.sendMessage(CC.translate("&e"));

        ConfigUtils();

    }
    public void onDisable() {

        c.sendMessage(CC.translate("&e      &aKDirecto  "));
        c.sendMessage(CC.translate("&e"));
        c.sendMessage(CC.translate("&eDesactivando KDirecto..."));
        c.sendMessage(CC.translate("&e"));
        c.sendMessage(CC.translate("&eVersion: " + version));
        c.sendMessage(CC.translate("&eAuthor: " + autor));
        c.sendMessage(CC.translate("&e"));
        c.sendMessage(CC.translate("&e¡Gracias por utilizar el plugin!"));
        c.sendMessage(CC.translate("&eReporta errores en discord: KasiGames#6059"));
        c.sendMessage(CC.translate("&e"));

    }

    public void ConfigUtils() {
        config = getConfig();

        cooldownManager = new CooldownManager();
        cooldownTime = config.getInt("cooldown", 60);
        cooldownMsg = ((config.getString("CooldownMsg")));
        invalidLink = (config.getString(("InvalidLink")));
        Configrecargada = (config.getString(("ConfigMsg")));
        Usocorrecto = (config.getString(("CorrectUse")));
        anuncio = config.getStringList("Message");
        sonido = config.getString("Sound");
        SinPermiso = config.getString("NoPerms");

        if (sonido != null && sonido.equalsIgnoreCase("NONE")) {
            sonido = null; //
        }

    }

}
