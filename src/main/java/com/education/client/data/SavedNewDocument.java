package com.education.client.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "documentName",
        "documentType",
        "data",
        "section"
})
public class SavedNewDocument {
    @JsonProperty("documentName")
    private String documentName;
    @JsonProperty("documentType")
    private String documentType;
    @JsonProperty("data")
    private byte[] data;
    @JsonProperty("section")
    private Section section;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("documentName")
    public String getDocumentName() {
        return documentName;
    }

    @JsonProperty("documentName")
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @JsonProperty("documentType")
    public String getDocumentType() {
        return documentType;
    }

    @JsonProperty("documentType")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @JsonProperty("data")
    public byte[] getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(byte[] data) {
        this.data = data;
    }

    @JsonProperty("section")
    public Section getSection() {
        return section;
    }

    @JsonProperty("section")
    public void setSection(Section section) {
        this.section = section;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
