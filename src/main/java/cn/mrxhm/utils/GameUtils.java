package cn.mrxhm.utils;

import cn.mrxhm.ChenBRoulette;
import cn.mrxhm.gameobject.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameUtils {
    public static int max_players;
    public static List<Player> players;
    public static String lang;
    public static File lang_file;
    public static HashMap<String, String> lang_map;
    public static final double max_health = 100.0;
    public static final double min_damage_multi = 0.5;
    public static final double max_damage_multi = 2.5;
    public static final double basic_damage = 10.0;
    public static final int max_ammo = 10;
    public static double damage_multi = 1.0;

    public static void randomDamageMulti() {
        damage_multi = Double.parseDouble((new DecimalFormat("0.00")).format((new Random()).nextDouble(min_damage_multi, max_damage_multi)));
    }

    public static int overHanding(int id) {
        int new_id = id;
        while (new_id > max_players) {
            new_id -= max_players;
        }
        return new_id;
    }

    public static void nextPlayer() {
        ChenBRoulette.player_s = players.get(overHanding(ChenBRoulette.player_s.id + 1) - 1);
        if (ChenBRoulette.player_s.cd > 0) {
            ChenBRoulette.player_s.cd--;
            nextPlayer();
        }
    }

    public static void init() throws IOException {
        max_players = 2;
        players = new ArrayList<>();
        lang = "zh_CN";
        initLangFile(lang);
        parseLangFile();
    }

    public static void init(int max_players_amount) throws IOException {
        max_players = max_players_amount;
        players = new ArrayList<>();
        lang = "zh_CN";
        initLangFile(lang);
        parseLangFile();
    }

    public static void init(String language) throws IOException {
        max_players = 2;
        players = new ArrayList<>();
        lang = language;
        initLangFile(lang);
        parseLangFile();
    }

    public static void init(int max_players_amount, String language) throws IOException {
        max_players = max_players_amount;
        players = new ArrayList<>();
        lang = language;
        initLangFile(lang);
        parseLangFile();
    }

    public static void initLangFile(String lang_name) throws IOException {
        File d = new File("lang");
        File f = new File(d + "\\" + lang_name + ".json");
        if (d.exists()) {
            if (f.exists()) {
                lang_file = f;
            } else {
                f.createNewFile();
            }
        } else {
            d.mkdirs();
            initLangFile(lang_name);
        }
    }

    public static void parseLangFile() throws IOException {
        FileInputStream fis = new FileInputStream(lang_file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        lang_map = JSON.parseObject(new String(bytes), HashMap.class);
        fis.close();
    }

    public static void listPlayers() {
        sortPlayers();
        for (Player p : players) {
            Printer.p(p.toString());
        }
    }

    public static void extractItem() {
        for (Player p : players) {
            ItemPool.lootItem(p, max_players * 2);
        }
    }

    public static void fillAmmo() {
        Ammos.addAmmo((new Random()).nextInt(2, max_ammo + 1));
        Ammos.listAmmoAmount();
    }

    public static boolean addPlayer(Player player) {
        if (players.size() < max_players) {
            players.add(player);
            return true;
        } else {
            return false;
        }
    }

    public static Player getPlayer(int id) {
        for (Player p : players) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

    public static void sortPlayers() {
        int i = 1;
        for (Player p : players) {
            p.id = i;
            i++;
        }
    }

    public static String ratioPlayers() {
        return "(" + players.size() + " / " + max_players + ")";
    }

    public static void printAllPlayerInv() {
        for (Player p : players) {
            Printer.p(p.name + ":");
            p.inv.listItems();
            System.out.println();
        }
    }
}
