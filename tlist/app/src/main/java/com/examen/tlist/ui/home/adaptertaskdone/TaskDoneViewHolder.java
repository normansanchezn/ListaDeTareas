package com.examen.tlist.ui.home.adaptertaskdone;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;

public class TaskDoneViewHolder extends RecyclerView.ViewHolder {

    RadioButton radioButton;
    ImageView btnEdit, btnDelete;

    public TaskDoneViewHolder(@NonNull View itemView) {
        super(itemView);
        radioButton = itemView.findViewById(R.id.rbItem);
        btnEdit = itemView.findViewById(R.id.btnEditTask);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}
