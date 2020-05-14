package com.galinc.hardtraining2.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.galinc.hardtraining2.MyApp;
import com.galinc.hardtraining2.db.AppDatabase;
import com.galinc.hardtraining2.db.itility.Document;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewTrainingEditExViewModel extends ViewModel {
    private AppDatabase mDataBase = MyApp.getInstance().getDatabase();
    private int numberOfItr = 0;
    private int weight = 0;
    private LiveData<Document> documentLiveData;


    public int getNumberOfItr() {
        return numberOfItr;
    }

    public int getWeight() {
        return weight;
    }

    public void setNumberOfItr(int numberOfItr) {
        this.numberOfItr = numberOfItr;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LiveData<Document> getDocument(String guid) { return mDataBase.documentDao().getByGuidLiveData(guid); }

    public void setDocument(Document document){
        mDataBase.documentDao().insertCompletable(document).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("test1","Запись в БД прошла успешно");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test1","Ошибка записи в БД");
                    }
                });
    }
    //    public NewTrainingEditExViewModel(@NonNull Application application) {
//        super(application);
//    }
//
//    @NonNull
//    @Override
//    public <T extends Application> T getApplication() {
//        return super.getApplication();
//    }
}
