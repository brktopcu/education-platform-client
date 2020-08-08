package com.education.client.data;

public class Video {
    private Long videoId;

    private String videoName;

    private String videoType;

    private byte[] videoData;

    private Section section;

    public Video(Long videoId, String videoName, String videoType, byte[] videoData, Section section) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoType = videoType;
        this.videoData = videoData;
        this.section = section;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public byte[] getVideoData() {
        return videoData;
    }

    public void setVideoData(byte[] videoData) {
        this.videoData = videoData;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
