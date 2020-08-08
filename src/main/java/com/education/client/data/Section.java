package com.education.client.data;

public class Section {

    private Long sectionId;

    private String sectionName;

    private String sectionDescription;

    private Course course;

    public Section(Long sectionId, String sectionName, String sectionDescription, Course course) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.sectionDescription = sectionDescription;
        this.course = course;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
