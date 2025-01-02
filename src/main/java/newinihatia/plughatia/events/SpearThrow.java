package newinihatia.plughatia.events;

import newinihatia.plughatia.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class SpearThrow implements Listener {
    private int spearThrowMinDamage = 15;
    private int spearThrowMaxDamage = 30;


//    @EventHandler
//    public static void onSpearThrow(ProjectileHitEvent event) {
////        ItemStack item = event.getItem().getItemStack();
//        if (event.getEntity().getType() != EntityType.TRIDENT) {
//            return;
//        }
////        Damageable meta = (Damageable) item.getItemMeta();
////        if (ItemManager.areEquivalent(item, ItemManager.ironSpear)) {
////            meta.setHealth();
////        }
//
//        Trident trident = (Trident) event.getEntity();
////        Damageable item = (Damageable) trident;
////        Damageable meta = (Damageable) item.getItemMeta();
////        ItemMeta meta = item.getItemMeta();
////        meta.setHealth(meta.getHealth() - 25);
////        trident.getItem().setItemMeta((ItemMeta) meta);
////        item.setHealth(item.getHealth() - 25);
//        ((Player) event.getEntity().getShooter()).sendMessage("You have thrown a spear!");
//        trident.setMetadata();
//    }

//    @EventHandler
//    public static void onSpearPickup(PlayerPickupArrowEvent event) {
//        AbstractArrow arrow =  event.getArrow();
//        if (arrow.getType() != EntityType.TRIDENT) {
//            System.out.println(event.getItem().getItemStack().getType());
//            System.out.println(event.getItem().getType());
//            return;
//        }
//        Trident trident = (Trident) arrow;
//        ItemStack item = trident.getItem();
//        Damageable meta = (Damageable) item.getItemMeta();
////        if (ItemManager.areEquivalent(item, ItemManager.ironSpear)) {
//            meta.setDamage(meta.getDamage() + 25);
////        }
//        item.setItemMeta(meta);
////        trident.setItem(item);
//        Player payer = event.getPlayer();
//        event.getPlayer().sendMessage("You have picked up a spear!");
//        System.out.println("puckied up");
//    }
}
