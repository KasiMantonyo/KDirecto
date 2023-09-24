package comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.kasimantonyo.kdirecto.Main;

import utils.CC;
import utils.CooldownManager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandoDirecto implements CommandExecutor {

    private Main plugin;

    public ComandoDirecto(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {

        if (!s.hasPermission("kdirecto.directo")) {
            s.sendMessage(CC.translate((Main.SinPermiso)));
            return true;
        }


        if (!(s instanceof Player)) {
            s.sendMessage(CC.translate("&cEste comando solamente puede ser utilizado por jugadores!"));
            s.sendMessage(CC.translate("&cOnly player can execute this command!"));
            return true;
        }

        // -----------------------------------------------------------------------------------------

        Player p = (Player) s;

        // if (Main.cooldownManager.isOnCooldown(p)) {
        // long remainingTime = Main.cooldownManager.getRemainingTime(p);
        // s.sendMessage(CC.translate("&cDebes esperar &c&n &r&c segundo(s) antes de usar este comando nuevamente."));
        // .replace ("%cooldown%", + remainingTime +));
        // return true;
        // }

        // Cooldown

        boolean bypassCooldown = p.hasPermission("kdirecto.bypass.cooldown");

        if (!bypassCooldown && Main.cooldownManager.isOnCooldown(p)) {
            long tiempoRestante = Main.cooldownManager.getRemainingTime(p);
            String cooldownMsg = Main.config.getString("CooldownMsg");
            cooldownMsg = cooldownMsg.replace("%cooldown%", String.valueOf(tiempoRestante));
            s.sendMessage(CC.translate(cooldownMsg));
            return true;
        }

        if (a.length < 1) {
            s.sendMessage(CC.translate(Main.Usocorrecto));
            return true;
        }

        String link = a[0];

        Matcher verificar = Main.ComprobarLink.matcher(link);
        if (!verificar.matches()) {
            s.sendMessage(CC.translate((Main.invalidLink)));
            return true;
        }

        Main.cooldownManager.setCooldown(p, Main.cooldownTime);

        for (String anuncioMensaje : Main.anuncio) {
            anuncioMensaje = anuncioMensaje.replace("%link%", link)
                    .replace("%player%", p.getName());


            Bukkit.broadcastMessage(CC.translate(anuncioMensaje));
        }

        String sonido = Main.sonido;
        if (sonido != null && !sonido.equalsIgnoreCase("NONE")) {
            try {
                Sound sound = Sound.valueOf(sonido);
                p.playSound(p.getLocation(), sound, 1.0f, 1.0f);
            } catch (IllegalArgumentException e) {
                s.sendMessage(CC.translate("&cEl sonido en la sección 'sound' es inválido, cámbialo por uno válido. Ejemplo: VILLAGER_YES"));
                e.printStackTrace();
            }

            return true;
        }
        return bypassCooldown;
    }
}
//  -----------------------------------------------------------------------------------------