package newinihatia.plughatia.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BannedItems implements Listener {

    public static List<ItemStack> banned = new ArrayList<>();

    public static void init() {
        makeBanned();
    }

    public static void makeBanned() {
        banned.add(new ItemStack(Material.DIAMOND_HELMET));
        banned.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
        banned.add(new ItemStack(Material.DIAMOND_LEGGINGS));
        banned.add(new ItemStack(Material.DIAMOND_BOOTS));

        banned.add(new ItemStack(Material.DIAMOND_SWORD));
        banned.add(new ItemStack(Material.DIAMOND_AXE));
        banned.add(new ItemStack(Material.DIAMOND_PICKAXE));
        banned.add(new ItemStack(Material.DIAMOND_SHOVEL));
        banned.add(new ItemStack(Material.DIAMOND_HOE));


        banned.add(new ItemStack(Material.NETHERITE_HELMET));
        banned.add(new ItemStack(Material.NETHERITE_CHESTPLATE));
        banned.add(new ItemStack(Material.NETHERITE_LEGGINGS));
        banned.add(new ItemStack(Material.NETHERITE_BOOTS));

        banned.add(new ItemStack(Material.NETHERITE_SWORD));
        banned.add(new ItemStack(Material.NETHERITE_AXE));
        banned.add(new ItemStack(Material.NETHERITE_PICKAXE));
        banned.add(new ItemStack(Material.NETHERITE_SHOVEL));
        banned.add(new ItemStack(Material.NETHERITE_HOE));
    }

    @EventHandler
    public static void onClickBannedItem(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem() == null) {
            return;
        }

        for (ItemStack item : banned) {
            if (e.getCurrentItem().getType().equals(item.getType()) && !e.getCurrentItem().getItemMeta().hasCustomModelData()) {
                p.getInventory().removeItem(e.getCurrentItem());
                p.closeInventory();
                p.sendMessage(ChatColor.DARK_RED + "That item is banned! None of that on Middle Earth. . .");
                e.setCancelled(true);
            }
        }

    }

}
