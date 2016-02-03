package com.test.viaplay.biocram.viaplaytest.data.model;

import com.orm.SugarRecord;
import com.test.viaplay.biocram.viaplaytest.web.model.RawViaplaySectionModel;

/**
 * Created by biocram on 03/02/16.
 */
public class SectionModel extends SugarRecord {

    private String mName;
    private String mUrl;

    public SectionModel() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public static SectionModel generateFrom(RawViaplaySectionModel rawSection) {
        SectionModel model = new SectionModel();
        model.setName(rawSection.getTitle());
        model.setUrl(rawSection.getHref());
        return model;
    }
}
