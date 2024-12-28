package newinihatia.plughatia.objects;

import newinihatia.plughatia.menus.anvils.AnvilMenu;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AnvilObj {
    private int[] location = new int[4];
    private String anvilType;
    private Map<Integer, String> items = new HashMap<>();

    public AnvilObj(Block block, AnvilMenu anvilMenu) {
        location[0] = block.getX();
        location[1] = block.getY();
        location[2] = block.getZ();
        location[3] = Bukkit.getServer().getWorlds().indexOf(block.getWorld());
        anvilType = anvilMenu.getAnvilTypeString();
        for (Map.Entry<Integer, ItemStack> entry : anvilMenu.getItems().entrySet()) {
            ItemStack item = entry.getValue();
            if (item != null) {
                try {
                    ByteArrayOutputStream io = new ByteArrayOutputStream();
                    BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

                    os.writeObject(item);
                    os.flush();

                    byte[] serializedObject = io.toByteArray();

                    String encodedObject = Base64.getEncoder().encodeToString(serializedObject);
                    items.put(entry.getKey(), encodedObject);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public String getAnvilType() {
        return anvilType;
    }

    public void setAnvilType(String anvilType) {
        this.anvilType = anvilType;
    }

    public Map<Integer, String> getItems() {
        return items;
    }

    public void setItems(Map<Integer, String> items) {
        this.items = items;
    }
}
