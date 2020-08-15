package com.education.client.views.kurslar;

import com.education.client.components.HtmlVideo;
import com.education.client.data.Course;
import com.education.client.data.Document;
import com.education.client.data.Video;
import com.education.client.services.RestService;
import com.education.client.data.Section;
import com.education.client.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

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



        //Section Demo Container
        HorizontalLayout sectionsDemoContainer = new HorizontalLayout();
        sectionsDemoContainer.getStyle().set("margin-left","20px");
        VerticalLayout sectionsDemo = new VerticalLayout();
        sectionsDemo.getStyle().set("background-color","ghostwhite");
        Details sectionDemoDetails = new Details();
        sectionDemoDetails.setSummaryText("Tüm Bölümler");
        sections.forEach(section -> {
            Anchor anchor = new Anchor();
            anchor.getStyle().set("cursor","pointer");
            anchor.getStyle().set("color","var(--lumo-secondary-text-color)");
            anchor.setText(section.getSectionName());
            anchor.setHref("/courses/"+course.getCourseId()+"#"+section.getSectionId().toString());

            sectionsDemo.add(anchor);
        });
        sectionDemoDetails.addContent(sectionsDemo);
        sectionsDemoContainer.add(sectionDemoDetails);

       //Course Name Container
       HorizontalLayout courseNameContainer = new HorizontalLayout();
       courseNameContainer.getStyle().set("margin-left", "50px");
       courseNameContainer.getStyle().set("margin-top", "50px");
       Icon capIcon = new Icon(VaadinIcon.ACADEMY_CAP);
       capIcon.setSize("50px");
       courseNameContainer.add(capIcon);
       courseNameContainer.getStyle().set("margin-top", "1.25em");
       H3 courseName = new H3(course.getCourseName());
       courseName.getStyle().set("margin-top","0.25em");
       Button newSectionButton = new Button("Yeni Bölüm Ekle", new Icon(VaadinIcon.PLUS_CIRCLE));
       newSectionButton.getStyle().set("cursor","pointer");
       newSectionButton.addClickListener(event1 -> {
           UI.getCurrent().navigate(RouteConfiguration.forSessionScope()
                   .getUrl(AddNewSection.class,course.getCourseId()));
       });
       Button deleteCourseButton = new Button("Kursu sil", new Icon(VaadinIcon.CLOSE_CIRCLE));
       deleteCourseButton.getStyle().set("cursor","pointer");
       deleteCourseButton.getStyle().set("color","red");
       Notification deletedNotification = new Notification(
               "Kurs silindi",5000);
        deletedNotification.setPosition(Notification.Position.TOP_CENTER);
       deleteCourseButton.addClickListener(event1 -> {
        restService.deleteCourse(course.getCourseId());
        deletedNotification.open();
        UI.getCurrent().navigate(RouteConfiguration.forSessionScope()
                   .getUrl(AllCourses.class));
       });
       HorizontalLayout buttonLayout = new HorizontalLayout();
       buttonLayout.add(newSectionButton,deleteCourseButton);
       buttonLayout.getStyle().set("margin-left","30px");
       courseNameContainer.add(courseName,buttonLayout);


       //Section Container
       VerticalLayout sectionContainer = new VerticalLayout();
        sections.forEach(section ->{
            List<Document> documents = restService.getDocumentsBySectionId(section.getSectionId());
            List<Video> videos = restService.getVideosBySectionId(section.getSectionId());
            float count = (float)documents.size()+(float)videos.size();

            H1 sectionAnchor = new H1();
            sectionAnchor.setId(section.getSectionId().toString());
            VerticalLayout sectionDetails = new VerticalLayout();
            VerticalLayout videoLayout = new VerticalLayout();
            HorizontalLayout videoCheckboxLayout = new HorizontalLayout();

            videos.forEach(video -> {
                Icon newItemIcon = new Icon(VaadinIcon.ANGLE_DOWN);
                newItemIcon.setSize("30px");
                byte[] videoData = video.getVideoData();
                String encodedVideoData = Base64.getEncoder().encodeToString(videoData);
                String dataUrl = "data:video/mp4;base64,"+encodedVideoData;
                HtmlVideo htmlVideo = new HtmlVideo(dataUrl,"video/mp4");
                Checkbox videoCheckbox = new Checkbox();
                videoCheckbox.addValueChangeListener(event1 -> {
                    float addOrRemove = 1/count;
                    //TODO rest call to add to progress bar
                });
                videoCheckboxLayout.add(htmlVideo,videoCheckbox);
                videoLayout.add(newItemIcon,videoCheckboxLayout);
            });


            HorizontalLayout documentLayout = new HorizontalLayout();
            documentLayout.getStyle().set("margin-top","50px");
            documents.forEach(document -> {
                Icon fileIcon = new Icon(VaadinIcon.FILE);
                fileIcon.setSize("30px");
                Icon newItemIcon = new Icon(VaadinIcon.ANGLE_RIGHT);
                newItemIcon.setSize("30px");

                byte[] docData = document.getData();
                String encodedDocData = Base64.getEncoder().encodeToString(docData);
                String docDataUrl = "data:application/pdf;base64," + encodedDocData;
                Anchor a = new Anchor(docDataUrl,document.getDocumentName());
                a.setTarget("_blank");
                a.getStyle().set("color","hsl(214deg 47% 47%)");
                Checkbox documentCheckbox = new Checkbox();
                documentCheckbox.addValueChangeListener(event1 -> {
                   float addOrRemove = 1/count;
                    //TODO rest call to add to progress bar
                });
                documentLayout.add(newItemIcon,fileIcon,a,documentCheckbox);
            });
            HorizontalLayout sectionName = new HorizontalLayout();
            Icon sectionIcon = new Icon(VaadinIcon.PLAY);
            sectionIcon.setSize("30px");
            H4 h2SectionName = new H4(section.getSectionName());
            h2SectionName.getStyle().set("margin-top", "0.25em");
            sectionName.add(sectionIcon, h2SectionName,sectionAnchor);

            VerticalLayout sectionDescription = new VerticalLayout();
            Span sectionDescriptionSpan = new Span(section.getSectionDescription());
            sectionDescription.add(sectionDescriptionSpan);

            sectionDetails.add(sectionName,sectionDescription, videoLayout,documentLayout);
            sectionDetails.getStyle().set("background-color", "var(--lumo-contrast-10pct)");
            sectionContainer.add(sectionDetails);
        });


        add(courseNameContainer,sectionsDemoContainer,sectionContainer);
    }
}
