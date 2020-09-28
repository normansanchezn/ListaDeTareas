package com.examen.tlist.ui.home.adaptertaskdone;

import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;

public class TaskDoneViewHolder extends RecyclerView.ViewHolder {
    RadioButton radioButton;

    public TaskDoneViewHolder(@NonNull View itemView) {
        super(itemView);
        radioButton = itemView.findViewById(R.id.rbItem);
    }
}
