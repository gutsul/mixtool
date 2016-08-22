package com.vint.ffmpeg;

import com.vint.model.Effect;
import com.vint.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ygrigortsevich on 15.08.16.
 */
public class FFmpeg {

    public static void createSilentAudio(int duration, String outputName) throws IOException, InterruptedException {
        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -y -f lavfi -i \"aevalsrc=0|0:d=" + duration + "\" "+ outputName };
        runLinuxCommand(cmd);
    }

    public static void mixSoundEffects(String source, ArrayList<Effect> shedule, String outputName) throws IOException, InterruptedException {
        int size = shedule.size() + 1;
        int sourceDuration = getDuration(source);

        String effectList ="";
        String delaylist ="";
        String streams ="";
        int i = 1;
        for(Effect effect: shedule){
            if(effect.isLoop()){
                int effectDuration = getDuration(effect.getEffectPath());
                int loops = ((sourceDuration - (effect.getTimeStart()/1000))/ effectDuration) + 1;
                effect.setEffectPath(loop(loops, effect.getEffectPath()));
            }
            effectList += "-i " + effect.getEffectPath() + " ";
            String stream = "[stream"+ i +"]";
            delaylist += "["+ i +"]adelay="+ effect.getTimeStart() +"|"+ effect.getTimeStart() + stream + "; ";
            streams +=stream;
            i++;
        }

        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -y -i " + source + " " + effectList + "-filter_complex \"" + delaylist + "[0]"+streams+"amix=" + size + ":duration=first\" " + outputName};

        runLinuxCommand(cmd);
    }

    public static int getDuration(String filePath) throws IOException, InterruptedException {
        String[] cmd = new String[]{"/bin/sh", "-c", "ffprobe -i "+ filePath +" -show_entries format=duration -v quiet -of csv=\"p=0\""};
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        int duration = (int) Float.parseFloat(stdInput.readLine());
        return duration;
    }

    public static String loop(int loops, String filePath) throws IOException, InterruptedException {
        String tmp = "list.txt";
        String output = "loop" + UUID.randomUUID() + ".wav";

        File list = new File(tmp);
        FileWriter fileWriter = new FileWriter(list);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < loops; i++){
            printWriter.println("file '"+ filePath + "'");
        }
        printWriter.close();

        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -f concat -i "+ tmp +" -c copy " + output};
        runLinuxCommand(cmd);

        Utils.deleteExistedFile(tmp);
        return output;
    }

    public static void mergeVideoAndAudio(String pathToVideo, String pathToAudio, String outputName) throws IOException, InterruptedException {
        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -y -i "+ pathToVideo +" -i "+ pathToAudio+" -c:v copy -c:a aac -strict experimental -b:a 192k -shortest "+ outputName };
        runLinuxCommand(cmd);
    }

    public static void createVideoFromImagesCBR(String fileMask, int framerate, String constantBitrate, String outputName) throws IOException, InterruptedException {
      createVideoFromImagesVBR(fileMask, framerate, constantBitrate, constantBitrate, outputName);
    }

    public static void createVideoFromImagesVBR(String fileMask, int framerate, String minBitrate, String maxBitrate, String outputName) throws IOException, InterruptedException {
        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -y -framerate "+ framerate +" -i "+ fileMask +" -c:v libx264 -minrate "+ minBitrate +" -maxrate "+ maxBitrate +" -pix_fmt yuv420p "+ outputName };
        runLinuxCommand(cmd);
    }

    public static void createVideoFromImagesABR(String fileMask, int framerate, String avarageBitrate, String outputName) throws IOException, InterruptedException {
        String[] cmd = new String[]{"/bin/sh", "-c", "ffmpeg -y -framerate "+ framerate +" -i "+ fileMask +" -c:v libx264 -b:v "+ avarageBitrate +" -pix_fmt yuv420p "+ outputName };
        runLinuxCommand(cmd);
    }

    private static void runLinuxCommand(String[] cmd) throws IOException, InterruptedException {
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();

//        BufferedReader stdInput = new BufferedReader(new
//                InputStreamReader(pr.getInputStream()));
//
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(pr.getErrorStream()));
//
//        // read the output from the command
//        System.out.println("Here is the standard output of the command:");
        String s;
//        while ((s = stdInput.readLine()) != null) {
//            System.out.println(s);
//        }
//
        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }

}
