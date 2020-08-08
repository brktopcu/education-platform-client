package com.education.client.views.kurslar;

import com.education.client.views.main.MainView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "courses/{courseId}", layout = MainView.class)
@PageTitle("Kurs Detayları")
@CssImport(value = "styles/views/kurslar/kurslar-view.css", include = "lumo-badge")
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class CourseDetails extends Div implements AfterNavigationObserver {


    @Override
    public void afterNavigation(AfterNavigationEvent event) {

    }
}
