package com.education.client.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "videoId",
        "videoName",
        "videoType",
        "videoData",
        "courseCategory",
        "createdDate"
})
public class Video {
    @JsonProperty("videoId")
    private Integer videoId;
    @JsonProperty("videoName")
    private String videoName;
    @JsonProperty("videoType")
    private String videoType;
    @JsonProperty("videoData")
    private String videoData;
    @JsonProperty("courseCategory")
    private CourseCategory courseCategory;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("videoId")
    public Integer getVideoId() {
        return videoId;
    }

    @JsonProperty("videoId")
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

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
    public String getVideoData() {
        return videoData;
    }

    @JsonProperty("videoData")
    public void setVideoData(String videoData) {
        this.videoData = videoData;
    }

    @JsonProperty("courseCategory")
    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    @JsonProperty("courseCategory")
    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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
