package com.galinc.hardtraining2.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.galinc.hardtraining2.MyApp;
import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.db.AppDatabase;
import com.galinc.hardtraining2.db.itility.Document;
import com.galinc.hardtraining2.ui.adapter.DataAdapterTraining;
import com.galinc.hardtraining2.ui.adapter.RecyclerViewClickListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TrainingFragment extends Fragment {

    private TextView tvInfo;
    AppDatabase mDataBase = MyApp.getInstance().getDatabase();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.training_fragment, container, false);

        //tvInfo = view.findViewById(R.id.tvInfo);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RecyclerView recyclerView = getView().findViewById(R.id.listlt);
        final int positionOfDocument = getArguments().getInt("positionOfDocument",0);
        final String guidOfDocument = getArguments().getString("guidOfDocument","");

        RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
            Bundle myBundle = new Bundle();
            myBundle.putInt("positionOfNewExercise", ++position);
//            Navigation.findNavController(getView()).navigate(R.id.action_trainingListFragment_to_trainingFragment,myBundle);
        };

        mDataBase.documentDao().getByGuidLiveData(guidOfDocument).observe(this, document ->{
                    if (document.listTrainings != null) recyclerView.setAdapter(new DataAdapterTraining(getLayoutInflater(), document.listTrainings,listener));
                }
                );
//        final Disposable subscribe = mDataBase.documentDao().getByIdFlow(positionOfDocument).observeOn(AndroidSchedulers.mainThread()).subscribe(document ->{
//                    if (document.listTrainings != null) recyclerView.setAdapter(new DataAdapterTraining(getLayoutInflater(), document.listTrainings));
//                }
//                );
        //tvInfo.setText(getArguments().getString("key"));
    }
}
