package comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.kasimantonyo.kdirecto.Main;
import utils.CC;

public class ComandoAdmin implements CommandExecutor {

    private Main plugin;

    public ComandoAdmin(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {

        if (a.length == 0) {
            s.sendMessage(CC.translate("&aUsando &eKDirecto" + plugin.getDescription().getVersion()));
            s.sendMessage(CC.translate("&aUtiliza &a&n/kdirecto help&r&a para una lista de comandos!"));
            return true;
        }

        if (a.length > 0) {
            if (a[0].equalsIgnoreCase("reload")) {

                if (!s.hasPermission("kdirecto.reload")) {
                    s.sendMessage(CC.translate((Main.SinPermiso)));
                    return true;
                }
                plugin.reloadConfig();
                Main.config = plugin.getConfig();
                Main.anuncio = Main.config.getStringList("Message");
                Main.cooldownTime = Main.config.getInt("cooldown", 60);
                Main.invalidLink = CC.translate(Main.config.getString("InvalidLink"));
                Main.Configrecargada = Main.config.getString("ConfigMsg");
                Main.SinPermiso = Main.config.getString("NoPerms");
                Main.cooldownMsg = Main.config.getString("CooldownMsg");
                Main.Usocorrecto = Main.config.getString("CorrectUse");
                Main.sonido = Main.config.getString("Sound");

                plugin.saveConfig();

                s.sendMessage(CC.translate((Main.Configrecargada)));

                return true;
            } else if (a[0].equalsIgnoreCase("help")) {

                if (!s.hasPermission("kdirecto.help")) {
                    s.sendMessage(CC.translate((Main.SinPermiso)));
                    return true;
                }

                s.sendMessage(CC.translate(""));
                s.sendMessage(CC.translate("&eKDirecto &8| &fLista de comandos"));
                s.sendMessage(CC.translate(""));
                s.sendMessage(CC.translate("&f/kdirecto reload &8- &r&fRecarga la configuración del plugin"));
                s.sendMessage(CC.translate("&f/kdirecto help &8- &r&fMuestra esta lista de comandos"));
                s.sendMessage(CC.translate(""));
                s.sendMessage(CC.translate("&fVersión: &e" + plugin.getDescription().getVersion()));
                return true;
            }
        }

        return false;
    }
}