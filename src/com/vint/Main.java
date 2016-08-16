package com.vint;

import com.vint.ffmpeg.FFmpeg;
import com.vint.model.Input;
import com.vint.utils.Error;
import com.vint.utils.Log;
import com.vint.utils.ParcerArgs;

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
// TEST
        args = new String[]{ParcerArgs.SOURCE_PATH_KEY, "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/test/output.wav", ParcerArgs.SOUND_EFECTS_KEY, "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/sound_efects/ahem_x.wav","/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/sound_efects/alarm_beep.wav", ParcerArgs.TIMELINE_KEY, "0", "25000"};
        init(args);

        if (source != null){
            File sourceFile = new File(source);
            if (!sourceFile.exists()){
                Log.e(Error.ERROR2);
                return;
            }
        } else {
            if(duration > 0){
                FFmpeg.createSilentAudio(duration, SILENT_FILE);
                source = SILENT_FILE;
            } else {
                Log.e(Error.ERROR1);
                return;
            }
        }

        if (output == null){
            String fileName = new File(source).getName();
            output = System.getProperty("user.dir") + File.separator + fileName;
        }
        FFmpeg.mixSoundEffects(source, effects, timeline, output);
    }

    private static void init(String[] args) {
        Input input = ParcerArgs.parse(args);

        duration = input.getDuration();
        source = input.getSourcePath();
        output = input.getOutputPath();
        effects = input.getSoundEffects();
        timeline = input.getTimeline();
    }
}
