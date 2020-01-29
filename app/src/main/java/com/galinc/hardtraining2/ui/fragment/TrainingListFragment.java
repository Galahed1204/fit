package com.galinc.hardtraining2.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.galinc.hardtraining2.MyApp;
import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.db.AppDatabase;
import com.galinc.hardtraining2.db.itility.Document;
import com.galinc.hardtraining2.net.NetworkService;
import com.galinc.hardtraining2.ui.MainActivity;
import com.galinc.hardtraining2.ui.adapter.DataAdapterDocument;
import com.galinc.hardtraining2.ui.adapter.DataAdapterTraining;
import com.galinc.hardtraining2.ui.adapter.RecyclerViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingListFragment extends Fragment {

    TextView tvInfo;
    List<Document> documents = new ArrayList<>();
    private AppDatabase mDataBase = MyApp.getInstance().getDatabase();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_list_fragment, container, false);

//        tvInfo = view.findViewById(R.id.tvInfo);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.list_trainings);
        RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
            Bundle myBundle = new Bundle();
            myBundle.putInt("positionOfDocument", position);
            myBundle.putString("guidOfDocument",((DataAdapterDocument)recyclerView.getAdapter()).getDocuments().get(position).getGuid());
//            ((DataAdapterDocument)recyclerView.getAdapter()).getDocuments().get(position);
            Navigation.findNavController(getView()).navigate(R.id.action_trainingListFragment_to_trainingFragment,myBundle);
        };
        mDataBase.documentDao().getAllLiveData().observe(this,documents1 ->
                recyclerView.setAdapter(new DataAdapterDocument(getLayoutInflater(), documents1,listener)));

        mSwipeRefreshLayout = getView().findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(() -> NetworkService
                .getInstance()
                .getJSONApi()
                .postDocuments(NetworkService.GET_LISTOFTRAININGS)
                .enqueue(new Callback<List<Document>>() {
                    @Override
                    public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                        List<Document> post = response.body();
                        if (post != null){
                            Completable.fromAction(() -> {

                                mDataBase.documentDao().deleteAll();
                                mDataBase.documentDao().insert(post);
                                Log.d("test1","Загрузился список упражнений");
                                //TODO make toast

                            }).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe();

                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<Document>> call, Throwable t) {
                        Snackbar.make(getView().findViewById(R.id.swipe_container), t.toString(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                        Log.d("test1","Не загрузился список упражнений");

                    }
                }));

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Log.d("test1","Нажата кнопка добавления");
            Navigation.findNavController(getView()).navigate(R.id.action_nav_document_to_newTrainingFragment);
        });

    }

}
