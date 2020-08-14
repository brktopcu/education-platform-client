package com.education.client.views.kurslar;

import com.education.client.data.*;
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
        SavedNewDocument document = new SavedNewDocument();

        Notification createdNotification = new Notification(
                "Kaydedildi",5000);
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

        MemoryBuffer memoryBuffer1 = new MemoryBuffer();
        Upload documentUpload = new Upload(memoryBuffer1);
        documentUpload.addSucceededListener(event1 -> {
            //FIXME get doc attributes
            document.setDocumentName("Test-doc");
            document.setDocumentType("application/pdf");
            document.setSection(currentSection);
            InputStream inputStream = memoryBuffer1.getInputStream();
            try {
                document.setData(inputStream.readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Label infoLabel = new Label();
        infoLabel.getStyle().set("font-weight","bold");

        Button save = new Button("Kaydet");
        Button reset = new Button("Sıfırla");

        layoutWithBinder.addFormItem(videoUpload,"Video yükle");
        layoutWithBinder.addFormItem(documentUpload,"Doküman yükle");

        HorizontalLayout actions = new HorizontalLayout();
        save.getStyle().set("cursor","pointer");
        save.getStyle().set("marginRight", "10px");
        reset.getStyle().set("cursor","pointer");
        actions.getStyle().set("margin-top", "25px");
        actions.getStyle().set("margin-left", "12%");
        actions.add(save, reset);

        save.addClickListener(event1 -> {

            if (video.getVideoData()!=null && document.getData()!=null) {
                restService.postVideo(video);
                restService.postDocument(document);

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
