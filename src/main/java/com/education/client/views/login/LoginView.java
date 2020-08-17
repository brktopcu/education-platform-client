package com.education.client.views.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();

    public LoginView(){
        Image logo = new Image();
        logo.setSrc("/images/logos/iLearn-logo.png");
        login.setI18n(createLoginI18n());
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(logo,login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    private LoginI18n createLoginI18n(){
       final LoginI18n i18n = LoginI18n.createDefault();


        i18n.setHeader(new LoginI18n.Header());

        i18n.getHeader().setTitle("Giriş yap");
        i18n.getHeader().setDescription("Lütfen giriş yapın");
        i18n.getForm().setUsername("Kullanıcı Adı");
        i18n.getForm().setTitle(" ");
        i18n.getForm().setSubmit("Giriş");
        i18n.getForm().setPassword("Şifre");
        i18n.getForm().setForgotPassword("Şifremi Unuttum");
        i18n.getErrorMessage().setTitle("Kullanıcı adı/şifre hatalı");
        i18n.getErrorMessage()
                .setMessage("Lütfen geçerli bir kullanıcı adı/şifre girin.");

        return i18n;
    }
}
