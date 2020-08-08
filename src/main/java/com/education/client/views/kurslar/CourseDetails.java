package com.education.client.views.kurslar;

import com.education.client.data.Course;
import com.education.client.data.RestService;
import com.education.client.data.Section;
import com.education.client.views.main.MainView;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.util.List;

@Route(value = "courses", layout = MainView.class)
@PageTitle("Kurs Detayları")
@CssImport(value = "styles/views/kurslar/kurslar-view.css", include = "lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class CourseDetails extends Div implements HasUrlParameter<Long> {

    private final RestService restService;

    public CourseDetails(RestService restService) {
        this.restService = restService;
    }


    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Long parameter) {

        Course course = restService.getCourseById(parameter);
        List<Section> sections = restService.getSectionsByCourseId(parameter);

       HorizontalLayout topPanel = new HorizontalLayout();
       Image image = new Image();
       StreamResource resource =
       new StreamResource("img", () -> new ByteArrayInputStream(course.getCoursePicture()));
       image.setSrc(resource);
       topPanel.add(image);
       add(topPanel);

        HorizontalLayout sectionDetails = new HorizontalLayout();
        sections.forEach(section ->{
            H1 sectionName = new H1(section.getSectionName());
            sectionDetails.add(sectionName);
        });
        add(sectionDetails);
    }
}
