package com.galinc.hardtraining2.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.db.itility.ListTraining;

import java.util.List;

public class DataAdapterTraining extends RecyclerView.Adapter<DataAdapterTraining.ViewHolder> {

    private LayoutInflater inflater;
    private List<ListTraining> trainings;
    private RecyclerViewClickListener mListener;

    public List<ListTraining> getTrainings() {
        return trainings;
    }

    @NonNull
    @Override
    public DataAdapterTraining.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_training, parent, false);
        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapterTraining.ViewHolder holder, int position) {
        ListTraining training = trainings.get(position);

        holder.nameView.setText(String.format(inflater.getContext().getResources().getString(R.string.exercise_string),training.getName()));
        holder.numberOfitrView.setText(String.format(inflater.getContext().getResources().getString(R.string.itr_string),training.getNumberofitr()));
        holder.weightView.setText(String.format(inflater.getContext().getResources().getString(R.string.weight_string),training.getWeight()));
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public DataAdapterTraining(LayoutInflater inflater, List<ListTraining> trainings) {
        this.inflater = inflater;
        this.trainings = trainings;
    }

    public DataAdapterTraining(LayoutInflater inflater, List<ListTraining> trainings, RecyclerViewClickListener mListener) {
        this.inflater = inflater;
        this.trainings = trainings;
        this.mListener = mListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nameView, numberOfitrView, weightView;
        private RecyclerViewClickListener mListener;

        ViewHolder(View view,RecyclerViewClickListener listener){
            super(view);
            nameView =  view.findViewById(R.id.namefit);
            numberOfitrView =  view.findViewById(R.id.numberofitr);
            weightView = view.findViewById(R.id.weight);
            mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());

        }
    }
}
