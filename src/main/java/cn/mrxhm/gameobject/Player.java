package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

public class Player implements GameObject {
    public String name;
    public double health;
    public Inventory inv;
    public int id;
    public int cd = 0;

    public Player(String name) {
        this.name = name;
        this.id = GameUtils.players.size() + 1;
        this.health = 100.0;
        this.inv = new Inventory();
    }

    public Item getItem(int id) {
        for (Item i : inv.items) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public void heal(double value) {
        this.health = Math.max(this.health + value, GameUtils.max_health);
    }

    public void damage(double value) {
        this.health = Math.max(this.health - value, 0);
    }

    @Override
    public String toString() {
        return id + ".\t" + name + "\tHP:" + health + "/" + GameUtils.max_health;
    }
}
