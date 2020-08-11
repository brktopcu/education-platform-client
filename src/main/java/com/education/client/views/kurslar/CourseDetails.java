package com.education.client.views.kurslar;

import com.education.client.data.Course;
import com.education.client.data.Document;
import com.education.client.data.Video;
import com.education.client.services.RestService;
import com.education.client.data.Section;
import com.education.client.views.main.MainView;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.server.Resource;
import org.springframework.core.io.ByteArrayResource;

import java.io.*;
import java.util.Base64;
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


        //Top Panel
       VerticalLayout topPanel = new VerticalLayout();
       topPanel.setWidth("100%");
       topPanel.addClassName("card");
       topPanel.setSpacing(false);
       topPanel.getThemeList().add("spacing-s");
       Image image = new Image();
       StreamResource resource =
       new StreamResource("img", () -> new ByteArrayInputStream(course.getCoursePicture()));
       image.setSrc(resource);
       image.setHeight("100px");
       topPanel.add(image);

       //Course Name Container
       HorizontalLayout courseNameContainer = new HorizontalLayout();
       courseNameContainer.getStyle().set("margin-left", "50px");
       courseNameContainer.getStyle().set("margin-top", "50px");
       Icon capIcon = new Icon(VaadinIcon.ACADEMY_CAP);
       capIcon.setSize("50px");
       courseNameContainer.add(capIcon);
       courseNameContainer.add(new H3(course.getCourseName()));

       //Section Container
       VerticalLayout sectionContainer = new VerticalLayout();



        sections.forEach(section ->{
            VerticalLayout sectionDetails = new VerticalLayout();
            VerticalLayout videoLayout = new VerticalLayout();
            List<Video> videos = restService.getVideosBySectionId(section.getSectionId());
            videos.forEach(video -> {
                Icon newItemIcon = new Icon(VaadinIcon.CHEVRON_RIGHT);
                newItemIcon.setSize("30px");
                byte[] videoData = video.getVideoData();
                String encodedVideoData = Base64.getEncoder().encodeToString(videoData);
                String dataUrl = "data:video/mp4;base64,"+encodedVideoData;
                HtmlVideo htmlVideo = new HtmlVideo(dataUrl,"video/mp4");
                videoLayout.add(newItemIcon,htmlVideo);
            });

            List<Document> documents = restService.getDocumentsBySectionId(section.getSectionId());
            HorizontalLayout documentLayout = new HorizontalLayout();
            documentLayout.getStyle().set("margin-top","50px");
            documents.forEach(document -> {
                Icon fileIcon = new Icon(VaadinIcon.FILE);
                fileIcon.setSize("50px");
                Icon newItemIcon = new Icon(VaadinIcon.CHEVRON_RIGHT);
                newItemIcon.setSize("30px");

                byte[] docData = document.getData();
                String encodedDocData = Base64.getEncoder().encodeToString(docData);
                String docDataUrl = "data:application/pdf;base64," + encodedDocData;
                Anchor a = new Anchor(docDataUrl,document.getDocumentName());
                a.setTarget("_blank");
                documentLayout.add(newItemIcon,fileIcon,a);
            });
            VerticalLayout sectionDescription = new VerticalLayout();
            H4 h2SectionName = new H4(section.getSectionName());
            sectionDescription.add(h2SectionName);
            Span sectionDescriptionSpan = new Span(section.getSectionDescription());
            sectionDescription.add(sectionDescriptionSpan);

            sectionDetails.add(sectionDescription, videoLayout,documentLayout);
            sectionDetails.getStyle().set("background-color", "var(--lumo-contrast-10pct)");
            sectionContainer.add(sectionDetails);
        });


        add(courseNameContainer,sectionContainer);
    }
}
