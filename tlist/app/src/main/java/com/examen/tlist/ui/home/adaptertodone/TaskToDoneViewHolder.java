package com.examen.tlist.ui.home.adaptertodone;

import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;

public class TaskToDoneViewHolder extends RecyclerView.ViewHolder {

    RadioButton radioButton;

    public TaskToDoneViewHolder(@NonNull View itemView) {
        super(itemView);
        radioButton = itemView.findViewById(R.id.rbItem);
    }
}
