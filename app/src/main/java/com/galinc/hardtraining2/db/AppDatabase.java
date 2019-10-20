package com.galinc.hardtraining2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.galinc.hardtraining2.db.dao.DocumentDao;
import com.galinc.hardtraining2.db.dao.ExerciseDao;
import com.galinc.hardtraining2.db.dao.TemplateDao;
import com.galinc.hardtraining2.db.itility.Document;
import com.galinc.hardtraining2.db.itility.Exercise;
import com.galinc.hardtraining2.db.itility.TemplateTraining;

@Database(entities = { Document.class, Exercise.class, TemplateTraining.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DocumentDao documentDao();
    public abstract ExerciseDao exerciseDao();
    public abstract TemplateDao templateTrainingDao();
}
