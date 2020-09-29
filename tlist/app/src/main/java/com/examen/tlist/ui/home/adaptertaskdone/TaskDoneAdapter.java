package com.examen.tlist.ui.home.adaptertaskdone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;
import com.examen.tlist.data.model.TaskEntity;

import java.util.ArrayList;

public class TaskDoneAdapter extends RecyclerView.Adapter<TaskDoneViewHolder> {

    Context context;
    ArrayList<TaskEntity> listDone;

    public TaskDoneAdapter(Context context, ArrayList<TaskEntity> listDone) {
        this.context = context;
        this.listDone = listDone;
    }

    @NonNull
    @Override
    public TaskDoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task_to_done, parent, false);
        return new TaskDoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDoneViewHolder holder, int position) {
        final RadioButton radioButton = holder.radioButton;
        radioButton.setText(listDone.get(position).getTitleOfTask());
        radioButton.setChecked(listDone.get(position).getVerifyOfTask());
    }

    @Override
    public int getItemCount() {
        return listDone.size();
    }
}
