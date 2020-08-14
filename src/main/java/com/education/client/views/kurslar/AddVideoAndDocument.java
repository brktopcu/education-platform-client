package com.education.client.views.kurslar;

import com.education.client.data.Course;
import com.education.client.data.SavedNewSection;
import com.education.client.data.SavedNewVideo;
import com.education.client.data.Section;
import com.education.client.services.RestService;
import com.education.client.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

@Route(value = "new-video", layout = MainView.class)
@PageTitle("Yeni Bölüm Ekle")
@CssImport(value = "styles/views/kurslar/kurslar-view.css", include = "lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class AddVideoAndDocument extends Div implements HasUrlParameter<String> {
    private final RestService restService;

    @Autowired
    public AddVideoAndDocument(RestService restService) {
        this.restService = restService;
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {

        String[] parameters = parameter.split("-");
        String parameterFinal = new String();
        for(String s:parameters){
            parameterFinal+=s;
            parameterFinal+="/";
        }

        Section currentSection = restService.getSectionByNameAndDesc(parameterFinal);

        FormLayout layoutWithBinder = new FormLayout();

        SavedNewVideo video = new SavedNewVideo();

        Notification createdNotification = new Notification(
                "Video kaydedildi",5000);
        createdNotification.setPosition(Notification.Position.TOP_CENTER);

        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload videoUpload = new Upload(memoryBuffer);
        videoUpload.addSucceededListener(event1 -> {
            //FIXME get video attributes
            video.setVideoName("video");
            video.setVideoType("video/mp4");
            video.setVideoSection(currentSection);
            InputStream inputStream = memoryBuffer.getInputStream();
            try {
                video.setVideoData(inputStream.readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Label infoLabel = new Label();
        infoLabel.getStyle().set("font-weight","bold");

        Button save = new Button("Kaydet");
        Button reset = new Button("Sıfırla");

        layoutWithBinder.addFormItem(videoUpload,"Video yükle");

        HorizontalLayout actions = new HorizontalLayout();
        save.getStyle().set("cursor","pointer");
        save.getStyle().set("marginRight", "10px");
        reset.getStyle().set("cursor","pointer");
        actions.getStyle().set("margin-top", "25px");
        actions.getStyle().set("margin-left", "12%");
        actions.add(save, reset);

        save.addClickListener(event1 -> {

            if (video.getVideoData()!=null) {
                restService.postVideo(video);

                infoLabel.setText("Kaydedildi");
                createdNotification.open();
                UI.getCurrent().navigate(RouteConfiguration.forSessionScope()
                        .getUrl(CourseDetails.class, currentSection.getCourse().getCourseId()));
            } else {
                infoLabel.setText("Lütfen tüm alanları doldurun!");
            }
        });

        reset.addClickListener(event1 -> {
            infoLabel.setText("");
        });
        add(layoutWithBinder, actions, infoLabel);

    }
}
