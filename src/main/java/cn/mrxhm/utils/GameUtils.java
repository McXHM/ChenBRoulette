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

/**
 * 游戏的基本工具，游戏的大脑
 */
public class GameUtils {
    /**
     * 最大玩家数，默认2人
     */
    public static int max_players = 2;
    /**
     * 玩家列表
     */
    public static List<Player> players;
    /**
     * 本地化语言，默认为en_US
     */
    public static String lang = "en_US";
    /**
     * 本地化文件
     */
    public static File lang_file;
    /**
     * 本地化映射
     */
    public static HashMap<String, String> lang_map;
    /**
     * 最大血量，默认为100
     */
    public static double max_health = 100.0;
    /**
     * 手锯的最小伤害倍数，默认为0.5
     */
    public static double min_damage_multi = 0.5;
    /**
     * 手锯的最大上海倍数，默认为2.5
     */
    public static double max_damage_multi = 2.5;
    /**
     * 枪的基础伤害，默认为10.0
     */
    public static double basic_damage = 10.0;
    /**
     * 子弹的最大数量，默认为10
     */
    public static int max_ammo = 10;
    /**
     * 枪的伤害倍数，默认为1.0
     */
    public static double damage_multi = 1.0;

    /**
     * 随机伤害倍数
     */
    public static void randomDamageMulti() {
        damage_multi = Double.parseDouble((new DecimalFormat("0.00")).format((new Random()).nextDouble(min_damage_multi, max_damage_multi)));
    }

    /**
     * 对ID做溢出处理
     *
     * @param id 当前ID
     * @return 新ID
     */
    public static int overHanding(int id) {
        int new_id = id;
        while (new_id > max_players) {
            new_id -= max_players;
        }
        return new_id;
    }

    /**
     * 让下一位玩家操作的方法
     */
    public static void nextPlayer() {
        ChenBRoulette.player_s = players.get(overHanding(ChenBRoulette.player_s.id + 1) - 1);
        if (ChenBRoulette.player_s.cd > 0) {
            ChenBRoulette.player_s.cd--;
            nextPlayer();
        }
    }

    /**
     * 初始化
     *
     * @throws IOException 抛出异常
     */
    public static void init() throws IOException {
        players = new ArrayList<>();
        if (lang == null) {
            lang = "zh_CN";
        }
        initLangFile(lang);
        parseLangFile();
    }

    /**
     * 初始化
     *
     * @param max_players_amount 最大玩家数量
     * @throws IOException 抛出异常
     */
    public static void init(int max_players_amount) throws IOException {
        max_players = max_players_amount;
        players = new ArrayList<>();
        lang = "zh_CN";
        initLangFile(lang);
        parseLangFile();
    }

    /**
     * 初始化
     *
     * @param language 本地化语言
     * @throws IOException 抛出异常
     */
    public static void init(String language) throws IOException {
        players = new ArrayList<>();
        lang = language;
        initLangFile(lang);
        parseLangFile();
    }

    /**
     * 初始化
     *
     * @param max_players_amount 最大玩家数量
     * @param language           本地化语言
     * @throws IOException 抛出异常
     */
    public static void init(int max_players_amount, String language) throws IOException {
        max_players = max_players_amount;
        players = new ArrayList<>();
        lang = language;
        initLangFile(lang);
        parseLangFile();
    }

    /**
     * 初始化本地化文件
     *
     * @param lang_name 本地化文件名
     * @throws IOException 抛出异常
     */
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

    /**
     * 解析本地化文件
     *
     * @throws IOException 抛出异常
     */
    public static void parseLangFile() throws IOException {
        FileInputStream fis = new FileInputStream(lang_file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        lang_map = JSON.parseObject(new String(bytes), HashMap.class);
        fis.close();
    }

    /**
     * 列表玩家列表
     */
    public static void listPlayers() {
        sortPlayers();
        for (Player p : players) {
            Printer.p(p.toString());
        }
    }

    /**
     * 插入物品的方法
     */
    public static void extractItem() {
        for (Player p : players) {
            ItemPool.lootItem(p, max_players + 1);
        }
    }

    /**
     * 填充子弹的方法
     */
    public static void fillAmmo() {
        Ammos.addAmmo((new Random()).nextInt(2, max_ammo + 1));
        Ammos.listAmmoAmount();
    }

    /**
     * 新的回合的方法
     *
     * @throws InterruptedException 抛出异常
     */
    public static void newTurn() throws InterruptedException {
        Thread.sleep(1000);
        Printer.p("\f\rtext.random_ammo\n", false);
        fillAmmo();
        Thread.sleep(1000);
        Printer.p("\rtext.random_item");
        extractItem();
        Thread.sleep(1000);
        printAllPlayerInv();
        ChenBRoulette.player_t = null;
        damage_multi = 1.0;
        ChenBRoulette.operation = Operation.PLAYER_CHOICE;
        Printer.p("text.player_of_operation" + ChenBRoulette.player_s);
        Printer.p("text.please_try");
        ChenBRoulette.player_s.inv.listItems();
    }

    /**
     * 添加玩家的方法
     *
     * @param player 玩家
     * @return 是否成功
     */
    public static boolean addPlayer(Player player) {
        if (players.size() < max_players) {
            players.add(player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据ID获取玩家的方法
     *
     * @param id ID
     * @return 玩家
     */
    public static Player getPlayer(int id) {
        for (Player p : players) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * 排序玩家的方法
     */
    public static void sortPlayers() {
        int i = 1;
        for (Player p : players) {
            p.id = i;
            i++;
        }
    }

    /**
     * 获取玩家数与最大玩家数的比值
     *
     * @return 字符串化的比值
     */
    public static String ratioPlayers() {
        return "(" + players.size() + " / " + max_players + ")";
    }

    /**
     * 打印所有玩家的背包的方法
     */
    public static void printAllPlayerInv() {
        for (Player p : players) {
            Printer.p(p.name + ":");
            p.inv.listItems();
            System.out.println();
        }
    }
}
