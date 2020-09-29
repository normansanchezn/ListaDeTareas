package com.examen.tlist.ui.home.adaptertaskdone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;
import com.examen.tlist.data.local.RoomDb;
import com.examen.tlist.data.model.TaskEntity;

import java.util.ArrayList;

public class TaskDoneAdapter extends RecyclerView.Adapter<TaskDoneViewHolder> {

    Context context;
    ArrayList<TaskEntity> listDone;
    private RoomDb dataBase;

    public TaskDoneAdapter(Context context, ArrayList<TaskEntity> listDone, RoomDb dataBase) {
        this.context = context;
        this.listDone = listDone;
        this.dataBase = dataBase;
    }

    @NonNull
    @Override
    public TaskDoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task_to_done, parent, false);
        return new TaskDoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDoneViewHolder holder, final int position) {
        final RadioButton radioButton = holder.radioButton;
        final ImageView btnEdit = holder.btnEdit;
        final ImageView btnDelete = holder.btnDelete;

        radioButton.setText(listDone.get(position).getTitleOfTask());
        radioButton.setChecked(listDone.get(position).getVerifyOfTask());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listDone.size() != 0){
                    listDone.remove(position);
                    dataBase.localDao().deleteTask(listDone.remove(position));
                    notifyDataSetChanged();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Edit task
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDone.size();
    }
}
