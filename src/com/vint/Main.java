package com.vint;

import com.sun.deploy.util.SystemUtils;
import com.vint.ffmpeg.FFmpeg;
import com.vint.model.Input;
import com.vint.utils.Log;
import com.vint.utils.ParcerArgs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

// TEST
//        args = new String[]{ParcerArgs.SOURCE_PATH_KEY, "/home/ygrigortsevich/IdeaProjects/mixtool/test1.wav", ParcerArgs.SOUND_EFECTS_KEY, "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/sound_efects/ahem_x.wav","/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/sound_efects/alarm_beep.wav", ParcerArgs.TIMELINE_KEY, "0", "25"};

        Input input = ParcerArgs.parse(args);

        String source = input.getSourcePath();
        Log.d("source: " + source);

        String[] effects = input.getSoundEffects();
        for (String msg: effects){
            Log.d("effect:" + msg);
        }

        String[] timeLine = input.getTimeline();
        for (String msg: timeLine){
            Log.d("timeline: " + msg);
        }

        String sourceFileName;
        if (source != null){
            File sourceFile = new File(source);
            sourceFileName = sourceFile.getName();
        } else {
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            sourceFileName = "silent.wav";
            FFmpeg.createSilentAudio(60,sourceFileName);
            source = sourceFileName;
        }

        String mixedFileName = "mixed-" + sourceFileName;
        FFmpeg.mixSoundEffects(source, effects, timeLine, mixedFileName);

    }
}
