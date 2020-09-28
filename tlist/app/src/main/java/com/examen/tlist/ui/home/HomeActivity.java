package com.examen.tlist.ui.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.examen.tlist.R;
import com.examen.tlist.ui.home.adaptertodone.TasktToDoneAdapter;
import com.examen.tlist.ui.home.model.TaskEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fabCreateTask;
    private AlertDialog dialogBuilder;
    private Button btnSummit, btnCancel;
    private EditText etTask;
    private View dialogView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TaskEntity> listOfTask;
    private DateFormat dateFormat;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fabCreateTask = findViewById(R.id.fabCreateTask);
        dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_template, null);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        btnSummit = (Button) dialogView.findViewById(R.id.btnSummit);
        etTask = (EditText) dialogView.findViewById(R.id.etComment);

        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setView(dialogView);
        builder.setCancelable(true);
        dialogBuilder = builder.create();

        recyclerView = (RecyclerView) findViewById(R.id.rvTastToDone);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dateFormat = new SimpleDateFormat(getResources().getString(R.string.pattern_day));

        listOfTask = new ArrayList<>();

        // List of task mock
        // listOfTask.add(new TaskEntity("Tarea", dateFormat.format(date), false));

        mAdapter = new TasktToDoneAdapter(this, listOfTask);
        recyclerView.setAdapter(mAdapter);

        fabCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        btnSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleOfTask = etTask.getText().toString();
                date = new Date();
                listOfTask.add(new TaskEntity(titleOfTask, dateFormat.format(date), false));
                mAdapter.notifyDataSetChanged();
                dialogBuilder.dismiss();
            }
        });
    }
}