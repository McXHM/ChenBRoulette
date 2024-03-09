package cn.mrxhm.gameobject;

import cn.mrxhm.ChenBRoulette;

/**
 * 物品的子类：手铐
 */
public class Handcuffs implements Item {
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
    public Handcuffs(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    /**
     * 获取名字的方法
     *
     * @return 名称
     */
    @Override
    public String getName() {
        return "item.handcuffs";
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
        ChenBRoulette.player_t.cd = 1;
        Printer.p("text.show.lock" + ChenBRoulette.player_t);
        getParent().removeItem(id);
    }
}
