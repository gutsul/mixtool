package com.vint.model;

/**
 * Created by Yuriy Grigortsevich on 08.09.15.
 */
public class Input {
    private String sourcePath;
    private String[] soundEffects;
    private String[] timeline;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
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

        public Builder setSourcePath(String sourcePath) {
            Input.this.sourcePath = sourcePath;

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
