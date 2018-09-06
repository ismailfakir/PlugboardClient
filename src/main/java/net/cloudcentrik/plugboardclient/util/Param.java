package net.cloudcentrik.plugboardclient.util;

import javafx.util.Pair;

public class Param {
    public static Pair param(String key, Object value){
            return new Pair(key,value);
    }
}
