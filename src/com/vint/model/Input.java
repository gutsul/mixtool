package com.vint.model;

import static com.vint.utils.ParcerArgs.*;

/**
 * Created by Yuriy Grigortsevich on 08.09.15.
 */
public class Input {
    private int duration;
    private String audioSourcePath;
    private String outputPath;
    private String[] soundEffects;
    private String[] timeline;

    private String frameMask;
    private String avarageBitrate;
    private String minBitrate;
    private String maxBitrate;
    private String constantBitrate;
    private String videoCodec;
    private int fps;

    private boolean KEY_DURATION = false;
    private boolean KEY_AUDIO_SOURCE_PATH = false;
    private boolean KEY_OUTPUT_PATH = false;
    private boolean KEY_SOUND_EFFECTS = false;
    private boolean KEY_TIMELINE = false;
    private boolean KEY_FRAME_MASK = false;
    private boolean KEY_ABR = false;
    private boolean KEY_VBR = false;
    private boolean KEY_CBR = false;
    private boolean KEY_VIDEO_CODEC = false;
    private boolean KEY_FPS = false;

//    TODO: Refactor this

    public int getDuration() {
        return duration;
    }

    public String getAudioSourcePath() {
        return audioSourcePath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String[] getSoundEffects() {
        return soundEffects;
    }

    public String[] getTimeline() {
        return timeline;
    }

    public String getFrameMask() {
        return frameMask;
    }

    public String getAvarageBitrate() {
        return avarageBitrate;
    }

    public String getMinBitrate() {
        return minBitrate;
    }

    public String getMaxBitrate() {
        return maxBitrate;
    }

    public String getConstantBitrate() {
        return constantBitrate;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public int getFps() {
        return fps;
    }

    public boolean isKEY_DURATION() {
        return KEY_DURATION;
    }

    public boolean isKEY_AUDIO_SOURCE_PATH() {
        return KEY_AUDIO_SOURCE_PATH;
    }

    public boolean isKEY_OUTPUT_PATH() {
        return KEY_OUTPUT_PATH;
    }

    public boolean isKEY_SOUND_EFFECTS() {
        return KEY_SOUND_EFFECTS;
    }

    public boolean isKEY_TIMELINE() {
        return KEY_TIMELINE;
    }

    public boolean isKEY_FRAME_MASK() {
        return KEY_FRAME_MASK;
    }

    public boolean isKEY_ABR() {
        return KEY_ABR;
    }

    public boolean isKEY_VBR() {
        return KEY_VBR;
    }

    public boolean isKEY_CBR() {
        return KEY_CBR;
    }

    public boolean isKEY_VIDEO_CODEC() {
        return KEY_VIDEO_CODEC;
    }

    public boolean isKEY_FPS() {
        return KEY_FPS;
    }

    private Input(){

    }


    public static Builder newBuilder() {
        return new Input().new Builder();
    }

    public class Builder {

        private Builder(){

        }

        public Builder setDuraion(int duraion) {
            Input.this.duration = duraion;
            return this;
        }

        public Builder setSourcePath(String sourcePath) {
            Input.this.audioSourcePath = sourcePath;
            return this;
        }

        public Builder setOutputPath(String outputPath) {
            Input.this.outputPath = outputPath;
            return this;
        }

        public Builder setSoundEffects(String[] soundEffects) {
            Input.this.soundEffects = soundEffects;
            return this;
        }

        public Builder setTimeLine(String[] timeLine) {
            Input.this.timeline = timeLine;
            return this;
        }

        public Builder setFrameMask(String frameMask) {
            Input.this.frameMask = frameMask;
            return this;
        }

        public Builder setAvarageBitrate(String avarageBitrate) {
            Input.this.avarageBitrate = avarageBitrate;
            return this;
        }

        public Builder setMinBitrate(String minBitrate) {
            Input.this.minBitrate = minBitrate;
            return this;
        }

        public Builder setMaxBitrate(String maxBitrate) {
            Input.this.maxBitrate = maxBitrate;
            return this;
        }

        public Builder setConstantBitrate(String constantBitrate) {
            Input.this.constantBitrate = constantBitrate;
            return this;
        }

        public Builder setVideoCodec(String videoCodec) {
            Input.this.videoCodec = videoCodec;
            return this;
        }

        public Builder setFps(int fps) {
            Input.this.fps = fps;
            return this;
        }


        public Builder setKey(String key) {
            if(key.equals(AUDIO_SOURCE_PATH_KEY)){
                Input.this.KEY_AUDIO_SOURCE_PATH = true;
            } else if(key.equals(SOUND_EFECTS_KEY)) {
                Input.this.KEY_SOUND_EFFECTS = true;
            } else if(key.equals(TIMELINE_KEY)){
                Input.this.KEY_TIMELINE = true;
            } else if(key.equals(OUTPUT_PATH_KEY)){
                Input.this.KEY_OUTPUT_PATH =true;
            } else if(key.equals(DURATION_KEY)){
                Input.this.KEY_DURATION = true;
            } else if(key.equals(FRAME_MASK_KEY)){
                Input.this.KEY_FRAME_MASK = true;
            } else if(key.equals(ABR_KEY)){
                Input.this.KEY_ABR = true;
            } else if(key.equals(VBR_KEY)){
                Input.this.KEY_VBR = true;
            } else if(key.equals(CBR_KEY)){
                Input.this.KEY_CBR = true;
            } else if(key.equals(VIDEO_CODEC_KEY)){
               Input.this.KEY_VIDEO_CODEC = true;
            } else if(key.equals(FPS_KEY)){
                Input.this.KEY_FPS = true;
            }
            return this;
        }

        public Input build() {
            return Input.this;
        }
    }

}
