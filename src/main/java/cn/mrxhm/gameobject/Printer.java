package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

public class Printer {
    public static void p(Object o) {
        System.out.println(parseText(o));
    }

    public static void p(Object o, boolean line_break) {
        if (line_break) {
            p(o);
        } else {
            System.out.print(parseText(o));
        }
    }

    public static String parseText(Object o) {
        if (o instanceof String result) {
            for (String key : GameUtils.lang_map.keySet()) {
                result = result.replaceAll(key, GameUtils.lang_map.get(key));
            }
            return result;
        } else {
            return parseText(o.toString());
        }
    }
}
