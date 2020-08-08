package com.education.client.data;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "courseId",
        "courseName",
        "courseDescription",
        "courseCategory",
        "coursePicture",
        "createdDate"
})
public class Course {
    @JsonProperty("courseId")
    private Long courseId;
    @JsonProperty("courseName")
    private String courseName;
    @JsonProperty("courseDescription")
    private String courseDescription;
    @JsonProperty("courseCategory")
    private CourseCategory courseCategory;
    @JsonProperty("coursePicture")
    private byte[] coursePicture;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("courseId")
    public Long getCourseId() {
        return courseId;
    }

    @JsonProperty("courseId")
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

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

    @JsonProperty("courseCategory")
    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    @JsonProperty("courseCategory")
    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    @JsonProperty("coursePicture")
    public byte[] getCoursePicture() {
        return coursePicture;
    }

    @JsonProperty("coursePicture")
    public void setCoursePicture(byte[] coursePicture) {
        this.coursePicture = coursePicture;
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
