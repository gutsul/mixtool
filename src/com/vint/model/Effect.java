package com.vint.model;

/**
 * Created by ygrigortsevich on 15.08.16.
 */
public class Effect {
    private String effectPath;
    private int timeStart;
    private boolean isLoop = false;

    public String getEffectPath() {
        return effectPath;
    }

    public void setEffectPath(String effectPath) {
        this.effectPath = effectPath;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart == 0 ? 1 : timeStart;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }
}
