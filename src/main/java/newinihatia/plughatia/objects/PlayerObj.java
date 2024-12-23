package newinihatia.plughatia.objects;

import java.util.UUID;

public class PlayerObj {

    UUID uuid;
    String name;
    String username;
    String race;
    String CLASS;

    public PlayerObj(UUID uuid, String name, String username, String race, String CLASS) {
        this.name = name;
        this.uuid = uuid;
        this.username = username;
        this.race = race;
        this.CLASS = CLASS;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }
}