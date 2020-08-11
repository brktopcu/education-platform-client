/*package com.education.client.components;

import com.education.client.data.Video;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.upload.Upload;

import java.io.ByteArrayOutputStream;

public class VideoUploadComponent extends CustomField<Video> {

    private Video value;

    private ByteArrayOutputStream outputStream;

    private com.vaadin.ui.Video currentAvatar;
    private Upload upload;

    public VideoUploadComponent(String label){
        this();

        //TODO setlabel

    }

    public VideoUploadComponent() {

        // <img> that shows the current avatar
        currentAvatar = new Image();
        currentAvatar.setAlt("avatar image");
        currentAvatar.setMaxHeight("100px");
        currentAvatar.getStyle().set("margin-right", "15px");
        currentAvatar.setVisible(false); // see updateImage()

        // create the upload component and delegate actions to the receiveUpload method
        upload = new Upload(this::receiveUpload);
        upload.getStyle().set("flex-grow", "1");

        // listen to state changes
        upload.addSucceededListener(e -> uploadSuccess(e));

        upload.addFailedListener(e -> setFailed(e.getReason().getMessage()));
        upload.addFileRejectedListener(e -> setFailed(e.getErrorMessage()));

        // only allow images to be uploaded
        upload.setAcceptedFileTypes("image/*");

        // only allow single file at a time
        upload.setMaxFiles(1);

        // set max file size to 1 MB
        upload.setMaxFileSize(1 * 1024 * 1024);

        // component layouting
        Div wrapper = new Div();
        wrapper.add(currentAvatar, upload);
        wrapper.getStyle().set("display", "flex");
        add(wrapper);
    }


    @Override
    protected Video generateModelValue() {
        return null;
    }

    @Override
    protected void setPresentationValue(Video newPresentationValue) {

    }
}
*/