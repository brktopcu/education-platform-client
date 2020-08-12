package com.education.client.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "courseName",
        "courseDescription",
        "coursePicture"
})
public class SavedNewCourse {

    @JsonProperty("courseName")
    private String courseName;
    @JsonProperty("courseDescription")
    private String courseDescription;
    @JsonProperty("coursePicture")
    private byte[] coursePicture;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("courseName")
    public String getCourseName() {
        return courseName;
    }

    @JsonProperty("courseName")
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @JsonProperty("courseDescription")
    public String getCourseDescription() {
        return courseDescription;
    }

    @JsonProperty("courseDescription")
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @JsonProperty("coursePicture")
    public byte[] getCoursePicture() {
        return coursePicture;
    }

    @JsonProperty("coursePicture")
    public void setCoursePicture(byte[] coursePicture) {
        this.coursePicture = coursePicture;
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
