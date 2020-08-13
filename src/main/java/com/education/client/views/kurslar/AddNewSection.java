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
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Collectors;

@Route(value = "new-section", layout = MainView.class)
@PageTitle("Yeni Bölüm Ekle")
@CssImport(value = "styles/views/kurslar/kurslar-view.css", include = "lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class AddNewSection extends Div implements HasUrlParameter<Long> {

    private final RestService restService;

    @Autowired
    public AddNewSection(RestService restService){
        this.restService=restService;
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Long parameter) {
        Course affectedCourse = restService.getCourseById(parameter);

        FormLayout layoutWithBinder = new FormLayout();
        Binder<SavedNewSection> binder = new Binder<>();

        SavedNewSection sectionBeingEdited = new SavedNewSection();
        Section currentSection;
        sectionBeingEdited.setCourse(affectedCourse);

        SavedNewVideo savedNewVideo = new SavedNewVideo();

        TextField sectionNameField = new TextField("Bölüm adı");
        sectionNameField.setValueChangeMode(ValueChangeMode.EAGER);
        TextArea sectionDescriptionField = new TextArea("Bölüm tanımı");
        sectionDescriptionField.setValueChangeMode(ValueChangeMode.EAGER);
        Label infoLabel = new Label();
        infoLabel.getStyle().set("font-weight","bold");
        Notification createdNotification = new Notification(
                "Bölüm kaydedildi",5000);
        createdNotification.setPosition(Notification.Position.TOP_CENTER);

        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload videoUpload = new Upload(memoryBuffer);
        videoUpload.addSucceededListener(event1 -> {
            //FIXME get video attributes
            savedNewVideo.setVideoName("video");
            savedNewVideo.setVideoType("video/mp4");
            InputStream inputStream = memoryBuffer.getInputStream();
            try {
                savedNewVideo.setVideoData(inputStream.readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button save = new Button("Kaydet");
        Button reset = new Button("Sıfırla");

        layoutWithBinder.addFormItem(sectionNameField,sectionNameField.getLabel());
        layoutWithBinder.addFormItem(sectionDescriptionField,sectionDescriptionField.getLabel());
        layoutWithBinder.addFormItem(videoUpload,"Video yükle");

        HorizontalLayout actions = new HorizontalLayout();
        save.getStyle().set("cursor","pointer");
        save.getStyle().set("marginRight", "10px");
        reset.getStyle().set("cursor","pointer");
        actions.getStyle().set("margin-top", "25px");
        actions.getStyle().set("margin-left", "12%");
        actions.add(save, reset);

        sectionNameField.setRequiredIndicatorVisible(true);
        sectionDescriptionField.setRequiredIndicatorVisible(true);

        binder.forField(sectionNameField)
                .withValidator(new StringLengthValidator(
                        "Lütfen bölüm adını girin",1,null))
                .bind(SavedNewSection::getSectionName, SavedNewSection::setSectionName);

        binder.forField(sectionDescriptionField)
                .withValidator(new StringLengthValidator(
                        "Lütfen bölüm tanımını girin",1,null))
                .bind(SavedNewSection::getSectionDescription,SavedNewSection::setSectionDescription);

        save.addClickListener(event1 -> {
            if (binder.writeBeanIfValid(sectionBeingEdited) && savedNewVideo.getVideoData()!=null ) {
                restService.postSection(sectionBeingEdited);
                //TODO rest call to get section
                //TODO set savedNewVideo's section
                //TODO post video with section
                infoLabel.setText("Kaydedildi");
                createdNotification.open();
                UI.getCurrent().navigate(RouteConfiguration.forSessionScope()
                        .getUrl(CourseDetails.class, affectedCourse.getCourseId()));
            } else {
                infoLabel.setText("Lütfen tüm alanları doldurun!");
            }
        });

        reset.addClickListener(event1 -> {
            // clear fields by setting null
            binder.readBean(null);
            infoLabel.setText("");
        });
        add(layoutWithBinder, actions, infoLabel);
    }
}
