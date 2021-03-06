package com.alamat.besmellah.Radio;

import com.google.gson.annotations.SerializedName;

public class DataModel {
    @SerializedName("Name")
    String name;


    @SerializedName("URL")
    String url;

    public DataModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
