package com.test.viaplay.biocram.viaplaytest.data;

import android.util.Log;

import com.test.viaplay.biocram.viaplaytest.data.model.SectionDetailModel;
import com.test.viaplay.biocram.viaplaytest.data.model.SectionModel;
import com.test.viaplay.biocram.viaplaytest.utils.CommonUtil;

import java.util.List;

/**
 * Created by biocram on 03/02/16.
 */
public class DatabaseManager {

    private static final String TAG = DatabaseManager.class.getSimpleName();

    private static DatabaseManager sInstance;

    private DatabaseManager() {
    }

    public synchronized static DatabaseManager getInstance() {

        if (sInstance == null) sInstance = new DatabaseManager();
        return sInstance;
    }

    public void saveDataToDb(List<SectionModel> sections) {

        Log.d(TAG, "Saving top sections into db");

        if (!CommonUtil.isEmpty(sections))
            for (SectionModel model : sections)
                model.save();
    }

    public void saveDataToDb(SectionDetailModel sectionDetailModel) {

        Log.d(TAG, "Saving section detail into db");

        sectionDetailModel.save();
    }

    public List<SectionModel> getAllSectionsFromDb() {

        Log.d(TAG, "Retrieving data from db");

        return SectionModel.listAll(SectionModel.class);
    }

    public SectionDetailModel getSectionDetailFromDb(SectionModel section) {

        Log.d(TAG, "Retrieving data from db");

        List<SectionDetailModel> details = SectionDetailModel.find(SectionDetailModel.class, "m_Section = ?", Long.toString(section.getId()));
        if (!CommonUtil.isEmpty(details)) {
            return details.get(0);
        }
        return null;
    }


}
