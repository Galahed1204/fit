package com.galinc.hardtraining2.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.ui.viewmodel.NewTrainingEditExViewModel;
import com.galinc.hardtraining2.ui.viewmodel.NewTrainingViewModel;

public class NewTrainingEditExFragment extends Fragment {
    private NewTrainingEditExViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_training_edit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewTrainingEditExViewModel.class);
    }

}
