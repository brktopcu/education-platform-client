package com.education.client.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "courseCategoryId",
        "courseCategoryName"
})
public class CourseCategory {
    @JsonProperty("courseCategoryId")
    private Integer courseCategoryId;
    @JsonProperty("courseCategoryName")
    private String courseCategoryName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("courseCategoryId")
    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    @JsonProperty("courseCategoryId")
    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    @JsonProperty("courseCategoryName")
    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    @JsonProperty("courseCategoryName")
    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
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
