package fr.axone.server.commands;

import fr.axone.server.MainPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class PinCode implements InventoryHolder {
    private final Inventory inventory;
    //map index to number
    public static Map<Integer, Integer> numMap = new HashMap<Integer, Integer>() {{
            put(1, 12);
            put(2, 13);
            put(3, 14);
            put(4,21);
            put(5, 22);
            put(6,23);
            put(7,30);
            put(8,31);
            put(9,32);

    }};
    public PinCode(MainPlugin plugin) {
        this.inventory = plugin.getServer().createInventory(this, 36);

        // Load our pin codes into the inventory
        for (Map.Entry<Integer, Integer> map : numMap.entrySet())
        {
            ItemStack newItemStack = new ItemStack(Material.GREEN_WOOL, map.getKey());
            ItemMeta newItemStackMeta = newItemStack.getItemMeta();
            newItemStackMeta.setDisplayName(Integer.toString(map.getKey()));
            newItemStack.setItemMeta(newItemStackMeta);
            this.inventory.setItem(map.getValue(), newItemStack);
        }

        ItemStack clear_btn = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta clear_btn_meta = clear_btn.getItemMeta();
        clear_btn_meta.setDisplayName(ChatColor.RED + "Clear Code");
        clear_btn.setItemMeta(clear_btn_meta);
        this.inventory.setItem(26, clear_btn); //

        ItemStack enter_btn = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta enter_btn_meta = enter_btn.getItemMeta();
        enter_btn_meta.setDisplayName(ChatColor.GREEN + "Validate Code");
        enter_btn.setItemMeta(enter_btn_meta);
        this.inventory.setItem(25, enter_btn);
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }

    //erease our code when user wants
    public void clearCode()
    {
        this.inventory.setItem(0, null);
        this.inventory.setItem(1, null);
        this.inventory.setItem(2, null);

    }
    // validate the code


    //process our click right here
    public void processClick(InventoryClickEvent event)
    {
        ItemStack clicked = event.getCurrentItem();
        if (clicked != null && clicked.getType() == Material.GREEN_WOOL)
        {
            int number_choosed = clicked.getAmount();
            //event.getWhoClicked().sendMessage("You choosed to click on : " + Integer.toString(number_choosed));
            int current_index = 0;
            if (this.inventory.getItem(0) != null)
            {
                if (this.inventory.getItem(1 ) != null)
                {
                    current_index = 2;
                }
                else {
                    current_index = 1;

                }
            }
            this.inventory.setItem(current_index, new ItemStack(Material.ARROW, number_choosed));
        } else if (clicked != null && clicked.getType() == Material.RED_STAINED_GLASS_PANE) {
            event.getWhoClicked().sendMessage("Clearing code...");
            clearCode();

        } else if (clicked != null && clicked.getType() == Material.GREEN_STAINED_GLASS_PANE) {
            event.getWhoClicked().sendMessage("Validating Code...");
            String code ="";
            // bruh
            for (int i=0; i<3; i++)
            {
                if (this.inventory.getItem(i) != null)
                {
                    code += Integer.toString(this.inventory.getItem(i).getAmount());
                    //event.getWhoClicked().sendMessage(Integer.toString(this.inventory.getItem(i).getAmount()));
                }
            }

            //event.getWhoClicked().sendMessage("Your code is : " + code + "t");
            if (code.equalsIgnoreCase("123"))
            {
                // trash build system e
                event.getWhoClicked().sendMessage("Correct code !");
                event.getInventory().close();
            }
            else
            {
                // try again lll

                //Bukkit.getPlayer(event.getWhoClicked().getName()).kick(Component.text("Incorrect code ! Try again ..."));

            }
        }

    }
}
