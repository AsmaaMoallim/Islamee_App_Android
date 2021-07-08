package com.alamat.besmellah.Radio;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static Retrofit retrofit;

    public static Retrofit getInstance() {

        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NotNull String s) {

                }
            });

            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://mp3quran.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }


        return retrofit;
    }


    public static APICalls getAPIS() {
        return getInstance().create(APICalls.class);
    }
}
