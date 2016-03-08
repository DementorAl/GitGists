package ru.danilov.gitgists.model;

/**
 * Created by Danilov Alexey on 05.03.2016.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;


public class Gist extends RealmObject implements Serializable    {
    private String url;
    private String forks_url;
    private String commits_url;
    @PrimaryKey
    private String id;
    private String description;
    private boolean public_s;
    private Owner owner;
    private String user;
    @Ignore
    private Map<String, File> files;
    private String filesSerializable;
    private boolean truncated;
    private int comments;
    private String comments_url;
    private String html_url;
    private String git_pull_url;
    private String git_push_url;
    private String created_at;
    private String updated_at;


    public String getFilesSerializable() {
        Type itemsMapType = new TypeToken<Map<String, File>>(){}.getType();

        return new Gson().fromJson(filesSerializable,itemsMapType);
    }

    public void setFilesSerializable(String filesSerializable) {
        String jsonString = new Gson().toJson(files);
        filesSerializable = jsonString;
    }


    public String getLocaleDiscription() {
        return localeDiscription;
    }

    public void setLocaleDiscription(String localeDiscription) {
        this.localeDiscription = localeDiscription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String localeDiscription;
    private String note;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic_s() {
        return public_s;
    }

    public void setPublic_s(boolean public_s) {
        this.public_s = public_s;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getGit_pull_url() {
        return git_pull_url;
    }

    public void setGit_pull_url(String git_pull_url) {
        this.git_pull_url = git_pull_url;
    }

    public String getGit_push_url() {
        return git_push_url;
    }

    public void setGit_push_url(String git_push_url) {
        this.git_push_url = git_push_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }



    public static class SuperList extends ArrayList<Gist> {
    }




}
