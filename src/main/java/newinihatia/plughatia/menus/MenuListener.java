package newinihatia.plughatia.menus;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        if (inventory == player.getInventory()) {
            return;
        }
        int slot = event.getSlot();
        if (player.hasMetadata("NewInihatiaMenu")) {
            Menu menu = (Menu) player.getMetadata("NewInihatiaMenu").get(0).value();

            for (Map.Entry<Integer, Button> entry : menu.getButtons().entrySet()) {
                Button button = entry.getValue();
                if (button.getSlot() == slot) {
                    button.onClick(player);
                    if (!button.takeable) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasMetadata("NewInihatiaMenu")) {
            // Return items to player
//            Inventory inventory = event.getInventory();
//            for (int i = 0; i < inventory.getSize(); i++) {
//                if (inventory.getItem(i) != null) {
//                    if (((Menu) player.getMetadata("NewInihatiaMenu").get(0).value()).getButton(i) == null) {
//                        player.getInventory().addItem(inventory.getItem(i));
//                    }
//                }
//            }
            player.removeMetadata("NewInihatiaMenu", PlugHatia.getPlugin());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("NewInihatiaMenu")) {
            // Return items to player
//            Inventory inventory = player.getOpenInventory().getTopInventory();
//            for (int i = 0; i < inventory.getSize(); i++) {
//                if (inventory.getItem(i) != null) {
//                    if (((Menu) player.getMetadata("NewInihatiaMenu").get(0).value()).getButton(i) == null) {
//                        player.getInventory().addItem(inventory.getItem(i));
//                    }
//                }
//            }
            player.removeMetadata("NewInihatiaMenu", PlugHatia.getPlugin());
        }
    }
}
