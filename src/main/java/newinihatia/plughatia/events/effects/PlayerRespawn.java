package newinihatia.plughatia.events.effects;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.events.BannedItems;
import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class PlayerRespawn implements Listener {

    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent e) {

        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerObj JSONplayer = PlayerStorageUtil.findPlayer(uuid);

        Bukkit.getScheduler().runTaskLater(PlugHatia.getPlugin(), task -> {

            if (JSONplayer.getRace().equalsIgnoreCase("Traveler")) {

            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Human")) {

            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Hobbit")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 1000000000, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 1000000000, 0, false, false));
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Elf")) {
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Dwarf")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 1000000000, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 1000000000, 0, false, false));
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Orc")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 1000000000, 0, false, false));
            }

        }, 5L);

        ItemStack[] playerInv = player.getInventory().getContents();
        for (ItemStack invTemp : playerInv) {
            for (ItemStack remTemp : BannedItems.banned) {
                if (invTemp != null) {
                    if (invTemp.getType().equals(remTemp.getType())) {
                        player.getInventory().removeItem(invTemp);
                        player.sendMessage(ChatColor.DARK_RED + "You have been caught with a banned item (" + invTemp + ") and it has been removed from your inventory.\n" +
                                "If you think this was a mistake, please contact a Staff member.");
                        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + player.getName() + " has been caught with a banned item (" + invTemp + ") and it has been removed from their inventory.");
                    }
                }
            }
        }

    }

}
