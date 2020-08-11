package com.education.client.views.kurslar;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.dom.Element;

@Tag("video")
public class HtmlVideo extends Component {

    Element sourceElement = new Element("source");

    public HtmlVideo(String src, String type){
        getElement().setProperty("controls"," ");
        getElement().setProperty("width","600");
        getElement().setProperty("height","300");
        sourceElement.setProperty("src",src);
        sourceElement.setProperty("type",type + ";codecs=\"avc1.42E01E, mp4a.40.2\"");

        getElement().appendChild(sourceElement);
    }
}
