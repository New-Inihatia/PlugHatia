package newinihatia.plughatia.events.effects;

import newinihatia.plughatia.PlugHatia;
import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.utils.PlayerStorageUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class OrcCraftSpawnEggs implements Listener {

    private static final List<Material> helmets = List.of(Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET);
    private static final List<Material> chestplates = List.of(Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE);
    private static final List<Material> leggings = List.of(Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS);
    private static final List<Material> boots = List.of(Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS);
    private static final List<Material> tools = List.of(
            Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
            Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE,
            Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE,
            Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL,
            Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE,
            Material.BOW
    );

    private static final ItemStack rottenFlesh = new ItemStack(Material.ROTTEN_FLESH);
    private static final ItemStack bone = new ItemStack(Material.BONE);
    private static final ItemStack arrow = new ItemStack(Material.ARROW);
    private static final ItemStack gunpowder = new ItemStack(Material.GUNPOWDER);
    private static final ItemStack string = new ItemStack(Material.STRING);
    private static final ItemStack spiderEye = new ItemStack(Material.SPIDER_EYE);

    private static final List<Material> validEggs = List.of(
            Material.ZOMBIE_SPAWN_EGG,
            Material.HUSK_SPAWN_EGG,
            Material.SKELETON_SPAWN_EGG,
            Material.STRAY_SPAWN_EGG,
            Material.CREEPER_SPAWN_EGG,
            Material.SPIDER_SPAWN_EGG,
            Material.CAVE_SPIDER_SPAWN_EGG
    );

    @EventHandler
    public static void onPrepareCraftSpawnEgg(PrepareItemCraftEvent event) {
        boolean hasOrcViewer = false;
        for (HumanEntity viewer : event.getViewers()) {
            Player player = (Player) viewer;
            if (PlayerStorageUtil.findPlayer(player.getUniqueId()).getRace().equalsIgnoreCase("Orc")) {
                hasOrcViewer = true;
            }
        }
        if (!hasOrcViewer) {
            return;
        }

        CraftingInventory inv = event.getInventory();
        if (inv.getResult() != null) {
            return;
        }
        ItemStack[] matrix = inv.getMatrix();

        // Essential materials
        int rottenFleshCount = 0;
        int boneCount = 0;
        int arrowCount = 0;
        int gunpowderCount = 0;
        int stringCount = 0;
        int spiderEyeCount = 0;


        // Equipment
        ItemStack helmet = null;
        ItemStack chestplate = null;
        ItemStack leggingsItem = null;
        ItemStack bootsItem = null;
        ItemStack tool = null;

        ItemStack spawnEgg = null;

        for (ItemStack item : matrix) {
            if (item != null) {
                if (validEggs.contains(item.getType())) {
                    spawnEgg = item;
                } else if (item.isSimilar(rottenFlesh)) {
                    rottenFleshCount++;
                } else if (item.isSimilar(bone)) {
                    boneCount++;
                } else if (item.isSimilar(arrow)) {
                    arrowCount++;
                } else if (item.isSimilar(gunpowder)) {
                    gunpowderCount++;
                } else if (item.isSimilar(string)) {
                    stringCount++;
                } else if (item.isSimilar(spiderEye)) {
                    spiderEyeCount++;
                } else if (helmets.contains(item.getType())) {
                    helmet = item;
                } else if (chestplates.contains(item.getType())) {
                    chestplate = item;
                } else if (leggings.contains(item.getType())) {
                    leggingsItem = item;
                } else if (boots.contains(item.getType())) {
                    bootsItem = item;
                } else if (tools.contains(item.getType())) {
                    tool = item;
                }
            }
        }

        if (spawnEgg == null) {
            if (rottenFleshCount == 4) {
                spawnEgg = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
            } else if (boneCount == 3 && arrowCount == 1) {
                spawnEgg = new ItemStack(Material.SKELETON_SPAWN_EGG);
            } else if (gunpowderCount == 4) {
                spawnEgg = new ItemStack(Material.CREEPER_SPAWN_EGG);
            } else if (stringCount == 3 && spiderEyeCount == 1) {
                spawnEgg = new ItemStack(Material.SPIDER_SPAWN_EGG);
            } else {
                return;
            }
        }

        ItemMeta meta = spawnEgg.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (helmet != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "helmet"), PersistentDataType.STRING, ItemManager.serializeItemStack(helmet));
            lore.add(helmet.getType().toString());
        }
        if (chestplate != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "chestplate"), PersistentDataType.STRING, ItemManager.serializeItemStack(chestplate));
            lore.add(chestplate.getType().toString());
        }
        if (leggingsItem != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "leggings"), PersistentDataType.STRING, ItemManager.serializeItemStack(leggingsItem));
            lore.add(leggingsItem.getType().toString());
        }
        if (bootsItem != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "boots"), PersistentDataType.STRING, ItemManager.serializeItemStack(bootsItem));
            lore.add(bootsItem.getType().toString());
        }
        if (tool != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(PlugHatia.getPlugin(), "tool"), PersistentDataType.STRING, ItemManager.serializeItemStack(tool));
            lore.add(tool.getType().toString());
        }
        meta.setLore(lore);
        spawnEgg.setItemMeta(meta);
        inv.setResult(spawnEgg);
    }
}
