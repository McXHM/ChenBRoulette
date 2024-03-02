package cn.mrxhm.gameobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ammos {
    public boolean true_or_false;

    public Ammos() {
        Random r = new Random();
        this.true_or_false = (r.nextFloat() < r.nextFloat());
    }

    public static boolean current() {
        return ammoList.get(ammoList.size() - 1).true_or_false;
    }

    public static List<Ammos> ammoList;

    public static void init() {
        ammoList = new ArrayList<>();
    }

    public static void addAmmo() {
        ammoList.add(new Ammos());
    }

    public static void addAmmo(int amount) {
        ammoList.clear();
        for (int i = 0; i < amount; i++) {
            addAmmo();
        }
    }

    public static boolean removeAmmo() {
        Ammos a = ammoList.get(ammoList.size() - 1);
        boolean tof = a.true_or_false;
        ammoList.remove(a);
        return tof;
    }

    public static void listAmmo() {
        for (Ammos ammo : ammoList) {
            Printer.p(ammo.toString());
        }
    }

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

    @Override
    public String toString() {
        return "ammo." + true_or_false;
    }
}
