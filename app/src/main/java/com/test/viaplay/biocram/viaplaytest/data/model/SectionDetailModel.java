package com.test.viaplay.biocram.viaplaytest.data.model;

import com.orm.SugarRecord;
import com.test.viaplay.biocram.viaplaytest.web.model.RawViaplayModel;

/**
 * Created by biocram on 03/02/16.
 */
public class SectionDetailModel extends SugarRecord {

    private String mTitle;
    private String mDescription;

    private SectionModel mSection;


    public SectionDetailModel() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setSection(SectionModel section) {
        this.mSection = section;
    }

    public static SectionDetailModel generateFrom(RawViaplayModel rawModel, SectionModel section) {
        SectionDetailModel model = new SectionDetailModel();
        model.setTitle(rawModel.getTitle());
        model.setDescription(rawModel.getDescription());
        model.setSection(section);
        return model;
    }
}
