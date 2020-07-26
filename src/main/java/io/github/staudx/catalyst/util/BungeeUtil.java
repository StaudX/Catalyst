package io.github.staudx.catalyst.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.github.staudx.catalyst.CatalystPlugin;
import org.bukkit.entity.Player;

public class BungeeUtil {

    /*
    When a player clicks on a item in the server selector, this class helps send that player to the server they clicked on.
    Also I am terrible at explaining things, sorry.
     */

    public static void sendToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(CatalystPlugin.get(), "BungeeCord", out.toByteArray());
    }
}