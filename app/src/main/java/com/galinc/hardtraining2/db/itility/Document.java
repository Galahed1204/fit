package com.galinc.hardtraining2.db.itility;




import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.galinc.hardtraining2.db.convert.ListTrainingsConverter;

import java.util.List;

@Entity
public class Document {

    //@PrimaryKey(autoGenerate = true)
    public long id;


    private String number;

    @PrimaryKey
    @NonNull
    private String guid = "";

    private String date;

    private String kindOfTrainings;

    @TypeConverters({ListTrainingsConverter.class})
    public List<ListTraining> listTrainings;


    public List<ListTraining> getListTrainings() {
        return listTrainings;
    }

    public void setListTrainings(List<ListTraining> listTrainings) {
        this.listTrainings = listTrainings;
    }


    public String getKindOfTrainings() {
        return kindOfTrainings;
    }

    public void setKindOfTrainings(String kindOfTrainings) {
        this.kindOfTrainings = kindOfTrainings;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public String getGuid() {
        return guid;
    }

    public String getDate() {
        return date;
    }
}
