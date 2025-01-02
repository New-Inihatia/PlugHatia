package newinihatia.plughatia.events.effects;

import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class HumanResilience implements Listener {

    public static HashMap<UUID, Long> cooldown;

    public static void init(int maxPlayerCount) {
        cooldown = new HashMap<UUID, Long>(maxPlayerCount);
    }

    @EventHandler
    public static void onPlayerHurt(EntityDamageByEntityEvent e) {

        if (e.getEntityType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) e.getEntity();
        UUID uuid = player.getUniqueId();
        PlayerObj JSONplayer = PlayerStorageUtil.findPlayer(uuid);


        if (!JSONplayer.getRace().equalsIgnoreCase("Human")) {
            return;
        }

        double playerHealth = player.getHealth() - e.getDamage();

        if (!(playerHealth < 9.0)) { // If the player does not have less than 4.5 hearts left
            return;
        }

        Long oldTime = cooldown.get(uuid);

        if (oldTime == null) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20 * 10, 2, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 20 * 10, 1, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 12, 2, false, false));
            cooldown.put(uuid, System.currentTimeMillis());
            return;
        } else if (System.currentTimeMillis() - oldTime > 40 * 1000) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20 * 10, 2, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 20 * 10, 1, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 12, 2, false, false));
            cooldown.put(uuid, System.currentTimeMillis());
            return;
        }
    }

}
