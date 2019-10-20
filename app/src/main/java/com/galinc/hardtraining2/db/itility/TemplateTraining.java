package com.galinc.hardtraining2.db.itility;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.galinc.hardtraining2.db.convert.ListTrainingsConverter;

import java.util.List;

@Entity
public class TemplateTraining {

    @PrimaryKey(autoGenerate = true)
    public long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @TypeConverters({ListTrainingsConverter.class})
    public List<ListTraining> listTrainings;
}
