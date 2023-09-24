package api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class KDirectoAPI {

    private static String directoMsg;
    private static DirectoAction directoAction;

    public static String getDirectoMessage() {
        return directoMsg;
    }

    public static void setDirectoMessage(String msg) {
        directoMsg = msg;
    }

    public static void startDirecto(Player p, String directoLink) {
        if (directoAction != null) {
            directoAction.onDirectoStart(p, directoLink);
        }
    }

    public static boolean isPlayerInDirecto(Player player) {
        return false;
    }

    public static long getRemainingCooldown(Player player) {
        return 0;
    }

    public static void setDirectoAction(DirectoAction action) {
        directoAction = action;
    }

    public static DirectoAction getDirectoAction() {
        return directoAction;
    }

    public interface DirectoAction {
        void onDirectoStart(Player player, String directoLink);
    }

    public static class DirectoUtils {
        public static boolean isValidDirectoLink(String link) {
            return false;
        }
    }

    public static class DirectoStartEvent extends Event implements Listener {

        private static final HandlerList handlers = new HandlerList();
        private final Player player;
        private final String directoLink;

        public DirectoStartEvent(Player player, String directoLink) {
            this.player = player;
            this.directoLink = directoLink;
        }

        public Player getPlayer() {
            return player;
        }

        public String getDirectoLink() {
            return directoLink;
        }

        public HandlerList getHandlers() {
            return handlers;
        }

        public static HandlerList getHandlerList() {
            return handlers;
        }
    }
}
