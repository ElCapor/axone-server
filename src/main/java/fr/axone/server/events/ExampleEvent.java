package fr.axone.server.events;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import fr.axone.server.MainPlugin;
import fr.axone.server.commands.PinCode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

public class ExampleEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPaperServerListPing(final PaperServerListPingEvent e) {
        Bukkit.getLogger().info("Pong!");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        // log this into our console !
        Bukkit.getLogger().info("Player joined " + event.getPlayer().getName() + " ! Welcome to AxoneSMP V0.1 Alpha");
        event.getPlayer().openInventory(new PinCode(MainPlugin.myPlugin).getInventory());
        // send it to all players
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.sendMessage("Player joined " + event.getPlayer().getName() + " ! Welcome to AxoneSMP V0.1 Alpha");
        }

    }
}
