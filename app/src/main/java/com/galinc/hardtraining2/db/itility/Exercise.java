package com.galinc.hardtraining2.db.itility;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Exercise {
    @SerializedName("title")
    @Expose
    private String title;

    @PrimaryKey(autoGenerate = true)
    public long id;

    @SerializedName("guid")
    @Expose
    private String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
