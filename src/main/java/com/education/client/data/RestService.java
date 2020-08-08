package com.education.client.data;

import com.education.client.data.Course;
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
}
