package com.education.client.views.yenikursekle;

import com.education.client.data.SavedNewCourse;
import com.education.client.services.RestService;
import com.education.client.views.kurslar.AllCourses;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.education.client.views.main.MainView;
import com.vaadin.flow.router.RouteConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Collectors;


@Route(value = "new-course", layout = MainView.class)
@PageTitle("Yeni Kurs Ekle")
@CssImport("styles/views/yenikursekle/yeni-kurs-ekle-view.css")
public class YeniKursEkleView extends Div {


    private RestService service;


    public YeniKursEkleView(@Autowired RestService service) {

        this.service = service;

        FormLayout layoutWithBinder = new FormLayout();
        Binder<SavedNewCourse> binder = new Binder<>();

        SavedNewCourse courseBeingEdited = new SavedNewCourse();

        TextField courseNameField = new TextField("Kurs adı");
        courseNameField.setValueChangeMode(ValueChangeMode.EAGER);
        TextArea courseDescriptionField = new TextArea("Kurs tanımı");
        courseDescriptionField.setValueChangeMode(ValueChangeMode.EAGER);
        Label infoLabel = new Label();
        infoLabel.getStyle().set("font-weight","bold");
        Notification createdNotification = new Notification(
                "Kurs kaydedildi",5000);
        createdNotification.setPosition(Notification.Position.TOP_CENTER);

        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload courseImgUpload = new Upload(memoryBuffer);
        courseImgUpload.addSucceededListener(event -> {
            InputStream inputStream = memoryBuffer.getInputStream();

            try {
                courseBeingEdited.setCoursePicture(inputStream.readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        Button save = new Button("Kaydet");
        Button reset = new Button("Sıfırla");

        layoutWithBinder.addFormItem(courseNameField,courseNameField.getLabel());
        layoutWithBinder.addFormItem(courseDescriptionField,courseDescriptionField.getLabel());
        layoutWithBinder.addFormItem(courseImgUpload,"Resim yükle");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save, reset);
        save.getStyle().set("marginRight", "10px");
        actions.getStyle().set("margin-top", "25px");
        actions.getStyle().set("margin-left", "12%");

        courseNameField.setRequiredIndicatorVisible(true);
        courseDescriptionField.setRequiredIndicatorVisible(true);

        binder.forField(courseNameField)
                .withValidator(new StringLengthValidator(
                        "Lütfen kurs adını girin",1,null))
                .bind(SavedNewCourse::getCourseName, SavedNewCourse::setCourseName);

        binder.forField(courseDescriptionField)
                .withValidator(new StringLengthValidator(
                        "Lütfen kurs tanımını girin",1,null))
                .bind(SavedNewCourse::getCourseDescription,SavedNewCourse::setCourseDescription);

        save.addClickListener(event -> {
            if (binder.writeBeanIfValid(courseBeingEdited) && courseBeingEdited.getCoursePicture()!=null) {
                service.postCourse(courseBeingEdited);
                infoLabel.setText("Kaydedildi");
                createdNotification.open();
                UI.getCurrent().navigate(RouteConfiguration.forSessionScope()
                    .getUrl(AllCourses.class));
            } else {
                BinderValidationStatus<SavedNewCourse> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses()
                        .stream().filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(", "));
                infoLabel.setText("Lütfen tüm alanları doldurun!");
            }
        });

        reset.addClickListener(event -> {
            // clear fields by setting null
            binder.readBean(null);
            infoLabel.setText("");
        });
        add(layoutWithBinder, actions, infoLabel);
    }

}
