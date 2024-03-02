package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (items.size() < 2.718281828 * GameUtils.max_players) {
            items.add(item);
            return true;
        } else {
            return false;
        }
    }

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

    public void sortItems() {
        int i = 1;
        for (Item i2 : items) {
            i2.setId(i);
            i++;
        }
    }

    public void listItems() {
        for (Item i : items) {
            Printer.p(i.toString());
        }
    }

    public Player getPlayer() {
        for (Player p : GameUtils.players) {
            if (p.inv == this) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item i : items) {
            sb.append(i.toString());
        }
        return sb.toString();
    }
}
