package io.github.staudx.catalyst.inventory;

import io.github.staudx.catalyst.CatalystPlugin;
import io.github.staudx.catalyst.util.BungeeUtil;
import io.github.staudx.catalyst.util.CC;
import io.github.staudx.catalyst.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ServerSelector implements Listener {

    private final String bullet_point = "•", right_arrow = "»", left_arrow = "«";

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getItem() == null) {
            event.setCancelled(true);

        } else {

            if (CC.translate(CatalystPlugin.get().getConfig().getString("ITEM.SERVER_SELECTOR.NAME")).equalsIgnoreCase(event.getItem().getItemMeta().getDisplayName())) {
                Inventory inventory = Bukkit.createInventory(null, CatalystPlugin.get().getConfig().getInt("SERVER_SELECTOR.SIZE"), CC.translate(CatalystPlugin.get().getConfig().getString("SERVER_SELECTOR.NAME")));
                player.openInventory(inventory);
                IntStream.range(0, CatalystPlugin.get().getConfig().getInt("SERVER_SELECTOR.SIZE")).forEach(i -> inventory.setItem(i, new ItemBuilder(Material.getMaterial(
                        CatalystPlugin.get().getConfig().getString("GLASS_PANE.MATERIAL")), CatalystPlugin.get().getConfig().getInt("GLASS_PANE.AMOUNT"), (short) CatalystPlugin.get().getConfig().getInt("GLASS_PANE.VALUE"))
                        .setName(CatalystPlugin.get().getConfig().getString("GLASS_PANE.NAME"))
                        .create()));

                List<String> example_lore = new ArrayList<>();
                CatalystPlugin.get().getConfig().getStringList("SERVER_SELECTOR.SERVERS.1.LORE").forEach(string -> example_lore.add(CC.translate(string
                        .replace("%bullet_point%", bullet_point))
                        .replace("%right_arrow%", right_arrow)
                        .replace("%left_arrow%", left_arrow)));
                ItemStack example = new ItemBuilder(Material.getMaterial(CatalystPlugin.get().getConfig().getString("SERVER_SELECTOR.SERVERS.1.MATERIAL")))
                        .setName(CC.translate(CatalystPlugin.get().getConfig().getString("SERVER_SELECTOR.SERVERS.1.NAME")))
                        .setLore(example_lore)
                        .create();
                inventory.setItem(CatalystPlugin.get().getConfig().getInt("SERVER_SELECTOR.SERVERS.1.SLOT"), example);

                player.updateInventory();
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
            Player player = (Player) event.getWhoClicked();

            if(event.getInventory() != null && CC.translate(CatalystPlugin.get().getConfig().getString("SERVER_SELECTOR.NAME")).equalsIgnoreCase(event.getInventory().getName())) {
                if(CC.translate(CatalystPlugin.get().getConfig().getString("SERVER_SELECTOR.SERVERS.1.NAME")).equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                    BungeeUtil.sendToServer(player, CatalystPlugin.get().getConfig().getString("SERVER_SELECTOR.SERVERS.1.SERVER"));
                }
            }
        }
    }