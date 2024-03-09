package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个基本的背包类
 */
public class Inventory implements GameObject {
    /**
     * 物品列表
     */
    public List<Item> items;

    /**
     * 基本构造器，初始化物品列表
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * 添加物品的方法
     *
     * @param item 物品
     * @return 是否添加成功
     */
    public boolean addItem(Item item) {
        if (items.size() < 2.718281828 * GameUtils.max_players) {
            items.add(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 移除物品的方法
     *
     * @param id ID
     * @return 是否移除成功
     */
    public boolean removeItem(int id) {
        for (Item i : items) {
            if (i.getId() == id) {
                items.remove(i);
                sortItems();
                return true;
            }
        }
        return false;
    }

    /**
     * 排序物品的方法
     */
    public void sortItems() {
        int i = 1;
        for (Item i2 : items) {
            i2.setId(i);
            i++;
        }
    }

    /**
     * 列表物品的方法
     */
    public void listItems() {
        for (Item i : items) {
            Printer.p(i.toString());
        }
    }

    /**
     * 获取玩家的方法
     *
     * @return 这个背包的所属者
     */
    public Player getPlayer() {
        for (Player p : GameUtils.players) {
            if (p.inv == this) {
                return p;
            }
        }
        return null;
    }

    /**
     * 转为字符串的方法
     *
     * @return 一坨史，我不会去用
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item i : items) {
            sb.append(i.toString());
        }
        return sb.toString();
    }
}
