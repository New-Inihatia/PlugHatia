package newinihatia.plughatia.events;

import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MobNeutralityTowardsOrcs implements Listener {
    private static HashMap<UUID, List<UUID>> mobTargets = new HashMap<>();

    @EventHandler
    public static void onEntityTarget(EntityTargetEvent event) {
        UUID entityUUID = event.getEntity().getUniqueId();
        Entity target = event.getTarget();

        if (target == null) {
            return;
        }
        if (target.getType() != EntityType.PLAYER) {
            return;
        }
        if (!PlayerStorageUtil.findPlayer(target.getUniqueId()).getRace().equalsIgnoreCase("Orc")) {
            return;
        }
        if (!mobTargets.containsKey(entityUUID)) {
            event.setCancelled(true);
            return;
        }
        else {
            if (!mobTargets.get(entityUUID).contains(target.getUniqueId())) {
                event.setCancelled(true);
            }
            return;
        }
        // if it has made it thus far, it is cancelled
    }

    @EventHandler
    public static void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        List<EntityType> validEntities = Arrays.asList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.SPIDER);

        if (!validEntities.contains(entity.getType())) {
            return;
        }
        if (event.getDamageSource().getCausingEntity() == null) {
            return;
        }
        if (event.getDamageSource().getCausingEntity().getType() != EntityType.PLAYER) {
            return;
        }
        PlayerObj player = PlayerStorageUtil.findPlayer(event.getDamageSource().getCausingEntity().getUniqueId());
        if (!player.getRace().equalsIgnoreCase("Orc")) {
            return;
        }
        if (mobTargets.containsKey(entity.getUniqueId())) {
            List<UUID> newTargets = mobTargets.get(entity.getUniqueId());
            newTargets.add(event.getDamageSource().getCausingEntity().getUniqueId());
            mobTargets.put(entity.getUniqueId(), newTargets);
        }
        else {
            mobTargets.put(entity.getUniqueId(), Arrays.asList(event.getDamageSource().getCausingEntity().getUniqueId()));
        }
    }
}
