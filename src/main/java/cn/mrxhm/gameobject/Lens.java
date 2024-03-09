package cn.mrxhm.gameobject;

/**
 * 物品的子类：放大镜
 */
public class Lens implements Item {
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
    public Lens(Inventory inv) {
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
        return "item.lens";
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
        Printer.p("text.show.check_ammo" + "ammo." + Ammos.ammoList.get(Ammos.ammoList.size() - 1).true_or_false);
        getParent().removeItem(id);
    }
}
