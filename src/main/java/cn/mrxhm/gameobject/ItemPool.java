package cn.mrxhm.gameobject;

import java.util.Random;

public class ItemPool {
    public static void lootItem(Inventory inv) {
        Random r = new Random();
        switch (r.nextInt(5)) {
            case 0 -> inv.addItem(new HandSaw(inv));
            case 1 -> inv.addItem(new Beer(inv));
            case 2 -> inv.addItem(new Lens(inv));
            case 3 -> inv.addItem(new Ciggy(inv));
            case 4 -> inv.addItem(new Handcuffs(inv));
            default -> {
            }
        }
    }

    public static void lootItem(Player player) {
        lootItem(player.inv);
    }

    public static void lootItem(Inventory inv, int amount) {
        for (int i = 0; i < amount; i++) {
            lootItem(inv);
        }
    }

    public static void lootItem(Player player, int amount) {
        lootItem(player.inv, amount);
    }
}
