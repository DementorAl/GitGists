package ru.danilov.gitgists.api.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Danilov Alexey on 06.03.2016.
 */
public class File extends RealmObject implements Serializable {

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRaw_url() {
        return raw_url;
    }

    public void setRaw_url(String raw_url) {
        this.raw_url = raw_url;
    }

    private String filename;
    private String type;
    private String language;
    private String raw_url;
    private int size;


}
