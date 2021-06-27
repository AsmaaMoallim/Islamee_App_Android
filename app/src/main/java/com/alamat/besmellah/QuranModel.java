package com.alamat.besmellah;

public class QuranModel {
    String quranText;

    public QuranModel(String quranText) {
        this.quranText = quranText;
    }

    public String getHadeesText() {
        return quranText;
    }

    public void setHadeesText(String quranText) {
        this.quranText = quranText;
    }
}
