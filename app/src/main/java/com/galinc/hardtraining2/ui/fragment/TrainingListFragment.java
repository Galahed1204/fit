package com.galinc.hardtraining2.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.ui.MainActivity;

public class TrainingListFragment extends Fragment {

    TextView tvInfo;

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

//        tvInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle myBandle = new Bundle();
//                myBandle.putString("key", "100");
//                ((MainActivity) getActivity()).getNavController().navigate(R.id.action_trainingListFragment_to_trainingFragment, myBandle);
//            }
//        });


    }

}
