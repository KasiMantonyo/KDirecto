package utils;

import org.bukkit.entity.Player;

import org.kasimantonyo.kdirecto.Main;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {
    private Map<String, Long> cooldowns;

    public CooldownManager() {
        cooldowns = new HashMap<>();
    }

    public void setCooldown(Player player, long cooldownSeconds) {
        long currentTime = System.currentTimeMillis();
        long cooldownMillis = cooldownSeconds * 1000;
        long expirationTime = currentTime + cooldownMillis;
        cooldowns.put(player.getName(), expirationTime);
    }

    public boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player.getName()) && System.currentTimeMillis() < cooldowns.get(player.getName());
    }

    public long getRemainingTime(Player player) {
        if (cooldowns.containsKey(player.getName())) {
            long currentTime = System.currentTimeMillis();
            long expirationTime = cooldowns.get(player.getName());
            long remainingTimeMillis = expirationTime - currentTime;
            return remainingTimeMillis / 1000; // Convertir a segundos
        }
        return 0;
    }
}