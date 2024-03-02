package cn.mrxhm.gameobject;

public class Beer implements Item {
    public int id;
    public Inventory parent;

    public Beer(Inventory inv) {
        this.id = inv.items.size() + 1;
        this.parent = inv;
    }

    @Override
    public String getName() {
        return "item.beer";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Inventory getParent() {
        return parent;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getId() + ".\t" + getName();
    }

    @Override
    public void use() {
        Printer.p("text.show.recoil" + "ammo." + Ammos.removeAmmo());
        getParent().removeItem(id);
    }
}
