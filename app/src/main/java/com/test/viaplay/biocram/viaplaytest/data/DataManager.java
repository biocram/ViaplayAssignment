package com.test.viaplay.biocram.viaplaytest.data;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.test.viaplay.biocram.viaplaytest.data.model.SectionDetailModel;
import com.test.viaplay.biocram.viaplaytest.data.model.SectionModel;
import com.test.viaplay.biocram.viaplaytest.utils.CommonUtil;
import com.test.viaplay.biocram.viaplaytest.web.api.ApiInterface;
import com.test.viaplay.biocram.viaplaytest.web.model.RawViaplayModel;
import com.test.viaplay.biocram.viaplaytest.web.model.RawViaplaySectionModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by biocram on 02/02/16.
 */
public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private static final String BASE_URL = "https://content.viaplay.se";

    private static DataManager sInstance;

    private ApiInterface mApiInterface;

    private List<SectionModel> mTopSections;
    private Map<String, SectionDetailModel> mDetailSections;

    public interface OnAllSectionsReadyListener {

        void onAllSectionsReady(List<SectionModel> sections);
    }

    public interface OnSectionDetailReadyListener {

        void onSectionDetailReady(SectionDetailModel sectionDetail);
    }


    private DataManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiInterface = retrofit.create(ApiInterface.class);
    }

    public synchronized static DataManager getInstance() {
        if (sInstance == null) sInstance = new DataManager();
        return sInstance;
    }

    @WorkerThread
    public void getAllSections(Context context, OnAllSectionsReadyListener listener) {

        if (CommonUtil.isEmpty(mTopSections)) {

            if (CommonUtil.checkInternetConnection(context)) {

                // try getting data from web

                RawViaplayModel model = null;

                try {

                    model = getAllSectionsFromWeb();
                    initCache(model);
                    populateTopSectionsCache(model);
                    returnAllSectionsToListener(listener);
                    DatabaseManager.getInstance().saveDataToDb(mTopSections);

                } catch (IOException e) {
                    Log.e(TAG, "Error while retrieving data from web");
                }

            } else {
                // try getting data from db

                List<SectionModel> models = DatabaseManager.getInstance().getAllSectionsFromDb();
                initCache(models);
                populateTopSectionsCache(models);
                returnAllSectionsToListener(listener);
            }


        } else {

            Log.d(TAG, "Retrieving data from cache");
            returnAllSectionsToListener(listener);
        }
    }

    @WorkerThread
    public void getSpecificSection(Context context, int sectionPos, OnSectionDetailReadyListener listener) {

        SectionModel section = mTopSections.get(sectionPos);

        String sectionName = section.getName();

        if (CommonUtil.isEmpty(mDetailSections) || !mDetailSections.containsKey(sectionName)) {

            if (CommonUtil.checkInternetConnection(context)) {

                // try downloading data from web

                RawViaplayModel detail = null;

                try {

                    detail = getSectionFromWeb(section.getUrl());

                    populateSectionDetailCache(section, detail);

                    returnSectionDetailToListener(sectionName, listener);

                    DatabaseManager.getInstance().saveDataToDb(SectionDetailModel.generateFrom(detail, section));

                } catch (IOException e) {
                    Log.e(TAG, "Error while downloading section");
                }
            } else {

                // try retrieving data from db
                SectionDetailModel detail = DatabaseManager.getInstance().getSectionDetailFromDb(section);
                populateSectionDetailCache(section, detail);
                returnSectionDetailToListener(sectionName, listener);
            }


        } else {

            Log.d(TAG, "Retrieving data from cache");
            returnSectionDetailToListener(sectionName, listener);
        }
    }


    private void populateTopSectionsCache(RawViaplayModel rawModel) {

        for (RawViaplaySectionModel rawSection : rawModel.getSections())
            mTopSections.add(SectionModel.generateFrom(rawSection));
    }

    private void populateTopSectionsCache(List<SectionModel> sections) {

        if (!CommonUtil.isEmpty(sections))
            mTopSections.addAll(sections);
    }

    private void populateSectionDetailCache(SectionModel section, RawViaplayModel rawModel) {

        mDetailSections.put(section.getName(), SectionDetailModel.generateFrom(rawModel, section));
    }

    private void populateSectionDetailCache(SectionModel section, SectionDetailModel detail) {

        if (detail != null)
            mDetailSections.put(section.getName(), detail);
    }

    private void initCache(RawViaplayModel model) {

        if (model != null && !CommonUtil.isEmpty(model.getSections())) {
            int size = model.getSections().size();
            mTopSections = new ArrayList<>(size);
            mDetailSections = new HashMap<>(size);
        }
    }

    private void initCache(List<SectionModel> sections) {

        if (!CommonUtil.isEmpty(sections)) {
            int size = sections.size();
            mTopSections = new ArrayList<>(size);
            mDetailSections = new HashMap<>(size);
        }
    }

    private RawViaplayModel getAllSectionsFromWeb() throws IOException {

        Log.d(TAG, "Retrieving data from web api");

        RawViaplayModel model = null;

        Call<RawViaplayModel> call = mApiInterface.getAllSections();

        Response<RawViaplayModel> response = call.execute();

        if (response != null && response.body() != null
                && !CommonUtil.isEmpty(response.body().getSections())) {

            model = response.body();
        }

        return model;
    }

    private RawViaplayModel getSectionFromWeb(String url) throws IOException {

        Log.d(TAG, "Retrieving section data from web api");

        Call<RawViaplayModel> call = mApiInterface.getSectionDetail(getUrlParam(url));
        Response<RawViaplayModel> response = call.execute();

        if (response != null) return response.body();

        return null;
    }


    private String getUrlParam(String url) {

        url = url.replace("{?dtg}", "");

        return url;
    }

    private void returnAllSectionsToListener(OnAllSectionsReadyListener listener) {
        if (listener != null && !CommonUtil.isEmpty(mTopSections))
            listener.onAllSectionsReady(Collections.unmodifiableList(mTopSections));
    }

    private void returnSectionDetailToListener(String section, OnSectionDetailReadyListener listener) {
        if (listener != null && mDetailSections.containsKey(section))
            listener.onSectionDetailReady(mDetailSections.get(section));
    }
}
