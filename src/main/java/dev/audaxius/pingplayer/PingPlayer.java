package dev.audaxius.pingplayer;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PingPlayer extends JavaPlugin implements Listener {

    PingPlayer plugin;
    PluginManager pluginManager;

    @Override
    public void onEnable() {
        plugin = this;
        pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(this, plugin);
        getLogger().info("PingPlayer is Enabled!");
    }

    @Override
    public void onDisable() {

        getLogger().info("PingPlayer is Disabled!");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMentionInChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (message.contains(player.getDisplayName())) {
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1, 1);
                event.setMessage(message);
            }
        }
    }


}