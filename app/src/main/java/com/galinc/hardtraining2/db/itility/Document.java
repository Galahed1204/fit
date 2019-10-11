package com.galinc.hardtraining2.db.itility;




import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
public class Document {

    @PrimaryKey(autoGenerate = true)
    public long id;


    private String number;

    private String guid;

    private String date;

//    @TypeConverters({ListTrainingsConverter.class})
//    public List<ListTraining> listTrainings;

    private String kindOfTrainings;

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
