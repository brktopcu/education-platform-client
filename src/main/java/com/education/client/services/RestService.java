package com.education.client.services;

import com.education.client.data.Course;
import com.education.client.data.Section;
import com.education.client.data.Video;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import java.util.List;

@Service
public class RestService {

    public List<Course> getAllCourses(){
        final RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:8081/courses/all");

        final List<Course> courses = spec.retrieve()
                .toEntityList(Course.class).block().getBody();

        return courses;

    }
    public Course getCourseById(Long courseId){
        final RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:8081/courses/"+courseId);

        final Course course = spec.retrieve()
                .toEntity(Course.class).block().getBody();

        return course;
    }
    public List<Section> getSectionsByCourseId(Long courseId){
        final RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:8081/sections/"+courseId);

        final List<Section> sections = spec.retrieve()
                .toEntityList(Section.class).block().getBody();


        return sections;
    }

    public List<Video> getVideosBySectionId(Long sectionId){

        final RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:8081/videos/"+sectionId);

        final List<Video> videos = spec.retrieve()
                .toEntityList(Video.class).block().getBody();

        return videos;
    }
}
