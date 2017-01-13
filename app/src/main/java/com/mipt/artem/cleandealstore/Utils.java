package com.mipt.artem.cleandealstore;

import java.text.DecimalFormat;

/**
 * Created by artem on 20.08.16.
 */
public class Utils {
    static public String addSymbolOfRuble(String string) {
        return string + " \u20BD";
    }

    static public String addSymbolOfRuble(int price) {
        return Integer.toString(price) + " \u20BD";
    }

    static public String addSymbolOfRuble(double price) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(price) + " \u20BD";
    }

    public static String generatePriceString(double cost, int count) {
        return new StringBuilder().append(Utils.addSymbolOfRuble(cost))
                .append(" \u00D7 ")
                .append(Integer.toString(count))
                .append(" \u003D ")
                .append(Utils.addSymbolOfRuble(count * cost))
                .toString();
    }
}

