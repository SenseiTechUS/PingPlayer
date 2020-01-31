package dev.audaxius.pingplayer;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PingPlayer extends JavaPlugin implements Listener {

    PluginManager pluginManager;

    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(this, this);
        getLogger().info("PingPlayer is Enabled!");
    }

    @Override
    public void onDisable() {

        getLogger().info("PingPlayer is Disabled!");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMentionInChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        for (Player player : getServer().getOnlinePlayers()) {
            String name = player.getDisplayName();
            if (message.contains(name)) {
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1, 1);

                String first = message.substring(0,message.indexOf(name));
                String last = message.substring(message.indexOf(name)+name.length());
                event.setMessage(first + ChatColor.AQUA + name + ChatColor.RESET + last);
            }
        }
    }


}
