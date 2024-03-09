package cn.mrxhm.gameobject;

/**
 * 一个基本的物品接口
 */
public interface Item {
    /**
     * 获取名字的方法
     *
     * @return 名字（本地化前）
     */
    String getName();

    /**
     * 获取ID的方法
     *
     * @return ID
     */
    int getId();

    /**
     * 设置ID的方法
     *
     * @param id ID
     */
    void setId(int id);

    /**
     * 获取装载这个物品的背包的方法
     *
     * @return 背包
     */
    Inventory getParent();

    /**
     * 获取使用它的方法
     */
    void use();
}
