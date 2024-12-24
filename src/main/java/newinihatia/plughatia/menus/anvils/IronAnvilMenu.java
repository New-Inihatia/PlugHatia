package newinihatia.plughatia.menus.anvils;

import newinihatia.plughatia.items.AnvilRecipes;
import org.bukkit.entity.Player;

public class IronAnvilMenu extends AnvilMenu {

    public IronAnvilMenu() {
        super(AnvilRecipes.ironAnvilWeldingRecipes);
        this.setTitle("Iron Anvil");
    }

    @Override
    public void recipesFunction(Player player) {
        new SmithingRecipesMenu().displayTo(player);
    }

    private class SmithingRecipesMenu extends AnvilMenu.SmithingRecipesMenu {
        public SmithingRecipesMenu() {
            super(IronAnvilMenu.this, AnvilRecipes.ironAnvilSmithingRecipes);
            init();
        }
    }
}
