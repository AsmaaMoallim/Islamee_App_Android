package com.alamat.besmellah.Radio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("Radios")
    List<DataModel> Radios;

    public ResponseModel(List<DataModel> radios) {
        Radios = radios;
    }

    public List<DataModel> getRadios() {
        return Radios;
    }

    public void setRadios(List<DataModel> radios) {
        Radios = radios;
    }
}
