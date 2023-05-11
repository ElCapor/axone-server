package fr.axone.server;

import fr.axone.server.commands.ExampleCommand;
import fr.axone.server.commands.PinCodeCommand;
import fr.axone.server.events.ExampleEvent;
import fr.axone.server.bStats.Metrics;
import fr.axone.server.events.PinCodeEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import fr.axone.server.events.MyInventoryEvent;

public final class MainPlugin extends JavaPlugin {
    public static int id = 12345; // Constant plugin id from bStats
    public static MainPlugin myPlugin;
    @Override
    public void onEnable() {
        // What should this plugin do on startup
        myPlugin = this;
        getServer().getPluginManager().registerEvents(new ExampleEvent(), this);
        getServer().getPluginManager().registerEvents(new MyInventoryEvent(), this);
        getServer().getPluginManager().registerEvents(new PinCodeEvent(), this);
        PluginCommand hello = getCommand("hello");
        hello.setExecutor(new ExampleCommand());

        PluginCommand pinCode = getCommand("pincode");
        pinCode.setExecutor(new PinCodeCommand());

        Bukkit.getLogger().warning("Started");
        // bStats metrics
        Metrics m = getMetrics(id);
    }

    @Override
    public void onDisable() {
        // Should this plugin do anything when it is disabled?
    }

    private @NotNull Metrics getMetrics(int serviceId) {
        return new Metrics(this, serviceId);
    }
}

