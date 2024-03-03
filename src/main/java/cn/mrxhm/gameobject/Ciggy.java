package cn.mrxhm.gameobject;

import cn.mrxhm.ChenBRoulette;
import cn.mrxhm.utils.GameUtils;

import java.util.Random;

public class Ciggy implements Item{
    public int id;
    public Inventory parent;

    public Ciggy(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    @Override
    public String getName() {
        return "item.ciggy";
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
        int v =(new Random()).nextInt(15,26);
        ChenBRoulette.player_s.heal(v);
        Printer.p("text.show.heal"+ ChenBRoulette.player_s.health+"/"+ GameUtils.max_health);
        getParent().removeItem(id);
    }
}
