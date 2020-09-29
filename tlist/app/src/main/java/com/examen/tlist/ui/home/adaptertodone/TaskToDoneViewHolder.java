package com.examen.tlist.ui.home.adaptertodone;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;

public class TaskToDoneViewHolder extends RecyclerView.ViewHolder {

    RadioButton radioButton;
    ImageView btnEdit, btnDelete;

    public TaskToDoneViewHolder(@NonNull View itemView) {
        super(itemView);
        radioButton = itemView.findViewById(R.id.rbItem);
        btnDelete = itemView.findViewById(R.id.btnDelete);
        btnEdit = itemView.findViewById(R.id.btnEditTask);
    }
}