package newinihatia.plughatia.events.effects;

import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayerSneak implements Listener {

    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerObj JSONplayer = PlayerStorageUtil.findPlayer(uuid);

        Biome playerBiome = player.getWorld().getBiome(player.getLocation());
        List<Biome> hobbitSneakBiomes = Arrays.asList(Biome.PLAINS, Biome.FOREST, Biome.TAIGA, Biome.SAVANNA, Biome.DARK_FOREST, Biome.MUSHROOM_FIELDS, Biome.JUNGLE, Biome.SPARSE_JUNGLE);
        List<Biome> elfSneakBiomes = Arrays.asList(Biome.FOREST, Biome.DARK_FOREST, Biome.JUNGLE, Biome.TAIGA);


        if (JSONplayer.getRace().equalsIgnoreCase("Hobbit") && hobbitSneakBiomes.contains(playerBiome)) {
            // .isSneaking() returns the sneaking status of the player BEFORE the event
            if (!player.isSneaking()) {
                player.sendMessage("You are hidden!");
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, PotionEffect.INFINITE_DURATION, 0, false, true));
            }
            else if (player.isSneaking()) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    if (effect.getType() == PotionEffectType.INVISIBILITY) {
                        player.sendMessage("You are no longer hidden!");
                        player.removePotionEffect(effect.getType());
                    }
                }
            }
        }
        else if (JSONplayer.getRace().equalsIgnoreCase("Elf") && elfSneakBiomes.contains(playerBiome)) {
            // .isSneaking() returns the sneaking status of the player BEFORE the event
            if (!player.isSneaking()) {
                player.sendMessage("You are hidden!");
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, PotionEffect.INFINITE_DURATION, 0, false, false));
            }
            else if (player.isSneaking()) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    if (effect.getType() == PotionEffectType.INVISIBILITY) {
                        player.sendMessage("You are no longer hidden!");
                        player.removePotionEffect(effect.getType());
                    }
                }
            }
        }
    }
}
