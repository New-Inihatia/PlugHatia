package newinihatia.plughatia.events.effects;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.objects.PlayerObj;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class OrcsAndAllies implements Listener {
    private static UUID latestSpawnerUUID;
    private static ItemStack lastSpawnEgg = null;
    private static boolean autoTarget;
    private static ItemStack helmet;
    private static ItemStack chestplate;
    private static ItemStack leggings;
    private static ItemStack boots;
    private static ItemStack tool;
    private static Map<UUID, Set<UUID>> masters = new HashMap<>(); // master: allies
    private static Map<UUID, Set<UUID>> mobs = new HashMap<>(); // mob: targets
    private static Map<UUID, Boolean> alliesAutoTarget = new HashMap<>();
    private static double allyFollowDistance = 8;
    private static float orcDropChance = 0.5f;

    private static final List<Material> validEggs = List.of(
            Material.ZOMBIE_SPAWN_EGG,
            Material.HUSK_SPAWN_EGG,
            Material.SKELETON_SPAWN_EGG,
            Material.STRAY_SPAWN_EGG,
            Material.CREEPER_SPAWN_EGG,
            Material.SPIDER_SPAWN_EGG,
            Material.CAVE_SPIDER_SPAWN_EGG
    );
    private static final List<EntityType> validEntities = Arrays.asList(
            EntityType.ZOMBIE,
            EntityType.HUSK,
            EntityType.SKELETON,
            EntityType.STRAY,
            EntityType.CREEPER,
            EntityType.SPIDER,
            EntityType.CAVE_SPIDER
    );

    @EventHandler(priority = EventPriority.LOWEST)
    public static void onOrcSpawnEggUse(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        if (event.getItem() == null) {
            return;
        }
        if (!validEggs.contains(event.getItem().getType())) {
            return;
        }
        Player player = event.getPlayer();
        PlayerObj playerObj = PlayerStorageUtil.findPlayer(player.getUniqueId());
        if (!playerObj.getRace().equalsIgnoreCase("Orc")) {
            return;
        }

        ItemStack spawnEgg = event.getItem();
        latestSpawnerUUID = player.getUniqueId();
        lastSpawnEgg = spawnEgg;
        autoTarget = !player.isSneaking();

        ItemMeta meta = spawnEgg.getItemMeta();
        if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "helmet"), PersistentDataType.STRING)) {
            helmet = ItemManager.deserializeItemStack(meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "helmet"), PersistentDataType.STRING));
        }
        else {
            helmet = null;
        }
        if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "chestplate"), PersistentDataType.STRING)) {
            chestplate = ItemManager.deserializeItemStack(meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "chestplate"), PersistentDataType.STRING));
        }
        else {
            chestplate = null;
        }
        if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "leggings"), PersistentDataType.STRING)) {
            leggings = ItemManager.deserializeItemStack(meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "leggings"), PersistentDataType.STRING));
        }
        else {
            leggings = null;
        }
        if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "boots"), PersistentDataType.STRING)) {
            boots = ItemManager.deserializeItemStack(meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "boots"), PersistentDataType.STRING));
        }
        else {
            boots = null;
        }
        if (meta.getPersistentDataContainer().has(new NamespacedKey(PlugHatia.getPlugin(), "tool"), PersistentDataType.STRING)) {
            tool = ItemManager.deserializeItemStack(meta.getPersistentDataContainer().get(new NamespacedKey(PlugHatia.getPlugin(), "tool"), PersistentDataType.STRING));
        }
        else {
            tool = null;
        }
    }

    @EventHandler
    public static void orcSpawnAlly(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            return;
        }
        if (lastSpawnEgg == null) {
            return;
        }
        if (!validEntities.contains(event.getEntityType())) {
            return;
        }
        UUID uuid = event.getEntity().getUniqueId();
        mobs.put(uuid, new HashSet<>());
        alliesAutoTarget.put(uuid, autoTarget);
        if (!masters.containsKey(latestSpawnerUUID)) {
            masters.put(latestSpawnerUUID, new HashSet<>());
        }
        masters.get(latestSpawnerUUID).add(uuid);

        if (helmet != null) {
            event.getEntity().getEquipment().setHelmet(helmet);
        }
        if (chestplate != null) {
            event.getEntity().getEquipment().setChestplate(chestplate);
        }
        if (leggings != null) {
            event.getEntity().getEquipment().setLeggings(leggings);
        }
        if (boots != null) {
            event.getEntity().getEquipment().setBoots(boots);
        }
        if (tool != null) {
            event.getEntity().getEquipment().setItemInMainHand(tool);
        }
        EntityEquipment equipment = event.getEntity().getEquipment();
        equipment.setHelmetDropChance(orcDropChance);
        equipment.setChestplateDropChance(orcDropChance);
        equipment.setLeggingsDropChance(orcDropChance);
        equipment.setBootsDropChance(orcDropChance);
        equipment.setItemInMainHandDropChance(orcDropChance);
        equipment.setItemInOffHandDropChance(orcDropChance);
        lastSpawnEgg = null;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public static void onMobTargetEntity(EntityTargetEvent event) {
        UUID entityUUID = event.getEntity().getUniqueId();
        Entity target = event.getTarget();

        if (target == null) {
            return;
        }
        allyTarget(event);
    }

    private static void allyTarget(EntityTargetEvent event) {
        UUID entityUUID = event.getEntity().getUniqueId();
        Entity target = event.getTarget();
        UUID targetUUID = target.getUniqueId();

        // if this is already a target
        if (mobs.containsKey(entityUUID)) {
            if (mobs.get(entityUUID).contains(targetUUID)) {
                return;
            }
        }

        // for following masters
        if (masters.containsKey(targetUUID)) {
            if (masters.get(targetUUID).contains(entityUUID)) {
                if (event.getEntity().getType() == EntityType.SKELETON || event.getEntity().getType() == EntityType.STRAY || event.getEntity().getLocation().distance(target.getLocation()) < allyFollowDistance || alliesAutoTarget.get(entityUUID)) {
                    event.setCancelled(true);
                }
                return;
            }
        }

        // regular Orc neutrality
        if (target.getType() == EntityType.PLAYER) {
            if (validEntities.contains(event.getEntity().getType())) {
                if (PlayerStorageUtil.findPlayer(target.getUniqueId()).getRace().equalsIgnoreCase("Orc")) {
                    if (!mobs.containsKey(entityUUID)) {
                        event.setCancelled(true);
                        return;
                    } else if (!mobs.get(entityUUID).contains(target.getUniqueId())) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }

        // if there is no reason not to target the target, and is auto target
        if (alliesAutoTarget.containsKey(entityUUID)) {
            if (!alliesAutoTarget.get(entityUUID)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void onAttackEntity(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity) || !(attacker instanceof LivingEntity)) {
            return;
        }

        if (attacker.getType() == EntityType.PLAYER) {
            Player player = (Player) attacker;
            if (masters.containsKey(attacker.getUniqueId())) {
                for (UUID ally : masters.get(player.getUniqueId())) {
                    mobs.get(ally).add(event.getEntity().getUniqueId());
                    World world = player.getWorld();
                    for (Entity livingEntity : world.getLivingEntities()) {
                        if (livingEntity.getUniqueId().equals(ally) && livingEntity instanceof Monster && livingEntity != entity) {
                            ((Monster) livingEntity).setTarget((LivingEntity) entity);
                        }
                    }
                }
            }

            // increasing mob drops for Orcs
            if (entity instanceof Monster && PlayerStorageUtil.findPlayer(player.getUniqueId()).getRace().equalsIgnoreCase("Orc")) {
                EntityEquipment equipment = ((Monster) entity).getEquipment();
                equipment.setHelmetDropChance(orcDropChance);
                equipment.setChestplateDropChance(orcDropChance);
                equipment.setLeggingsDropChance(orcDropChance);
                equipment.setBootsDropChance(orcDropChance);
                equipment.setItemInMainHandDropChance(orcDropChance);
                equipment.setItemInOffHandDropChance(orcDropChance);
            }
        }

        if (validEntities.contains(entity.getType())) {
            UUID attackerUUID = attacker.getUniqueId();
            if (mobs.containsKey(entity.getUniqueId())) {
                Set<UUID> newTargets = mobs.get(entity.getUniqueId());
                if (!newTargets.contains(attackerUUID)) {
                    newTargets.add(attackerUUID);
                }
                mobs.put(entity.getUniqueId(), newTargets);
            } else {
                mobs.put(entity.getUniqueId(), new HashSet<>(Arrays.asList(attackerUUID)));
            }
        }
        if (mobs.containsKey(attacker.getUniqueId())) {
            if (!mobs.get(attacker.getUniqueId()).contains(entity.getUniqueId())) {
                if (masters.containsKey(entity.getUniqueId())) {
                    if (masters.get(entity.getUniqueId()).contains(attacker.getUniqueId())) {
                        event.setCancelled(true);
                        ((Mob) attacker).setTarget(null);
                    }
                }
            }
        }
    }
}
