package newinihatia.plughatia.events;

import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class PlayerJoin implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to New Inihatia!");

        PlayerObj JSONplayer = PlayerStorageUtil.findPlayer(uuid);

        if (JSONplayer == null) {
            PlayerStorageUtil.createPlayer(player, "Traveler", "Traveler");
            JSONplayer = PlayerStorageUtil.findPlayer(uuid);
            player.setDisplayName(JSONplayer.getName());
        }
        else if (JSONplayer != null) {

//            ScoreboardManager manager = Bukkit.getScoreboardManager();
//            Scoreboard board = manager.getNewScoreboard();

            player.setDisplayName(JSONplayer.getName());

            if (JSONplayer.getRace().equalsIgnoreCase("Traveler")) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Human")) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Hobbit")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, PotionEffect.INFINITE_DURATION, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, PotionEffect.INFINITE_DURATION, 0, false, false));
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(22);
//                board.getTeam("Hobbits").addPlayer((OfflinePlayer) player);
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Elf")) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Dwarf")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, PotionEffect.INFINITE_DURATION, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, PotionEffect.INFINITE_DURATION, 0, false, false));
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(22);
            }
            else if (JSONplayer.getRace().equalsIgnoreCase("Orc")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, PotionEffect.INFINITE_DURATION, 0, false, false));
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            }
        }

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
