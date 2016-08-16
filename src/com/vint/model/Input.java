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

        public Input build() {
            return Input.this;
        }
    }

}
