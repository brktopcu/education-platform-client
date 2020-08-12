package com.education.client.views.kurslar;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.education.client.data.Course;
import com.education.client.services.RestService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.education.client.views.main.MainView;
import com.vaadin.flow.server.StreamResource;


@Route(value = "all-courses", layout = MainView.class)

@RouteAlias(value = "", layout = MainView.class)@PageTitle("Kurslar")
@CssImport(value = "styles/views/kurslar/kurslar-view.css", include = "lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class KurslarView extends Div implements AfterNavigationObserver {

    private final RestService restService;

    Grid<Course> grid = new Grid<>();

    public KurslarView(RestService restService) {
        this.restService = restService;
        setId("kurslar-view");
        addClassName("kurslar-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(course -> createCard(course));
        add(grid);
    }

    private HorizontalLayout createCard(Course course) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        Image image = new Image();
        StreamResource resource =
                new StreamResource("img", () -> new ByteArrayInputStream(course.getCoursePicture()));
        image.setSrc(resource);
        image.setHeight("60px");
        image.setWidth("165px");
        image.getStyle().set("border-radius", "0%");
        VerticalLayout description = new VerticalLayout();
        description.getStyle().set("margin-left","15px");
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(course.getCourseName());
        name.addClassName("name");
        try {
            Span date = new Span(String.valueOf(course.getCreatedDate()).substring(0,10));
            date.addClassName("date");
            header.add(name, date);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }



        Span post = new Span(course.getCourseDescription());
        post.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button courseDetailsButton = new Button("İncele", new Icon(VaadinIcon.PLAY));
        courseDetailsButton.getStyle().set("cursor","pointer");

        courseDetailsButton.addClickListener(event -> UI.getCurrent()
               .navigate(RouteConfiguration.forSessionScope()
                       .getUrl(CourseDetails.class, course.getCourseId())));

        buttonLayout.add(courseDetailsButton);

        description.add(header, post, actions);
        card.add(image, description, buttonLayout);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        List<Course> courses = restService.getAllCourses();

        grid.setItems(courses);
    }

}
