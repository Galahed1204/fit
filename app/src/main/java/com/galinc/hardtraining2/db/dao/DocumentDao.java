package com.galinc.hardtraining2.db.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import com.galinc.hardtraining2.db.itility.Document;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DocumentDao {
    @Query("SELECT * FROM document")
    List<Document> getAll();

    @Query("SELECT * FROM document WHERE id = :id")
    Document getById(long id);

    @Insert
    void insert(Document listDocument);

    @Insert
    void insert(List<Document> listDocument);

    @Update
    void update(Document listDocument);

    @Delete
    void delete(Document listDocument);

    @Query("DELETE FROM document")
    void deleteAll();

    @Query("SELECT * FROM document")
    Flowable<List<Document>> getAllData();

    @Query("SELECT * FROM document WHERE id = :id")
    Flowable<Document> getByIdFlow(long id);

    @Query("SELECT * FROM document WHERE id = :id")
    LiveData<Document> getByIdLiveData(long id);

    @Query("SELECT * FROM document WHERE id = :id")
    Document getByIdDoc(long id);

//    @Transaction
//    @Query("SELECT * FROM document WHERE id = :id")
//    LiveData<DocumentWithTrainings> loadDocumentBy(long id);
//
//    @Transaction
//    @Query("SELECT * FROM document WHERE id = :id")
//    Flowable<DocumentWithTrainings> loadDocumentByIdFlow(long id);
//
//    @Transaction @Query("SELECT * FROM document WHERE id = :id")
//    DocumentWithTrainings getDocumentById(long id);
}
