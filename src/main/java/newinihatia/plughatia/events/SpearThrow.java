package newinihatia.plughatia.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SpearThrow implements Listener {
    @EventHandler
    public static void onSpearThrow(ProjectileHitEvent event) {
//        if (event.getEntity().getType() != EntityType.TRIDENT) {
//            return;
//        }
//        Trident trident = (Trident) event.getEntity();
//        ItemStack item = trident.getItem();
////        Damageable meta = (Damageable) item.getItemMeta();
//        ItemMeta meta = item.getItemMeta();
////        meta.setHealth(meta.getHealth() - 25);
//        meta.
//        trident.getItem().setItemMeta((ItemMeta) meta);
//        ((Player) event.getEntity().getShooter()).sendMessage("You have thrown a spear!");
    }
}
