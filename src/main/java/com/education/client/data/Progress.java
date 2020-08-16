package com.education.client.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "progressId",
        "progress",
        "course"
})
public class Progress {
    @JsonProperty("progressId")
    private Long progressId;
    @JsonProperty("progress")
    private float progress;
    @JsonProperty("course")
    private Course course;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("progressId")
    public Long getProgressId() {
        return progressId;
    }

    @JsonProperty("progressId")
    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    @JsonProperty("progress")
    public float getProgress() {
        return progress;
    }

    @JsonProperty("progress")
    public void setProgress(float progress) {
        this.progress = progress;
    }

    @JsonProperty("course")
    public Course getCourse() {
        return course;
    }

    @JsonProperty("course")
    public void setCourse(Course course) {
        this.course = course;
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
