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
import java.util.UUID;

import static com.vint.utils.ParcerArgs.*;


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

    private static String frameMask;
    private static String averageBitrate;
    private static String minBitrate;
    private static String maxBitrate;
    private static String constantBitrate;
    private static String videoCodec;
    private static int fps;

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

            checkRequiredKeysForVideo();

//            TODO: Refactor. Added this func when parce arg.
            checkFpsValue();
            checkVideoOutput();

            if(input.isKEY_CBR()){
                if(constantBitrate == null){
                    constantBitrate = "200k";
                    Log.i("Constant bitrate value is empty. Used default value " + constantBitrate);
                }

                FFmpeg.createVideoFromImagesCBR(frameMask, fps, constantBitrate, output);
            } else if(input.isKEY_ABR()){

                if(averageBitrate == null){
                    averageBitrate = "200k";
                    Log.i("Constant bitrate value is empty. Used default value " + averageBitrate);
                }

                FFmpeg.createVideoFromImagesABR(frameMask, fps, averageBitrate, output);
            } else if(input.isKEY_VBR()){

//                TODO: Code this

                FFmpeg.createVideoFromImagesVBR(frameMask, fps, minBitrate, maxBitrate, output);
            }

        } else if (type == VIDEO_WITH_AUDIO){
            Log.d("VIDEO & AUDIO");
        }
    }

//    TODO: Refactor.
    private static void checkVideoOutput() {
        if (output == null){
            String fileName = "video"+ UUID.randomUUID().toString();
            output = System.getProperty("user.dir") + File.separator + fileName + ".mp4";
        }
    }

    private static void checkFpsValue() {
        if(fps <= 0){
            Log.e("FPS value must be greater than zero");
            System.exit(0);
        }
    }

    private static void checkRequiredKeysForVideo() {
        if(!input.isKEY_FRAME_MASK()){
            Log.e(Error.MISSED_KEY + FRAME_MASK_KEY);
            System.exit(0);
        }
        if(!input.isKEY_FPS()){
            Log.e(Error.MISSED_KEY + FPS_KEY);
            System.exit(0);
        }

        if(!input.isKEY_CBR() && !input.isKEY_ABR() && !input.isKEY_VBR()){
//            TODO: Refactor this msg.
            Log.e("Bitrate key required. Choose one of this keys: "+ CBR_KEY +" "+ ABR_KEY +" "+ VBR_KEY);
            System.exit(0);
        }
    }

    private static void init(String[] args) {
        input = ParcerArgs.parse(args);

        duration = input.getDuration();
        source = input.getAudioSourcePath();
        output = input.getOutputPath();
        effects = input.getSoundEffects();
        timeline = input.getTimeline();

        frameMask = input.getFrameMask();
        averageBitrate = input.getAverageBitrate();
        minBitrate = input.getMinBitrate();
        maxBitrate = input.getMaxBitrate();
        constantBitrate = input.getConstantBitrate();
        videoCodec = input.getVideoCodec();
        fps = input.getFps();
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
