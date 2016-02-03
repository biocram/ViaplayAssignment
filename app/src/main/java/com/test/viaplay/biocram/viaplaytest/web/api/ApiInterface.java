package com.test.viaplay.biocram.viaplaytest.web.api;

import com.test.viaplay.biocram.viaplaytest.web.model.RawViaplayModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;


/**
 * Created by biocram on 02/02/16.
 */
public interface ApiInterface {

    @GET
    Call<RawViaplayModel> getSectionDetail(@Url String url);

    @GET("/androidv2-se")
    Call<RawViaplayModel> getAllSections();
}
