package cn.mrxhm.gameobject;

/**
 * 物品的子类：啤酒
 */
public class Beer implements Item {
    /**
     * ID
     */
    public int id;
    /**
     * 背包
     */
    public Inventory parent;

    /**
     * 构造方法
     *
     * @param inv 装载它的背包
     */
    public Beer(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    /**
     * 获得名称的方法
     *
     * @return 名称
     */
    @Override
    public String getName() {
        return "item.beer";
    }

    /**
     * 获得ID的方法
     *
     * @return ID
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * 获得背包的方法
     *
     * @return 背包
     */
    @Override
    public Inventory getParent() {
        return parent;
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
        Printer.p("text.show.recoil" + "ammo." + Ammos.removeAmmo());
        getParent().removeItem(id);
    }
}
