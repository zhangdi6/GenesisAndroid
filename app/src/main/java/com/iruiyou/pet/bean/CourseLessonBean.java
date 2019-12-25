package com.iruiyou.pet.bean;

public class CourseLessonBean {
    private float time;
    private String title;
    private long videoId;
    private String duration;
    private int type;
    private String token;
    private int playing;
    private boolean isfree;


    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIsfree() {
        return isfree;
    }

    public void setIsfree(boolean isfree) {
        this.isfree = isfree;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPlaying() {
        return playing;
    }

    public void setPlaying(int playing) {
        this.playing = playing;
    }

    @Override
    public String toString() {
        return "CourseLessonBean{" +
                "time=" + time +
                ", title='" + title + '\'' +
                ", videoId=" + videoId +
                ", duration='" + duration + '\'' +
                ", type=" + type +
                ", token='" + token + '\'' +
                ", playing=" + playing +
                ", isfree=" + isfree +
                '}';
    }
}
