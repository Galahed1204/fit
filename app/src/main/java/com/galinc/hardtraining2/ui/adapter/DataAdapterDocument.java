package com.galinc.hardtraining2.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galinc.hardtraining2.R;
import com.galinc.hardtraining2.db.itility.Document;

import java.util.List;

public class DataAdapterDocument extends RecyclerView.Adapter<DataAdapterDocument.ViewHolder> {
    private LayoutInflater inflater;
    private List<Document> documents;
    private RecyclerViewClickListener mListener;

    public DataAdapterDocument(LayoutInflater inflater, List<Document> documents, RecyclerViewClickListener mListener) {
        this.inflater = inflater;
        this.documents = documents;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public DataAdapterDocument.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapterDocument.ViewHolder holder, int position) {
        Document document = documents.get(position);
        holder.nameView.setText(document.getKindOfTrainings());
        holder.dateView.setText(document.getDate());
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nameView, dateView;
        private RecyclerViewClickListener mListener;

        ViewHolder(View view,RecyclerViewClickListener listener){
            super(view);
            nameView =  view.findViewById(R.id.name);
            dateView =  view.findViewById(R.id.date);
            mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }

    }
}
