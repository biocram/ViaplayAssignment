
package com.test.viaplay.biocram.viaplaytest.web.model;

import com.google.gson.annotations.SerializedName;

public class RawViaplaySectionModel {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("href")
    private String href;
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("templated")
    private Boolean templated;
    @SerializedName("active")
    private Boolean active;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * @return The href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The templated
     */
    public Boolean getTemplated() {
        return templated;
    }

    /**
     * @param templated The templated
     */
    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }

    /**
     * @return The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

}
