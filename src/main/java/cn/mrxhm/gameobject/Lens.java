package cn.mrxhm.gameobject;

public class Lens implements Item {
    public int id;
    public Inventory parent;

    public Lens(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    @Override
    public String getName() {
        return "item.lens";
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
        Printer.p("text.show.check_ammo" + "ammo." + Ammos.ammoList.get(Ammos.ammoList.size() - 1).true_or_false);
        getParent().removeItem(id);
    }
}
