package com.vint.model;

/**
 * Created by Yuriy Grigortsevich on 08.09.15.
 */
public class Input {
    private int duration;
    private String sourcePath;
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
    private boolean KEY_SOURCE_PATH = false;
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

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String[] getSoundEffects() {
        return soundEffects;
    }

    public void setSoundEffects(String[] soundEffects) {
        this.soundEffects = soundEffects;
    }

    public String[] getTimeline() {
        return timeline;
    }

    public void setTimeline(String[] timeline) {
        this.timeline = timeline;
    }

    public String getFrameMask() {
        return frameMask;
    }

    public void setFrameMask(String frameMask) {
        this.frameMask = frameMask;
    }

    public String getAvarageBitrate() {
        return avarageBitrate;
    }

    public void setAvarageBitrate(String avarageBitrate) {
        this.avarageBitrate = avarageBitrate;
    }

    public String getMinBitrate() {
        return minBitrate;
    }

    public void setMinBitrate(String minBitrate) {
        this.minBitrate = minBitrate;
    }

    public String getMaxBitrate() {
        return maxBitrate;
    }

    public void setMaxBitrate(String maxBitrate) {
        this.maxBitrate = maxBitrate;
    }

    public String getConstantBitrate() {
        return constantBitrate;
    }

    public void setConstantBitrate(String constantBitrate) {
        this.constantBitrate = constantBitrate;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isKEY_DURATION() {
        return KEY_DURATION;
    }

    public void setKEY_DURATION(boolean KEY_DURATION) {
        this.KEY_DURATION = KEY_DURATION;
    }

    public boolean isKEY_SOURCE_PATH() {
        return KEY_SOURCE_PATH;
    }

    public void setKEY_SOURCE_PATH(boolean KEY_SOURCE_PATH) {
        this.KEY_SOURCE_PATH = KEY_SOURCE_PATH;
    }

    public boolean isKEY_OUTPUT_PATH() {
        return KEY_OUTPUT_PATH;
    }

    public void setKEY_OUTPUT_PATH(boolean KEY_OUTPUT_PATH) {
        this.KEY_OUTPUT_PATH = KEY_OUTPUT_PATH;
    }

    public boolean isKEY_SOUND_EFFECTS() {
        return KEY_SOUND_EFFECTS;
    }

    public void setKEY_SOUND_EFFECTS(boolean KEY_SOUND_EFFECTS) {
        this.KEY_SOUND_EFFECTS = KEY_SOUND_EFFECTS;
    }

    public boolean isKEY_TIMELINE() {
        return KEY_TIMELINE;
    }

    public void setKEY_TIMELINE(boolean KEY_TIMELINE) {
        this.KEY_TIMELINE = KEY_TIMELINE;
    }

    public boolean isKEY_FRAME_MASK() {
        return KEY_FRAME_MASK;
    }

    public void setKEY_FRAME_MASK(boolean KEY_FRAME_MASK) {
        this.KEY_FRAME_MASK = KEY_FRAME_MASK;
    }

    public boolean isKEY_ABR() {
        return KEY_ABR;
    }

    public void setKEY_ABR(boolean KEY_ABR) {
        this.KEY_ABR = KEY_ABR;
    }

    public boolean isKEY_VBR() {
        return KEY_VBR;
    }

    public void setKEY_VBR(boolean KEY_VBR) {
        this.KEY_VBR = KEY_VBR;
    }

    public boolean isKEY_CBR() {
        return KEY_CBR;
    }

    public void setKEY_CBR(boolean KEY_CBR) {
        this.KEY_CBR = KEY_CBR;
    }

    public boolean isKEY_VIDEO_CODEC() {
        return KEY_VIDEO_CODEC;
    }

    public void setKEY_VIDEO_CODEC(boolean KEY_VIDEO_CODEC) {
        this.KEY_VIDEO_CODEC = KEY_VIDEO_CODEC;
    }

    public boolean isKEY_FPS() {
        return KEY_FPS;
    }

    public void setKEY_FPS(boolean KEY_FPS) {
        this.KEY_FPS = KEY_FPS;
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
            Input.this.sourcePath = sourcePath;
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
            Input.this.setFrameMask(frameMask);
            return this;
        }

        public Builder setAvarageBitrate(String avarageBitrate) {
            Input.this.setAvarageBitrate(avarageBitrate);
            return this;
        }

        public Builder setMinBitrate(String minBitrate) {
            Input.this.setMinBitrate(minBitrate);
            return this;
        }

        public Builder setMaxBitrate(String maxBitrate) {
            Input.this.setMaxBitrate(maxBitrate);
            return this;
        }

        public Builder setConstantBitrate(String constantBitrate) {
            Input.this.setConstantBitrate(constantBitrate);
            return this;
        }

        public Builder setVideoCodec(String videoCodec) {
            Input.this.setVideoCodec(videoCodec);
            return this;
        }

        public Builder setFps(int fps) {
            Input.this.setFps(fps);
            return this;
        }

        public Builder setKeyDuration(boolean isExist) {
            Input.this.KEY_DURATION = isExist;
            return this;
        }

        public Builder setKeySourcePath(boolean isExist) {
            Input.this.KEY_SOURCE_PATH = isExist;
            return this;
        }

        public Builder setKeyOutputPath(boolean isExist) {
            Input.this.KEY_OUTPUT_PATH = isExist;
            return this;
        }

        public Builder setKeySoundEffects(boolean isExist) {
            Input.this.KEY_SOUND_EFFECTS = isExist;
            return this;
        }

        public Builder setKeyTimeline(boolean isExist) {
            Input.this.KEY_TIMELINE = isExist;
            return this;
        }

        public Builder setKeyFrameMask(boolean isExist) {
            Input.this.KEY_FRAME_MASK = isExist;
            return this;
        }

        public Builder setKeyABR(boolean isExist) {
            Input.this.KEY_ABR = isExist;
            return this;
        }

        public Builder setKeyVBR(boolean isExist) {
            Input.this.KEY_VBR = isExist;
            return this;
        }

        public Builder setKeyCBR(boolean isExist) {
            Input.this.KEY_CBR = isExist;
            return this;
        }

        public Builder setKeyVideoCodec(boolean isExist) {
            Input.this.KEY_VIDEO_CODEC = isExist;
            return this;
        }

        public Builder setKeyFps(boolean isExist) {
            Input.this.KEY_FPS = isExist;
            return this;
        }

        public Input build() {
            return Input.this;
        }
    }

}
