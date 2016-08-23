package com.vint;

import com.vint.ffmpeg.FFmpeg;
import com.vint.model.Effect;
import com.vint.model.Input;
import com.vint.utils.Error;
import com.vint.utils.Log;
import com.vint.utils.ParcerArgs;
import com.vint.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    private static String SILENT_FILE="silent.wav";
    private static final int AUDIO_ONLY = 0;
    private static final int VIDEO_ONLY = 1;
    private static final int VIDEO_WITH_AUDIO = 2;

    private static Input input;
    private static int duration;
    private static String source;
    private static String output;
    private static String[] effects;
    private static String[] timeline;
    private static ArrayList<Effect> schedule;

    public static void main(String[] args) throws IOException, InterruptedException {
//        args = new String[]{
//            "-asrc",
//            "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/test/test1.wav",
//            "-se",
//            "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/sound_efects/car_x.wav",
//            "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/sound_efects/ahem_x.wav",
//            "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/sound_efects/applause_y.wav",
//            "-t",
//            "0",
//            "0L",
//            "18000",
//            "-out",
//            "/home/ygrigortsevich/Documents/SpilnaSprava/ffmpeg/output/test1-loop.wav",
//            "-fm"
//        };
        init(args);

        int type = determineType();

        if(type == AUDIO_ONLY){
            Log.d("AUDIO ONLY");

            checkAudioSource();
            checkEffects();
            checkTimeLine();
            checkOutput();

            schedule = createSchedule();

            FFmpeg.mixSoundEffects(source, schedule, output);
            deleteTmpFiles();
            Log.i("File generated in " + output);
        } else if(type == VIDEO_ONLY){
            Log.d("VIDEO ONLY");
        } else if (type == VIDEO_WITH_AUDIO){
            Log.d("VIDEO & AUDIO");
        }
    }

    private static void init(String[] args) {
        input = ParcerArgs.parse(args);

        duration = input.getDuration();
        source = input.getAudioSourcePath();
        output = input.getOutputPath();
        effects = input.getSoundEffects();
        timeline = input.getTimeline();
    }

    private static int determineType() {
        int type = AUDIO_ONLY;

        if((input.isKEY_DURATION() ||
                input.isKEY_AUDIO_SOURCE_PATH() ||
                input.isKEY_SOUND_EFFECTS() ||
                input.isKEY_TIMELINE()) &&
                (input.isKEY_FRAME_MASK() ||
                 input.isKEY_ABR() ||
                 input.isKEY_VBR() ||
                 input.isKEY_CBR() ||
                 input.isKEY_VIDEO_CODEC() ||
                 input.isKEY_FPS())){
            type = VIDEO_WITH_AUDIO;
        } else if(input.isKEY_DURATION() ||
                input.isKEY_AUDIO_SOURCE_PATH() ||
                input.isKEY_SOUND_EFFECTS() ||
                input.isKEY_TIMELINE()){
            type = AUDIO_ONLY;
        } else if(input.isKEY_FRAME_MASK() ||
                input.isKEY_ABR() ||
                input.isKEY_VBR() ||
                input.isKEY_CBR() ||
                input.isKEY_VIDEO_CODEC() ||
                input.isKEY_FPS()){
            type = VIDEO_ONLY;
        }
        return type;
    }

    private static void checkAudioSource() throws IOException, InterruptedException {
        if (source != null){
            checkFileExist(source);
        } else {
            if(duration > 0){
                FFmpeg.createSilentAudio(duration, SILENT_FILE);
                source = SILENT_FILE;
            } else {
                Log.e(Error.MISSED_KEY + ParcerArgs.AUDIO_SOURCE_PATH_KEY);
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

    private static ArrayList<Effect> createSchedule() {
        ArrayList<Effect> schedule = new ArrayList<>();
        for (int i = 0; i < timeline.length; i++){
            Effect effect = new Effect();
            if(timeline[i].contains("L")){
                effect.setLoop(true);
                String replaced = timeline[i].replace("L","");
                timeline[i] = replaced;
            } else {
                effect.setLoop(false);
            }
            int timeStart;
            try {
                timeStart = Integer.parseInt(timeline[i]);
                effect.setTimeStart(timeStart);
            } catch (Exception e) {
                Log.e(Error.BAD_TIME_FORMAT + timeline[i]);
                System.exit(0);
            }
            effect.setEffectPath(effects[i]);
            schedule.add(effect);
        }
        return schedule;
    }

    private static void deleteTmpFiles(){
        Utils.deleteExistedFile(SILENT_FILE);
        for(Effect effect: schedule){
            if(effect.isLoop()){
                Utils.deleteExistedFile(effect.getEffectPath());
            }
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
