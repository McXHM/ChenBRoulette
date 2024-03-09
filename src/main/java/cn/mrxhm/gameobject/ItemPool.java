package cn.mrxhm.gameobject;

import java.util.Random;

/**
 * 物品池
 */
public class ItemPool {
    /**
     * 随机一个物品的方法
     *
     * @param inv 背包
     */
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

    /**
     * 为玩家随机物品的方法
     *
     * @param player 玩家
     */
    public static void lootItem(Player player) {
        lootItem(player.inv);
    }

    /**
     * 为背包批量随机物品的方法
     *
     * @param inv    背包
     * @param amount 数量
     */
    public static void lootItem(Inventory inv, int amount) {
        for (int i = 0; i < amount; i++) {
            lootItem(inv);
        }
    }

    /**
     * 为玩家批量随机物品的方法
     *
     * @param player 玩家
     * @param amount 数量
     */
    public static void lootItem(Player player, int amount) {
        lootItem(player.inv, amount);
    }
}
