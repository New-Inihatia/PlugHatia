package newinihatia.plughatia.items;

import newinihatia.plughatia.PlugHatia;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IronHammer {
    public static ItemStack createIronHammer(int amount) {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, amount);
        Damageable meta = (Damageable) item.getItemMeta();
        meta.setDisplayName("Iron Hammer");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setMaxDamage(200);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", -3.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        meta.setCustomModelData(2);
        item.setItemMeta(meta);

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("iron_hammer_l"), item);
        sr.shape(
                "NI ",
                " S ",
                " S "
        );
        sr.setIngredient('N', Material.IRON_NUGGET);
        sr.setIngredient('I', Material.IRON_INGOT);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);

        ShapedRecipe sr2 = new ShapedRecipe(NamespacedKey.minecraft("iron_hammer_r"), item);
        sr2.shape(
                " IN",
                " S ",
                " S "
        );
        sr2.setIngredient('N', Material.IRON_NUGGET);
        sr2.setIngredient('I', Material.IRON_INGOT);
        sr2.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr2);

        return item;
    }
}
