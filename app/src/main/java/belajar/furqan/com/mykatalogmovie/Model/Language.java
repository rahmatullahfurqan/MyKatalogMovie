package belajar.furqan.com.mykatalogmovie.Model;

import com.google.gson.annotations.SerializedName;

public class Language {
    @SerializedName("iso_639_1")
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
