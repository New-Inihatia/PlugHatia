package newinihatia.plughatia.menus.anvils;

import newinihatia.plughatia.items.AnvilRecipe;
import newinihatia.plughatia.items.AnvilRecipes;
import newinihatia.plughatia.menus.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class IronAnvilMenu extends AnvilMenu {

    @Override
    public void recipesFunction(Player player) {
        System.out.println("recipes:!!!!");
        new RecipesMenu().displayTo(player);
    }

    private class RecipesMenu extends AnvilMenu.RecipesMenu {
        public RecipesMenu() {
            super(IronAnvilMenu.this, AnvilRecipes.ironAnvilRecipes);
            init();
        }
    }
}
