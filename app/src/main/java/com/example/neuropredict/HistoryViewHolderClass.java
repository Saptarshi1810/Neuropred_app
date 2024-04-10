package com.example.neuropredict;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryViewHolderClass extends RecyclerView.ViewHolder {

    TextView score ;

    public HistoryViewHolderClass(@NonNull View itemView) {
        super(itemView);
        score=itemView.findViewById(R.id.score);

    }
}
