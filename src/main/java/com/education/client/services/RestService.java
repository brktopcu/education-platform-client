package com.education.client.services;

import com.education.client.data.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Mono;

import java.util.Base64;
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
    public List<Document> getDocumentsBySectionId(Long sectionId){
        final RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:8081/documents/"+sectionId);

        final List<Document> documents = spec.retrieve()
                .toEntityList(Document.class).block().getBody();

        return documents;
    }
    public void postCourse(SavedNewCourse course){
        Mono<String> result =  WebClient.create().post()
                .uri("http://localhost:8081/courses/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(course))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());
    }

    public void deleteCourse(Long courseId){
        Mono<String> result =  WebClient.create().delete()
                .uri("http://localhost:8081/courses/"+courseId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());
    }
    public void postSection(SavedNewSection section){
        Mono<String> result =  WebClient.create().post()
                .uri("http://localhost:8081/sections/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(section))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());
    }

    public void postVideo(SavedNewVideo video){
        Mono<String> result =  WebClient.create().post()
                .uri("http://localhost:8081/videos/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(video))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());
    }

    public void postDocument(SavedNewDocument document){
        Mono<String> result =  WebClient.create().post()
                .uri("http://localhost:8081/documents/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(document))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());
    }

    public Section getSectionByNameAndDesc(String encodedNameAndDesc){

        final RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:8081/sections/getSection/"+encodedNameAndDesc);

        final Section section = spec.retrieve()
                .toEntity(Section.class).block().getBody();

        return section;
    }

    public Progress getProgressByCourseId(Long courseId){

        final RequestHeadersSpec<?> spec = WebClient.create().get()
                .uri("http://localhost:8081/progresses/"+courseId);

        final Progress progress = spec.retrieve()
                .toEntity(Progress.class).block().getBody();

        return progress;
    }

    public void updateProgress(Long courseId, Progress progress){
        Mono<String> result =  WebClient.create().put()
                .uri("http://localhost:8081/progresses/update/"+courseId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(progress))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());

    }

    public void saveNewProgress(Progress progress){
        Mono<String> result =  WebClient.create().post()
                .uri("http://localhost:8081/progresses")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(progress))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());

    }
    public void updateVideoCheckbox(Long videoId){
        Mono<String> result =  WebClient.create().put()
                .uri("http://localhost:8081/videos/update/"+videoId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());

    }

    public void updateDocumentCheckbox(Long documentId){
        Mono<String> result =  WebClient.create().put()
                .uri("http://localhost:8081/documents/update/"+documentId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(result.block());

    }

}
