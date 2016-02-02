
package com.test.viaplay.biocram.viaplaytest.web.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Links {

    @SerializedName("viaplay:sections")
    private List<ViaplaySection> viaplaySections = new ArrayList<ViaplaySection>();

    /**
     * 
     * @return
     *     The viaplaySections
     */
    public List<ViaplaySection> getViaplaySections() {
        return viaplaySections;
    }

    /**
     * 
     * @param viaplaySections
     *     The viaplay:sections
     */
    public void setViaplaySections(List<ViaplaySection> viaplaySections) {
        this.viaplaySections = viaplaySections;
    }

}
