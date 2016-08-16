package com.vint;

import com.vint.ffmpeg.FFmpeg;
import com.vint.model.Input;
import com.vint.utils.Error;
import com.vint.utils.Log;
import com.vint.utils.ParcerArgs;
import com.vint.utils.Utils;

import java.io.File;
import java.io.IOException;


public class Main {

    private static String SILENT_FILE="silent.wav";

    private static int duration;
    private static String source;
    private static String output;
    private static String[] effects;
    private static String[] timeline;

    public static void main(String[] args) throws IOException, InterruptedException {
        init(args);

        checkSource();
        checkEffects();
        checkTimeLine();
        checkOutput();

        FFmpeg.mixSoundEffects(source, effects, timeline, output);
        Utils.deleteExistedFile(SILENT_FILE);
        Log.i("File generated in " + output);
    }

    private static void init(String[] args) {
        Input input = ParcerArgs.parse(args);

        duration = input.getDuration();
        source = input.getSourcePath();
        output = input.getOutputPath();
        effects = input.getSoundEffects();
        timeline = input.getTimeline();
    }

    private static void checkSource() throws IOException, InterruptedException {
        if (source != null){
            checkFileExist(source);
        } else {
            if(duration > 0){
                FFmpeg.createSilentAudio(duration, SILENT_FILE);
                source = SILENT_FILE;
            } else {
                Log.e(Error.MISSED_KEY + ParcerArgs.SOURCE_PATH_KEY);
//                Log.e(Error.ERROR1);
                System.exit(0);
            }
        }
    }

    private static void checkEffects() {
        if (effects == null){
            Log.e(Error.MISSED_KEY + ParcerArgs.SOUND_EFECTS_KEY);
            System.exit(0);
        } else {
            for (String effect: effects){
                checkFileExist(effect);
            }
        }
    }

    private static void checkTimeLine() {
        if (timeline == null){
            Log.e(Error.MISSED_KEY + ParcerArgs.TIMELINE_KEY);
            System.exit(0);
        } else if (timeline.length != effects.length){
               Log.e(Error.ERROR2);
               System.exit(0);
        }
    }

    private static void checkOutput() {
        if (output == null){
            String fileName = new File(source).getName();
            output = System.getProperty("user.dir") + File.separator + fileName;
        }
    }

    private static void checkFileExist(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            Log.e(Error.FILE_NOT_EXIST + filePath);
            System.exit(0);
        }
    }
}
