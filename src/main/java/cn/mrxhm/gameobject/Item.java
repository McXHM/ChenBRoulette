package cn.mrxhm.gameobject;

public interface Item {
    public String getName();
    public int getId();
    public void setId(int id);
    public Inventory getParent();
    public void use();
}
