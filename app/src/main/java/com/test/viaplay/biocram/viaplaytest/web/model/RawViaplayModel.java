
package com.test.viaplay.biocram.viaplaytest.web.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RawViaplayModel {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("_links")
    private RawLinksModel Links;
    @SerializedName("responseCode")
    private RawResponseCodeModel responseCode;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The RawLinksModel
     */
    public RawLinksModel getLinks() {
        return Links;
    }

    /**
     * @param Links The _links
     */
    public void setLinks(RawLinksModel Links) {
        this.Links = Links;
    }

    /**
     * @return The responseCode
     */
    public RawResponseCodeModel getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode The responseCode
     */
    public void setResponseCode(RawResponseCodeModel responseCode) {
        this.responseCode = responseCode;
    }

    public List<RawViaplaySectionModel> getSections() {
        return Links != null ? Links.getViaplaySections() : new ArrayList<RawViaplaySectionModel>();
    }

}
