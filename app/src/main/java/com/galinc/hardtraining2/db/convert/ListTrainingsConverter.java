package com.galinc.hardtraining2.db.convert;

import androidx.room.TypeConverter;

import com.galinc.hardtraining2.db.itility.ListTraining;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ListTrainingsConverter {
    @TypeConverter
    public String fromListTrainings(List<ListTraining> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }

    @TypeConverter
    public List<ListTraining> toListTrainings(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<ListTraining>>() {}.getType();
        return gson.fromJson(data, listType);
    }
}
