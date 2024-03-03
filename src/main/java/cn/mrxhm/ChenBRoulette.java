package cn.mrxhm;

import cn.mrxhm.gameobject.*;
import cn.mrxhm.utils.GameUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChenBRoulette {
    public static Scanner scan;
    public static Operation operation;
    public static Player player_s;
    public static Player player_t;
    public static Item item_c;

    public static void main(String[] args) throws IOException, InterruptedException {
        loadConfig(args);
        GameUtils.init();
        Ammos.init();
        scan = new Scanner(System.in);
        operation = Operation.CREATE_PLAYER;
        Printer.p("text.welcome");
        interact();
    }

    public static boolean loadConfig(String[] args) throws IOException {
        boolean result = false;
        for (String s : args) {
            if (Pattern.matches("[a-z_]+=(\\d+(\\.\\d+)?)", s)) {
                String[] conf = s.split("=");
                if (conf.length == 2) {
                    String key = conf[0];
                    double value = Double.parseDouble(conf[1]);
                    switch (key.toLowerCase()) {
                        case "max_health" -> {
                            GameUtils.max_health = value;
                            Printer.p("utils.max_health" + GameUtils.max_health);
                        }
                        case "min_damage_multi" -> {
                            GameUtils.min_damage_multi = value;
                            Printer.p("utils.min_damage_multi" + GameUtils.min_damage_multi);
                        }
                        case "max_damage_multi" -> {
                            GameUtils.max_damage_multi = value;
                            Printer.p("utils.max_damage_multi" + GameUtils.max_damage_multi);
                        }
                        case "basic_damage" -> {
                            GameUtils.basic_damage = value;
                            Printer.p("utils.basic_damage" + GameUtils.basic_damage);
                        }
                        case "max_ammo" -> {
                            GameUtils.max_ammo = (int) value;
                            Printer.p("utils.max_ammo" + GameUtils.max_ammo);
                        }
                        case "max_players" -> {
                            GameUtils.max_players = (int) value;
                            Printer.p("utils.max_players" + GameUtils.max_players);
                        }
                    }
                }
                result = true;
            } else if (Pattern.matches("lang=[a-zA-Z_]+", s)) {
                String[] conf = s.split("=");
                if (conf.length == 2) {
                    GameUtils.lang = conf[1];
                    GameUtils.initLangFile(GameUtils.lang);
                    GameUtils.parseLangFile();
                    Printer.p("utils.lang" + GameUtils.lang);
                }
                result = true;
            }
        }
        return result;
    }


    public static void interact() throws InterruptedException {
        System.out.print("\r>>> ");
        String input = scan.nextLine();
        if (!input.equalsIgnoreCase("QUIT")) {
            if (!input.equals("")) {
                interact(input);
            } else {
                updateStep();
            }
            interact();
        }
    }

    public static void interact(String command) {
        switch (operation) {
            case CREATE_PLAYER -> {
                Player p = new Player(command);
                if (GameUtils.addPlayer(p)) {
                    Printer.p(p.name + "\ttext.add.player.true\t" + GameUtils.ratioPlayers());
                } else {
                    Printer.p("text.add.player.false");
                }
            }
            case PLAYER_CHOICE -> {
                if (Pattern.matches("\\d+", command)) {
                    int choice_id = Integer.parseInt(command);
                    if (0 < choice_id && choice_id <= player_s.inv.items.size()) {
                        item_c = player_s.inv.items.get(choice_id - 1);
                        Printer.p("text.item.choice" + item_c);
                    } else {
                        item_c = null;
                        Printer.p("text.item.choice" + "item.gun");
                    }
                } else {
                    Printer.p("text.command_error");
                }
            }
            case PLAYER_CHOICE_GUN, PLAYER_CHOICE_LOCK -> {
                int choice_id;
                if (Pattern.matches("\\d+", command)) {
                    choice_id = Integer.parseInt(command);
                    player_t = GameUtils.getPlayer(choice_id);
                    if (player_t != null) {
                        Printer.p("text.player.choice" + player_t);
                    } else {
                        Printer.p("text.player.not_found");
                    }
                } else {
                    Printer.p("text.command_error");
                }
            }
        }
    }

    public static void updateStep() throws InterruptedException {
        if (operation == Operation.CREATE_PLAYER) {
            if (GameUtils.players.size() == GameUtils.max_players) {
                for (int i = 5; i > 0; i--) {
                    Printer.p("\r" + "text.full_player" + i, false);
                    Thread.sleep(1000);
                }
                Printer.p("\rtext.random_ammo\n", false);
                Thread.sleep(1000);
                GameUtils.fillAmmo();
                for (int i = 3; i > 0; i--) {
                    Printer.p("\r" + i + "s text.next_operation", false);
                    Thread.sleep(1000);
                }
                Printer.p("\rtext.random_item");
                Thread.sleep(1000);
                GameUtils.extractItem();
                GameUtils.printAllPlayerInv();
                player_s = GameUtils.players.get(0);
                Thread.sleep(1000);
                Printer.p("\rtext.player_of_operation" + player_s.name);
                Printer.p("text.please_try");
                player_s.inv.listItems();

                operation = Operation.PLAYER_CHOICE;
            }
        } else {
            switch (operation) {
                case PLAYER_CHOICE -> {
                    if (item_c != null) {
                        if (item_c instanceof Handcuffs) {
                            Printer.p("text.target.choice");
                            GameUtils.listPlayers();
                            operation = Operation.PLAYER_CHOICE_LOCK;
                        } else {
                            item_c.use();
                            item_c = null;
                            if (Ammos.ammoList.isEmpty()) {
                                GameUtils.newTurn();
                                break;
                            }
                            Thread.sleep(1500);
                            Printer.p("text.please_try");
                            player_s.inv.listItems();
                        }
                    } else {
                        Printer.p("text.target.choice");
                        GameUtils.listPlayers();
                        operation = Operation.PLAYER_CHOICE_GUN;
                    }
                }
                case PLAYER_CHOICE_GUN -> {
                    if (player_t != null) {
                        if (Ammos.current()) {
                            double d = GameUtils.basic_damage * GameUtils.damage_multi;
                            player_t.damage(d);
                            Printer.p(player_t.name + " gun.hits " + d);
                            GameUtils.listPlayers();
                            GameUtils.nextPlayer();
                        } else {
                            Printer.p(player_t.name + " gun.misses");
                            if (player_s != player_t) {
                                GameUtils.nextPlayer();
                            }
                        }
                        Thread.sleep(1500);
                        operation = Operation.PLAYER_CHOICE;
                        if (!Ammos.ammoList.isEmpty()) {
                            Printer.p("text.player_of_operation" + player_s);
                            Printer.p("text.please_try");
                            player_s.inv.listItems();
                        }
                        GameUtils.damage_multi = 1.0;
                        player_t = null;
                        Ammos.removeAmmo();
                    }
                    if (Ammos.ammoList.isEmpty()) {
                        GameUtils.newTurn();
                    }
                }
                case PLAYER_CHOICE_LOCK -> {
                    if (player_t != null) {
                        item_c.use();
                        operation = Operation.PLAYER_CHOICE;
                        Printer.p("text.player_of_operation" + player_s);
                        Printer.p("text.please_try");
                        player_s.inv.listItems();
                        player_t = null;
                    }
                }
            }
        }
    }
}
