package com.galinc.hardtraining2.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.galinc.hardtraining2.db.itility.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM Exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM Exercise WHERE id = :id")
    Exercise getById(long id);

    @Insert
    void insert(Exercise exercise);

    @Insert
    void insert(List<Exercise> exercises);

    @Update
    void update(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

    @Query("DELETE FROM exercise")
    void deleteAll();
}
