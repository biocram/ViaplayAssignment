
package com.test.viaplay.biocram.viaplaytest.web.model;

import com.google.gson.annotations.SerializedName;

public class ViaplayModel {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("_links")
    private Links Links;
    @SerializedName("responseCode")
    private ResponseCode responseCode;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The Links
     */
    public com.test.viaplay.biocram.viaplaytest.web.model.Links getLinks() {
        return Links;
    }

    /**
     * 
     * @param Links
     *     The _links
     */
    public void setLinks(com.test.viaplay.biocram.viaplaytest.web.model.Links Links) {
        this.Links = Links;
    }

    /**
     * 
     * @return
     *     The responseCode
     */
    public ResponseCode getResponseCode() {
        return responseCode;
    }

    /**
     * 
     * @param responseCode
     *     The responseCode
     */
    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

}
