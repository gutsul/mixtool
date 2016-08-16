package com.vint.ffmpeg;

import com.vint.utils.Log;
import com.vint.utils.Utils;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ygrigortsevich on 15.08.16.
 */
public class FFmpeg {

    public static void createSilentAudio(int duration, String outputName) throws IOException, InterruptedException {
        Utils.deleteExistedFile(outputName);
        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -f lavfi -i \"aevalsrc=0|0:d=" + duration + "\" "+ outputName };
        runLinuxCommand(cmd);
    }

    public static void mixSoundEffects(String source, String[]effects, String[]timeLine, String outputName) throws IOException, InterruptedException {
        Utils.deleteExistedFile(outputName);

        int size = effects.length + 1;
        String effectList = "";
        for (String effect: effects){
            effectList += "-i " + effect + " ";
        }

        String delaylist = "";
        String streams = "";
        int[] time = new int[timeLine.length];
        for (int i = 0; i < timeLine.length; i++){
            if (timeLine[i].equals("0")){
                time[i] = 1;
            } else {
                time[i] = Integer.parseInt(timeLine[i]);
            }
            String stream = "[stream"+ (i + 1) +"]";
            delaylist += "["+ (i + 1) +"]adelay="+time[i]+"|"+ time[i] + stream + "; ";
            streams +=stream;
        }

        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -i " + source + " " + effectList + "-filter_complex \"" + delaylist + "[0]"+streams+"amix=" + size + "\" " + outputName};

        Log.d("ffmpeg: " + Arrays.toString(cmd));

        runLinuxCommand(cmd);
    }

    private static void runLinuxCommand(String[] cmd) throws IOException, InterruptedException {
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
    }

}
