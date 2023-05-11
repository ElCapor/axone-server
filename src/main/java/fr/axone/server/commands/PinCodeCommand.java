package fr.axone.server.commands;

import fr.axone.server.MainPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PinCodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("pincode")) {
            sender.sendMessage("Open PinCode menu");
            Player plr = (Player)sender;
            PinCode pinCode = new PinCode(MainPlugin.myPlugin);
            plr.openInventory(pinCode.getInventory());

        }

        return true;

    }


}
