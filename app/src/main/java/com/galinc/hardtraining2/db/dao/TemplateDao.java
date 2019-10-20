package com.galinc.hardtraining2.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.galinc.hardtraining2.db.itility.TemplateTraining;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TemplateDao {

    @Query("SELECT * FROM TemplateTraining")
    List<TemplateTraining> getAll();

    @Query("SELECT * FROM TemplateTraining WHERE id = :id")
    TemplateTraining getById(long id);

    @Query("SELECT name FROM TemplateTraining")
    List<String> getListName();

    @Query("SELECT * FROM TemplateTraining")
    LiveData<TemplateTraining> getAllLiveData();

    @Query("SELECT * FROM TemplateTraining WHERE id = :id")
    LiveData<TemplateTraining> getByIdLiveData(long id);

    @Query("SELECT * FROM TemplateTraining WHERE id = :id")
    Flowable<TemplateTraining> getByIdFlowable(long id);


    @Insert
    void insert(TemplateTraining TemplateTraining);

    @Insert
    void insert(List<TemplateTraining> TemplateTrainings);

    @Update
    void update(TemplateTraining TemplateTraining);

    @Delete
    void delete(TemplateTraining TemplateTraining);

    @Query("DELETE FROM TemplateTraining")
    void deleteAll();
}
