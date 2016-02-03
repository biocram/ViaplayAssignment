
package com.test.viaplay.biocram.viaplaytest.web.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RawLinksModel {

    @SerializedName("viaplay:sections")
    private List<RawViaplaySectionModel> viaplaySections = new ArrayList<RawViaplaySectionModel>();

    /**
     * 
     * @return
     *     The viaplaySections
     */
    public List<RawViaplaySectionModel> getViaplaySections() {
        return viaplaySections;
    }

    /**
     * 
     * @param viaplaySections
     *     The viaplay:sections
     */
    public void setViaplaySections(List<RawViaplaySectionModel> viaplaySections) {
        this.viaplaySections = viaplaySections;
    }

}
