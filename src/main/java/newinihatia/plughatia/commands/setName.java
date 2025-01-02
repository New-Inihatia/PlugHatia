package newinihatia.plughatia.commands;

import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class setName implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        

        if (!cmd.getName().equalsIgnoreCase("setName")) {
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage("Command format: setName <target_player> <name>");
            return false;
        }

        String name = "";
        for (int i = 1; i < args.length; i++) {
            name += args[i] + " ";
        }
        name = name.trim();
        String targetUsername = args[0];

        PlayerObj JSONplayer = PlayerStorageUtil.findPlayer(targetUsername);
        if (JSONplayer == null) {
            sender.sendMessage("Player "  + targetUsername + " not found!");
            return false;
        }

        UUID uuid = JSONplayer.getUuid();
        PlayerObj newJSONplayer = JSONplayer;
        newJSONplayer.setName(name);
        PlayerStorageUtil.updatePlayer(uuid, newJSONplayer);

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if (offlinePlayer.isOnline()) {
            Player player = offlinePlayer.getPlayer();
            player.setDisplayName(name);
        }

        sender.sendMessage(ChatColor.GOLD + "Successfully set name of " + targetUsername + " to " + name + "!");

        return true;

    }

}