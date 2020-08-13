package com.education.client.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "videoName",
        "videoType",
        "videoData",
        "section"
})
public class SavedNewVideo {
    @JsonProperty("videoName")
    private String videoName;
    @JsonProperty("videoType")
    private String videoType;
    @JsonProperty("videoData")
    private byte[] videoData;
    @JsonProperty("section")
    private Section section;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("videoName")
    public String getVideoName() {
        return videoName;
    }

    @JsonProperty("videoName")
    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @JsonProperty("videoType")
    public String getVideoType() {
        return videoType;
    }

    @JsonProperty("videoType")
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    @JsonProperty("videoData")
    public byte[] getVideoData() {
        return videoData;
    }

    @JsonProperty("videoData")
    public void setVideoData(byte[] videoData) {
        this.videoData = videoData;
    }

    @JsonProperty("section")
    public Section getVideoSection() {
        return section;
    }

    @JsonProperty("section")
    public void setVideoSection(Section videoSection) {
        this.section = videoSection;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
