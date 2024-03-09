package cn.mrxhm.gameobject;

import cn.mrxhm.ChenBRoulette;
import cn.mrxhm.utils.GameUtils;

import java.util.Random;

/**
 * 物品的子类：香烟
 */
public class Ciggy implements Item {
    /**
     * ID
     */
    public int id;
    /**
     * 背包
     */
    public Inventory parent;

    /**
     * 构造器
     *
     * @param inv 装载它的背包
     */
    public Ciggy(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    /**
     * 获取名称的方法
     *
     * @return 名称
     */
    @Override
    public String getName() {
        return "item.ciggy";
    }

    /**
     * 获取ID的方法
     *
     * @return ID
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * 设置ID的方法
     *
     * @param id ID
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取背包的方法
     *
     * @return 背包
     */
    @Override
    public Inventory getParent() {
        return this.parent;
    }

    /**
     * 转为字符串的方法
     *
     * @return 序号+名称
     */
    @Override
    public String toString() {
        return getId() + ".\t" + getName();
    }

    /**
     * 使用的方法
     */
    @Override
    public void use() {
        int v = (new Random()).nextInt(15, 26);
        ChenBRoulette.player_s.heal(v);
        Printer.p("text.show.heal" + ChenBRoulette.player_s.health + "/" + GameUtils.max_health);
        getParent().removeItem(id);
    }
}
