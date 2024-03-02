package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

public class HandSaw implements Item {
    public int id;
    public Inventory parent;

    public HandSaw(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    @Override
    public String getName() {
        return "item.handsaw";
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
        return parent;
    }

    @Override
    public String toString() {
        return getId() + ".\t" + getName();
    }

    @Override
    public void use() {
        GameUtils.randomDamageMulti();
        Printer.p("text.show.damage_multi" + GameUtils.damage_multi);
        getParent().removeItem(id);
    }
}
