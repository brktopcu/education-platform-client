package com.education.client.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sectionId",
        "sectionName",
        "sectionDescription",
        "course"
})
public class Section {

    @JsonProperty("sectionId")
    private Long sectionId;
    @JsonProperty("sectionName")
    private String sectionName;
    @JsonProperty("sectionDescription")
    private String sectionDescription;
    @JsonProperty("course")
    private Course course;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sectionId")
    public Long getSectionId() {
        return sectionId;
    }

    @JsonProperty("sectionId")
    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    @JsonProperty("sectionName")
    public String getSectionName() {
        return sectionName;
    }

    @JsonProperty("sectionName")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @JsonProperty("sectionDescription")
    public String getSectionDescription() {
        return sectionDescription;
    }

    @JsonProperty("sectionDescription")
    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
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
