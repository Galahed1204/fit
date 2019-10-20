package com.galinc.hardtraining2.db.itility;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import io.reactivex.annotations.NonNull;

@Entity
public class ListTraining {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "document_id")
    public int documentId;

    @Expose
    @Ignore
    private Exercise exercise;

    @Expose
    private int itr;

    @Expose
    private int numberofitr;

    @Expose
    private String weight;

    @NonNull
    @Expose
    private String name;

    @Expose
    private String guid;

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setItr(int itr) {
        this.itr = itr;
    }

    public void setNumberofitr(int numberofitr) {
        this.numberofitr = numberofitr;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getItr() {
        return itr;
    }

    public int getNumberofitr() {
        return numberofitr;
    }

    public String getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public String getGuid() {
        return guid;
    }
}
