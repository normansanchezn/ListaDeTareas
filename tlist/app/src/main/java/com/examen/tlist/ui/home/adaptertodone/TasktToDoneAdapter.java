package com.examen.tlist.ui.home.adaptertodone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;
import com.examen.tlist.ui.home.model.TaskEntity;

import java.util.ArrayList;

public class TasktToDoneAdapter extends RecyclerView.Adapter<TaskToDoneViewHolder> {

    Context context;
    ArrayList<TaskEntity> list;

    public TasktToDoneAdapter(Context ctx, ArrayList<TaskEntity> listOfTask) {
        this.context = ctx;
        this.list = listOfTask;
    }

    @NonNull
    @Override
    public TaskToDoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task_to_done, parent, false);
        return new TaskToDoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskToDoneViewHolder holder, int position) {
        holder.radioButton.setText(list.get(position).getTitleOfTask());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
