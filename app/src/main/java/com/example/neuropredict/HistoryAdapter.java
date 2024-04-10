package com.example.neuropredict;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolderClass> {

    Context context;
    List<HistoryItem> items;

    public HistoryAdapter(Context context, List<HistoryItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public HistoryViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolderClass(LayoutInflater.from(context).inflate(R.layout.item_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolderClass holder, int position) {
        holder.score.setText(items.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
