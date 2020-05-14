package com.galinc.hardtraining2.ui.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.db.itility.Document;
import com.galinc.hardtraining2.db.itility.ListTraining;
import com.galinc.hardtraining2.ui.viewmodel.NewTrainingEditExViewModel;
import com.galinc.hardtraining2.ui.viewmodel.NewTrainingViewModel;

public class NewTrainingEditExFragment extends Fragment {
    private NewTrainingEditExViewModel mViewModel;
    private EditText editNumberOf2;
    private EditText editWeight;
    private TextView nameExercise2;
    private TextView nameTraining;
    private Document editDocument;
    private Button buttonSave;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_training_edit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewTrainingEditExViewModel.class);
        final int positionOfDocument = getArguments().getInt("positionOfNewExercise",0);
        nameExercise2 = getActivity().findViewById(R.id.name_exercise2);
        editNumberOf2 = getActivity().findViewById(R.id.edit_number_of2);
        editWeight = getActivity().findViewById(R.id.edit_weight);
        nameTraining = getActivity().findViewById(R.id.name_training);
        buttonSave = getActivity().findViewById(R.id.button_save);

        mViewModel.getDocument(NewTrainingFragment.NEW_GUID).observe(this, document -> {
            editDocument = document;
            ListTraining listTraining = document.listTrainings.get(positionOfDocument);
            nameExercise2.setText(listTraining.getName());
            editWeight.setText(listTraining.getWeight());
            editNumberOf2.setText(String.format("%d",listTraining.getNumberofitr()));
            nameTraining.setText(document.getKindOfTrainings());
        });
        if (getArguments() == null){
            editNumberOf2.setText(mViewModel.getNumberOfItr());
        }

        editNumberOf2.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER))
            {   mViewModel.setNumberOfItr(Integer.parseInt(editNumberOf2.getText().toString()));
                return true;
            }
            return false;
        });

        editWeight.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER))
            {   mViewModel.setWeight(Integer.parseInt(editWeight.getText().toString()));
                return true;
            }
            return false;
        });

        buttonSave.setOnClickListener(v -> {
            editDocument.getListTrainings().get(positionOfDocument).setNumberofitr(mViewModel.getNumberOfItr());
            editDocument.getListTrainings().get(positionOfDocument).setWeight(String.format("%d",mViewModel.getWeight()));

            mViewModel.setDocument(editDocument);
            Bundle myBundle = new Bundle();
            myBundle.putBoolean("fromEditText", true);
//            Navigation.findNavController(getView()).navigate(R.id.action_newTrainingEditExFragment_to_newTrainingFragment,myBundle);
            Navigation.findNavController(getView()).navigate(R.id.newTrainingFragment,myBundle);
        });
//        String sNumber = editNumberOf2.getText().toString();
    }

}
