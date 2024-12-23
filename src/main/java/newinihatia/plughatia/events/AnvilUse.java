package newinihatia.plughatia.events;

import newinihatia.plughatia.items.ItemManager;
import newinihatia.plughatia.menus.anvils.IronAnvilMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class AnvilUse implements Listener {
    @EventHandler
    public void onAnvilUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Block block = event.getClickedBlock();
        if (block.getType() != Material.ANVIL) {
            return;
        }
        List<ItemStack> hammers = Arrays.asList(ItemManager.ironHammer);
        boolean hasHammer = false;
        for (ItemStack hammer : hammers) {
            if (ItemManager.areEquivalent(p.getInventory().getItemInMainHand(), hammer)) {
                hasHammer = true;
            }
        }
        if (!hasHammer) {
            return;
        }
        new IronAnvilMenu().displayTo(p);
        event.setCancelled(true);
    }
}
