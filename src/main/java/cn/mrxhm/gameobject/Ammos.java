package cn.mrxhm.gameobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 子弹类
 */
public class Ammos {
    /**
     * 这颗子弹的真假
     */
    public boolean true_or_false;

    /**
     * 构造方法，随机真假子弹
     */
    public Ammos() {
        Random r = new Random();
        this.true_or_false = (r.nextFloat() < r.nextFloat());
    }

    /**
     * 获取当前子弹真假
     *
     * @return 真假
     */
    public static boolean current() {
        return ammoList.get(ammoList.size() - 1).true_or_false;
    }

    /**
     * 子弹列表
     */
    public static List<Ammos> ammoList;

    /**
     * 初始化子弹列表
     */
    public static void init() {
        ammoList = new ArrayList<>();
    }

    /**
     * 添加子弹的方法
     */
    public static void addAmmo() {
        ammoList.add(new Ammos());
    }

    /**
     * 批量添加子弹的方法
     *
     * @param amount 子弹带数量
     */
    public static void addAmmo(int amount) {
        ammoList.clear();
        for (int i = 0; i < amount; i++) {
            addAmmo();
        }
    }

    /**
     * 移除子弹的方法
     *
     * @return 是否成功
     */
    public static boolean removeAmmo() {
        Ammos a = ammoList.get(ammoList.size() - 1);
        boolean tof = a.true_or_false;
        ammoList.remove(a);
        return tof;
    }

    /**
     * 列表子弹的方法
     */
    public static void listAmmo() {
        for (Ammos ammo : ammoList) {
            Printer.p(ammo.toString());
        }
    }

    /**
     * 列出子弹的数目的方法
     */
    public static void listAmmoAmount() {
        int true_amount = 0;
        int false_amount = 0;
        for (Ammos a : ammoList) {
            if (a.true_or_false) {
                true_amount += 1;
            } else {
                false_amount += 1;
            }
        }
        Printer.p(true_amount + " " + "ammo.true" + " , " + false_amount + " " + "ammo.false");
    }

    /**
     * 重写了子弹转为字符串的方法
     *
     * @return 子弹名（真假）
     */
    @Override
    public String toString() {
        return "ammo." + true_or_false;
    }
}
