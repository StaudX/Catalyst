package io.github.staudx.catalyst.listener;

import io.github.staudx.catalyst.CatalystPlugin;
import io.github.staudx.catalyst.util.CC;
import io.github.staudx.catalyst.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class CataylstListener implements Listener {

    private CatalystPlugin plugin;

    public CataylstListener(CatalystPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());

        ItemStack server_selector = new ItemBuilder(Material.getMaterial(CatalystPlugin.get().getConfig().getString("ITEM.SERVER_SELECTOR.MATERIAL")))
                .setName(CC.translate(CatalystPlugin.get().getConfig().getString("ITEM.SERVER_SELECTOR.NAME")))
                .create();
        player.getInventory().setItem(CatalystPlugin.get().getConfig().getInt("ITEM.SERVER_SELECTOR.SLOT"), server_selector);
        
        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }
        CatalystPlugin.get().getConfig().getStringList("WELCOME_MESSAGE").forEach(string -> player.sendMessage(CC.translate(string
                .replace("%bullet_point%", "•"))));
    }

    /*
    Weather doesn't change.
     */

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    /*
    Allows every player to take no hunger loss.
     */

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    /*
    Stops all entitys from spawning.
     */

    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    /*
    Stops pvp action between players.
     */

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    /*
    Stops Entity Damage
     */
    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    /*
    Stops people from dropping items
     */
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    /*
    Stops people from picking up items what have somehow been dropped.
     */
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    /*
    Stops people from breaking blocks
     */
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
         Player player = event.getPlayer();
         if(!player.hasPermission("catalyst.break")) {
             event.setCancelled(true);
         }
    }

    /*
    Stops people from placing blocks
     */
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("catalyst.place")) {
            event.setCancelled(true);
        }
    }

    /*
    Inventory Click Event
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
