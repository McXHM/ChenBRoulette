package cn.mrxhm.gameobject;

import cn.mrxhm.ChenBRoulette;

public class Handcuffs implements Item {
    public int id;
    public Inventory parent;

    public Handcuffs(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    @Override
    public String getName() {
        return "item.handcuffs";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Inventory getParent() {
        return this.parent;
    }

    @Override
    public String toString() {
        return getId() + ".\t" + getName();
    }

    @Override
    public void use() {
        ChenBRoulette.player_t.cd += 1;
        Printer.p("text.show.lock" + ChenBRoulette.player_t);
        getParent().removeItem(id);
    }
}
