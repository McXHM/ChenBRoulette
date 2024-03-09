package cn.mrxhm.gameobject;

import cn.mrxhm.utils.GameUtils;

/**
 * 一个用于本地化的输出类
 */
public class Printer {
    /**
     * 打印组件
     *
     * @param o 组件
     */
    public static void p(Object o) {
        System.out.println(parseText(o));
    }

    /**
     * 打印组件
     *
     * @param o          组件
     * @param line_break 是否换行
     */
    public static void p(Object o, boolean line_break) {
        if (line_break) {
            p(o);
        } else {
            System.out.print(parseText(o));
        }
    }

    /**
     * 本地化组件
     *
     * @param o 组件
     * @return 本地化后的文本
     */
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
