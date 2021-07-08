package com.alamat.besmellah.Radio;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APICalls {
    @GET("api/radio/radio_ar.json")
    Call<ResponseModel> getAllSources();
}
