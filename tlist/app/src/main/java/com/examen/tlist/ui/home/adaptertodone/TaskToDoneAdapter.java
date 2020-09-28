package com.examen.tlist.ui.home.adaptertodone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examen.tlist.R;
import com.examen.tlist.ui.home.adaptertaskdone.TaskDoneAdapter;
import com.examen.tlist.ui.home.model.TaskEntity;
import com.examen.tlist.ui.home.utils.ToolBox;

import java.util.ArrayList;

public class TaskToDoneAdapter extends RecyclerView.Adapter<TaskToDoneViewHolder> {

    Context context;
    ArrayList<TaskEntity> list, listDone;
    RecyclerView rvTaskDone;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public TaskToDoneAdapter(Context ctx, ArrayList<TaskEntity> listOfTask, RecyclerView rvTaskDone) {
        this.context = ctx;
        this.list = listOfTask;
        this.rvTaskDone = rvTaskDone;
        listDone = new ArrayList<>();
    }

    @NonNull
    @Override
    public TaskToDoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task_to_done, parent, false);
        return new TaskToDoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskToDoneViewHolder holder, final int position) {
        final RadioButton radioButton = holder.radioButton;
        rvTaskDone.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvTaskDone.setLayoutManager(layoutManager);
        mAdapter = new TaskDoneAdapter(context, listDone);
        rvTaskDone.setAdapter(mAdapter);

        radioButton.setText(list.get(position).getTitleOfTask());
        radioButton.setChecked(list.get(position).getVerifyOfTask());

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton.isChecked()) {
                    list.get(position).setVerifyOfTask(true);
                    listDone.add(list.get(position));
                    list.remove(position);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
