package com.vint.utils;

import com.vint.model.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ygrigortsevich on 15.08.16.
 */
public class ParcerArgs {
    public static final String SOURCE_PATH_KEY = "-src";
    public static final String SOURCE_PATH_VERBOSE_KEY = "--source";
    public static final String SOUND_EFECTS_KEY = "-se";
    public static final String SOUND_EFFECTS_VERBOSE_KEY = "--sound-efects";
    public static final String TIMELINE_KEY = "-t";
    public static final String TIMELINE_VERBOSE_KEY = "--timeline";

    public static final String[] KEYS = new String[] {
        SOURCE_PATH_KEY, SOUND_EFECTS_KEY, TIMELINE_KEY
    };

    public static Input parse(String[] args){
        Input.Builder inputBuilder = Input.newBuilder();
        int max = args.length;
        for (int i = 0; i < max; i++) {
            String arg = args[i];
            if (arg.equals(SOURCE_PATH_KEY)){
                String value = (i + 1 < max)?args[i + 1]: null;
                inputBuilder.setSourcePath(value);
            } else if (arg.equals(SOUND_EFECTS_KEY)) {
                inputBuilder.setSoundEffects(getValuesList(i,args));
            } else if (arg.equals(TIMELINE_KEY)){
                inputBuilder.setTimeLine(getValuesList(i,args));
            } else {
//              TODO Add usage;
            }
        }
        return inputBuilder.build();
    }

    private static String[] getValuesList(int position, String[] args){
        ArrayList<String> values = new ArrayList<>();
        int max = args.length;

        for (int i = position; i < max; i++) {
            if (i + 1 < max) {
                String value = args[i + 1];
                if (!isKey(value)){
                    values.add(value);
                } else {
                    break;
                }
            }
        }
        return values.toArray(new String[values.size()]);
    }

    private static boolean isKey(String value){
        for (String key: KEYS){
            if (value.equals(key)){
                return true;
            }
        }
        return false;
    }

}
