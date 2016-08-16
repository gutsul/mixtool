package com.vint.utils;

/**
 * Created by ygrigortsevich on 15.08.16.
 */
public class Log {
    public static void d(String msg){
        System.out.println("[DEBUG] " + msg);
    }

    public static void e(String msg){
        System.out.println("[ERROR] " + msg);
    }
}
