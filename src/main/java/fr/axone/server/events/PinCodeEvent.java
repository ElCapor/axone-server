package fr.axone.server.events;

import fr.axone.server.commands.PinCode;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PinCodeEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {
        //event.getWhoClicked().sendMessage("Processing Event...");
        // We're getting the clicked inventory to avoid situations where the player
        // already has a stone in their inventory and clicks that one.
        Inventory inventory = event.getClickedInventory();
        // Add a null check in case the player clicked outside the window.
        if (inventory == null || !(inventory.getHolder() instanceof PinCode myInventory)) {
            return;
        }

        event.setCancelled(true);
        myInventory.processClick(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        //event.getWhoClicked().sendMessage("Processing Event...");
        // We're getting the clicked inventory to avoid situations where the player
        // already has a stone in their inventory and clicks that one.
        Inventory inventory = event.getInventory();
        // Add a null check in case the player clicked outside the window.
        if (inventory == null || !(inventory.getHolder() instanceof PinCode myInventory)) {
            return;
        }
        // fix ????????
        // too tash dkzdz
        Bukkit.getLogger().info(event.getReason().toString());
        // fix 2023 build is trash
        if (event.getReason() != InventoryCloseEvent.Reason.PLUGIN)
        {
            if (event.getReason() != InventoryCloseEvent.Reason.CANT_USE)
            {
                Bukkit.getPlayer(event.getPlayer().getName()).kick(Component.text("Please input a pin code !"));

            }
        }


    }
}
