package fr.axone.server.commands;

import fr.axone.server.MainPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MyInventory implements InventoryHolder {
    private final Inventory inventory;
    private int clicks = 0;
    public MyInventory(MainPlugin plugin) {
        this.inventory = plugin.getServer().createInventory(this, 9);
        // Set the stone that we're going to be clicking.
        this.inventory.setItem(1, new ItemStack(Material.STONE));
    }
    // A method we will call in the listener whenever the player clicks the stone.
    public void addClick() {
        this.clicks++;
        this.updateCounter();
    }

    // A method that will update the counter item.
    private void updateCounter() {
        this.inventory.setItem(8, new ItemStack(Material.BEDROCK, this.clicks));
    }
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

}
