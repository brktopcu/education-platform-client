package com.education.client.views.yenikursekle;

import com.education.client.data.entity.Person;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.education.client.views.main.MainView;


@Route(value = "new-course", layout = MainView.class)
@PageTitle("Yeni Kurs Ekle")
@CssImport("styles/views/yenikursekle/yeni-kurs-ekle-view.css")
public class YeniKursEkleView extends Div {

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField email = new TextField();

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    public YeniKursEkleView() {
        setId("yeni-kurs-ekle-view");
        VerticalLayout wrapper = createWrapper();

        createTitle(wrapper);
        createFormLayout(wrapper);
        createButtonLayout(wrapper);

        // Configure Form
        Binder<Person> binder = new Binder<>(Person.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> binder.readBean(null));
        save.addClickListener(e -> {
            Notification.show("Not implemented");
        });

        add(wrapper);
    }

    private void createTitle(VerticalLayout wrapper) {
        H1 h1 = new H1("Form");
        wrapper.add(h1);
    }

    private VerticalLayout createWrapper() {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setId("wrapper");
        wrapper.setSpacing(false);
        return wrapper;
    }

    private void createFormLayout(VerticalLayout wrapper) {
        FormLayout formLayout = new FormLayout();
        addFormItem(wrapper, formLayout, firstName, "First name");
        addFormItem(wrapper, formLayout, lastName, "Last name");
        FormLayout.FormItem emailFormItem = addFormItem(wrapper, formLayout,
                email, "Email");
        formLayout.setColspan(emailFormItem, 2);
    }

    private void createButtonLayout(VerticalLayout wrapper) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(cancel);
        buttonLayout.add(save);
        wrapper.add(buttonLayout);
    }

    private FormLayout.FormItem addFormItem(VerticalLayout wrapper,
            FormLayout formLayout, Component field, String fieldName) {
        FormLayout.FormItem formItem = formLayout.addFormItem(field, fieldName);
        wrapper.add(formLayout);
        field.getElement().getClassList().add("full-width");
        return formItem;
    }

}
