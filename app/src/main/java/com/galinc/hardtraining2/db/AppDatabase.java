package com.galinc.hardtraining2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.galinc.hardtraining2.db.dao.DocumentDao;
import com.galinc.hardtraining2.db.itility.Document;

@Database(entities = { Document.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DocumentDao documentDao();
}
