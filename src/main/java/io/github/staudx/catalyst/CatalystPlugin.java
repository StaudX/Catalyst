package io.github.staudx.catalyst;

import io.github.staudx.catalyst.inventory.ServerSelector;
import io.github.staudx.catalyst.listener.CataylstListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class CatalystPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // load configuration
        saveDefaultConfig();

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Catalyst has loaded.");

        this.registerListeners();
    }

    private void registerListeners() {
        Arrays.asList(
                new ServerSelector(),
                new CataylstListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public static CatalystPlugin get() {
        return getPlugin(CatalystPlugin.class);
    }
}
