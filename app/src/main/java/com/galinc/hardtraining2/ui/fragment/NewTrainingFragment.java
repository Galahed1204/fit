package com.galinc.hardtraining2.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.galinc.hardtraining2.MyApp;
import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.db.AppDatabase;
import com.galinc.hardtraining2.db.itility.Document;
import com.galinc.hardtraining2.ui.adapter.DataAdapterTraining;
import com.galinc.hardtraining2.ui.adapter.RecyclerViewClickListener;
import com.galinc.hardtraining2.ui.viewmodel.NewTrainingViewModel;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewTrainingFragment extends Fragment {
    private NewTrainingViewModel mViewModel;

    private AppDatabase mDataBase = MyApp.getInstance().getDatabase();
    private Disposable listDocuments;
    private TextView nameOfTraining;
    public static NewTrainingFragment newInstance() {
        return new NewTrainingFragment();
    }
    private RecyclerView recyclerView ;
    public static final String NEW_GUID = "new_document";



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_training_fragment, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewTrainingViewModel.class);
        // TODO: Use the ViewModel

        RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
            Bundle myBundle = new Bundle();
            myBundle.putInt("positionOfNewExercise", ++position);

//            myBundle.putParcelableArrayList ("list", ((DataAdapterTraining)this.recyclerView.getAdapter()).getTrainings());
            Navigation.findNavController(getView()).navigate(R.id.action_newTrainingFragment_to_newTrainingEditExFragment,myBundle);
        };

        recyclerView = getView().findViewById(R.id.newTrainingList);

        nameOfTraining = getView().findViewById(R.id.nameOfTraining);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        listDocuments = Completable
                .fromAction(() -> adapter.addAll(mDataBase.templateTrainingDao().getListName()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Выберете шаблон тренировки")
                            .setAdapter(adapter, (dialog, which) -> {
//                                Toast.makeText(getContext(), String.valueOf(which), Toast.LENGTH_SHORT).show();
//                                mDataBase.templateTrainingDao().getByIdLiveData(which).observe(this, new Observer<TemplateTraining>() {
//                                    @Override
//                                    public void onChanged(@Nullable TemplateTraining templateTraining) {
//                                        recyclerView.setAdapter(new DataAdapterTraining(getLayoutInflater(),templateTraining.listTrainings));
//                                    }
//                                });
                                mDataBase.templateTrainingDao().getByIdFlowable(which + 1).observeOn((AndroidSchedulers.mainThread())).subscribe(templateTraining -> {
                                    recyclerView.setAdapter(new DataAdapterTraining(getLayoutInflater(), templateTraining.listTrainings,listener));
                                    nameOfTraining.setText(templateTraining.getName());
                                    //make new document
                                    Document document = new Document();
                                    document.setGuid(NEW_GUID);
                                    document.setListTrainings(templateTraining.listTrainings);
                                    mDataBase.documentDao().insertCompletable(document).subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.d("test1","Ошибка записи в БД");
                                        }
                                    });

                                });
                            })

                            .setCancelable(false)
                            .setNegativeButton("Отмена",
                                    (dialog, id) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();
                });


    }

    @Override
    public void onDestroy() {
        listDocuments.dispose();
        super.onDestroy();
    }



}
