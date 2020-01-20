package com.galinc.hardtraining2.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.galinc.hardtraining2.MyApp;
import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.db.AppDatabase;
import com.galinc.hardtraining2.db.itility.Exercise;
import com.galinc.hardtraining2.db.itility.TemplateTraining;
import com.galinc.hardtraining2.net.NetworkService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {

    private AppDatabase mDataBase = MyApp.getInstance().getDatabase();

    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_download, container, false);
        Button buttonDownloadExercise = v.findViewById(R.id.button_download_exercise);
        buttonDownloadExercise.setOnClickListener(v1 -> {
            //mDataBase ;
            NetworkService.getInstance().
                    getJSONApi()
                    .postData(NetworkService.GET_EXERCISES)
                    .enqueue(new Callback<List<Exercise>>() {
                        @Override
                        public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                            List<Exercise> posts = response.body();
                            if (posts != null)
                                Completable.fromAction(() -> {
                                    mDataBase.exerciseDao().deleteAll();
                                    mDataBase.exerciseDao().insert(posts);
                                }).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe();
                            Log.d("test1","Загрузился справочник упражнений");
                        }

                        @Override
                        public void onFailure(Call<List<Exercise>> call, Throwable t) {
                            Log.d("test1","Не загрузился справочник упражнений");
                        }
                    });
        });

        Button buttonDownloadTemplate = v.findViewById(R.id.button_download_template);
        buttonDownloadTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkService.getInstance()
                        .getJSONApi()
                        .postTemplate(NetworkService.GET_TAMPLATE)
                        .enqueue(new Callback<List<TemplateTraining>>() {
                            @Override
                            public void onResponse(Call<List<TemplateTraining>> call, Response<List<TemplateTraining>> response) {
                                List<TemplateTraining> posts = response.body();

                                if (posts != null)
                                    Completable.fromAction(() -> {
                                        mDataBase.templateTrainingDao().deleteAll();
                                        mDataBase.templateTrainingDao().insert(posts);

                                    }).subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe();
                                Log.d("test1","Загрузился справочник шаблонов");
                            }

                            @Override
                            public void onFailure(Call<List<TemplateTraining>> call, Throwable t) {
                                Log.d("test1","Не загрузился справочник шаблонов");
                            }
                        });
            }
        });
        return v;
    }

}
