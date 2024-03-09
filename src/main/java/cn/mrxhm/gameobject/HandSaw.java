package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

/**
 * 物品的子类：手锯
 */
public class HandSaw implements Item {
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
    public HandSaw(Inventory inv) {
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
        return "item.handsaw";
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
        return parent;
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
        GameUtils.randomDamageMulti();
        Printer.p("text.show.damage_multi" + GameUtils.damage_multi);
        getParent().removeItem(id);
    }
}
