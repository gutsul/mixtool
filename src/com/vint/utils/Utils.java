package com.vint.utils;

import java.io.File;

/**
 * Created by ygrigortsevich on 16.08.16.
 */
public class Utils {
    public static void deleteExistedFile(String outputName){
        File file = new File(outputName);
        if (file.exists()){
            file.delete();
            Log.d("File " + outputName + " exist & deleted.");
        }
    }
}
