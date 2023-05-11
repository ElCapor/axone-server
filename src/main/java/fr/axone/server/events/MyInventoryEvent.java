package fr.axone.server.events;

import fr.axone.server.commands.MyInventory;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MyInventoryEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {

        // We're getting the clicked inventory to avoid situations where the player
        // already has a stone in their inventory and clicks that one.
        Inventory inventory = event.getClickedInventory();
        // Add a null check in case the player clicked outside the window.
        if (inventory == null || !(inventory.getHolder() instanceof MyInventory myInventory)) {
            return;
        }

        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        // Check if the player clicked the stone.
        if (clicked != null && clicked.getType() == Material.STONE) {
            // Use the method we have on MyInventory to increment the field
            // and update the counter.
            myInventory.addClick();
        }
    }
}
