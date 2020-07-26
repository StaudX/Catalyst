package io.github.staudx.catalyst.util;

import org.bukkit.ChatColor;

public class CC {

    public static String translate (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}