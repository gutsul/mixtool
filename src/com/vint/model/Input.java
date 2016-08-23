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

    private boolean KEY_DURATION = false;
    private boolean KEY_SOURCE_PATH = false;
    private boolean KEY_OUTPUT_PATH = false;
    private boolean KEY_SOUND_EFFECTS = false;
    private boolean KEY_TIMELINE = false;

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
        public Input build() {
            return Input.this;
        }
    }

}
