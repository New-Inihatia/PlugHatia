package newinihatia.plughatia.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerChangeMode implements Listener {

    @EventHandler
    public static void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {

        Player player = event.getPlayer();
        GameMode gameMode = event.getNewGameMode();
        Bukkit.broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + " has gone into " + gameMode + " mode.");

    }

}
