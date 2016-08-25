package com.vint.utils;

import com.vint.model.Input;

import java.util.ArrayList;

/**
 * Created by ygrigortsevich on 15.08.16.
 */
public class ParcerArgs {
    public static final String AUDIO_SOURCE_PATH_KEY = "-asrc";
    public static final String AUDIO_SOURCE_PATH_VERBOSE_KEY = "--audio-source";
    public static final String SOUND_EFECTS_KEY = "-se";
    public static final String SOUND_EFFECTS_VERBOSE_KEY = "--sound-effects";
    public static final String TIMELINE_KEY = "-t";
    public static final String TIMELINE_VERBOSE_KEY = "--timeline";
    public static final String OUTPUT_PATH_KEY = "-out";
    public static final String OUTPUT_PATH_VERBOSE_KEY = "--output";
    public static final String DURATION_KEY = "-d";
    public static final String DURATION_VERBOSE_KEY = "--duration";

    public static final String FRAME_MASK_KEY = "-fm";
    public static final String FRAME_MASK_VERBOSE_KEY = "--frame-mask";
    public static final String ABR_KEY = "-ABR";
    public static final String VBR_KEY = "-VBR";
    public static final String CBR_KEY = "-CBR";
    public static final String VIDEO_CODEC_KEY = "-vc";
    public static final String VIDEO_CODEC_VERBOSE_KEY = "--video-codec";
    public static final String FPS_KEY = "-fps";


    public static final String[] KEYS = new String[] {
            AUDIO_SOURCE_PATH_KEY,
            AUDIO_SOURCE_PATH_VERBOSE_KEY,
            SOUND_EFECTS_KEY,
            SOUND_EFFECTS_VERBOSE_KEY,
            TIMELINE_KEY,
            TIMELINE_VERBOSE_KEY,
            OUTPUT_PATH_KEY,
            OUTPUT_PATH_VERBOSE_KEY,
            DURATION_KEY,
            DURATION_VERBOSE_KEY,
            FRAME_MASK_KEY,
            FRAME_MASK_VERBOSE_KEY,
            ABR_KEY,
            VBR_KEY,
            CBR_KEY,
            VIDEO_CODEC_KEY,
            VIDEO_CODEC_VERBOSE_KEY,
            FPS_KEY
    };

    public static Input parse(String[] args){
        Input.Builder inputBuilder = Input.newBuilder();
        int max = args.length;
        for(int i = 0; i < max; i++) {
            String arg = args[i];
            if(arg.equals(AUDIO_SOURCE_PATH_KEY)){
                inputBuilder.setKey(AUDIO_SOURCE_PATH_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                if(isKey(value)){
                    value = null;
                }
                inputBuilder.setSourcePath(value);
            } else if(arg.equals(SOUND_EFECTS_KEY)) {
                inputBuilder.setKey(SOUND_EFECTS_KEY);
                inputBuilder.setSoundEffects(getValuesList(i,args));
            } else if(arg.equals(TIMELINE_KEY)){
                inputBuilder.setKey(TIMELINE_KEY);
                inputBuilder.setTimeLine(getValuesList(i,args));
            } else if(arg.equals(OUTPUT_PATH_KEY)){
                inputBuilder.setKey(OUTPUT_PATH_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                if(isKey(value)){
                    value = null;
                }
                inputBuilder.setOutputPath(value);
            } else if(arg.equals(DURATION_KEY)){
                inputBuilder.setKey(DURATION_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                try {
                    int duration = Integer.parseInt(value);
                    inputBuilder.setDuraion(duration);
                }catch (Exception e){
                    Log.e(Error.ONLY_NUMBERS + value);
                    System.exit(0);
                }
            } else if(arg.equals(FRAME_MASK_KEY)){
                inputBuilder.setKey(FRAME_MASK_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                if(isKey(value)){
                    value = null;
                }
                if(value == null){
                    Log.e(Error.MISSED_VALUE + FRAME_MASK_KEY);
                    System.exit(0);
                }
                inputBuilder.setFrameMask(value);
            } else if(arg.equals(ABR_KEY)){
                inputBuilder.setKey(ABR_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                if(isKey(value)){
                    value = null;
                }
                inputBuilder.setAverageBitrate(value);
            } else if(arg.equals(VBR_KEY)){
                inputBuilder.setKey(VBR_KEY);
                String[] values = getValuesList(i, args);

                if(values.length >= 2){
                    inputBuilder.setMinBitrate(values[0]);
                    inputBuilder.setMaxBitrate(values[1]);
                } else {
                    Log.e(Error.MISSED_VALUE + VBR_KEY);
                    System.exit(0);
                }
            } else if(arg.equals(CBR_KEY)){
                inputBuilder.setKey(CBR_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                if(isKey(value)){
                    value = null;
                }
                inputBuilder.setConstantBitrate(value);
            } else if(arg.equals(VIDEO_CODEC_KEY)){
//                TODO: Add default video codec
                inputBuilder.setKey(VIDEO_CODEC_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                if(isKey(value)){
                    value = null;
                }
                inputBuilder.setVideoCodec(value);
            } else if(arg.equals(FPS_KEY)){
                inputBuilder.setKey(FPS_KEY);
                String value = (i + 1 < max)?args[i + 1]: null;
                try {
                    int fps = Integer.parseInt(value);
                    inputBuilder.setFps(fps);
                }catch (Exception e){
                    Log.e(Error.ONLY_NUMBERS + value);
                    System.exit(0);
                }
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
