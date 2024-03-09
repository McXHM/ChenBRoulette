package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

/**
 * 一个基本的玩家类
 */
public class Player implements GameObject {
    /**
     * 名称
     */
    public String name;
    /**
     * 血量
     */
    public double health;
    /**
     * 背包
     */
    public Inventory inv;
    /**
     * ID
     */
    public int id;
    /**
     * 冷却时间（被手铐锁住的回合）
     */
    public int cd = 0;

    /**
     * 构造器
     *
     * @param name 玩家名称
     */
    public Player(String name) {
        this.name = name;
        this.id = GameUtils.players.size() + 1;
        this.health = GameUtils.max_health;
        this.inv = new Inventory();
    }

    /**
     * 根据ID获取物品
     *
     * @param id ID
     * @return 物品
     */
    public Item getItem(int id) {
        for (Item i : inv.items) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * 治疗玩家的方法
     *
     * @param value 值
     */
    public void heal(double value) {
        this.health = Math.min(this.health + value, GameUtils.max_health);
    }

    /**
     * 伤害玩家的方法
     *
     * @param value 值
     */
    public void damage(double value) {
        this.health = Math.max(this.health - value, 0);
    }

    /**
     * 转为字符串的方法
     *
     * @return ID+名称+血量
     */
    @Override
    public String toString() {
        return id + ".\t" + name + "\tHP:" + health + "/" + GameUtils.max_health;
    }
}
