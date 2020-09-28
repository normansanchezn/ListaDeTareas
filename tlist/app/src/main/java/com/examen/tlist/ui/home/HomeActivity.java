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
import com.examen.tlist.ui.home.adaptertodone.TaskToDoneAdapter;
import com.examen.tlist.ui.home.model.TaskEntity;
import com.examen.tlist.ui.home.utils.ToolBox;
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
    private RecyclerView rvTasktoDone, rvTaskDone;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TaskEntity> listOfTask;
    private DateFormat dateFormat;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Init variables
        fabCreateTask = findViewById(R.id.fabCreateTask);
        dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_template, null);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        btnSummit = (Button) dialogView.findViewById(R.id.btnSummit);
        etTask = (EditText) dialogView.findViewById(R.id.etComment);
        rvTasktoDone = (RecyclerView) findViewById(R.id.rvTastToDone);
        rvTaskDone = (RecyclerView) findViewById(R.id.rvTaskDone);

        dateFormat = new SimpleDateFormat(getResources().getString(R.string.pattern_day));
        listOfTask = new ArrayList<>();

        // For AlertDialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setView(dialogView);
        builder.setCancelable(true);
        dialogBuilder = builder.create();

        // For RecyclerView
        rvTasktoDone.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvTasktoDone.setLayoutManager(layoutManager);

        // List of task mock
        // listOfTask.add(new TaskEntity("Tarea", dateFormat.format(date), false));

        mAdapter = new TaskToDoneAdapter(this, listOfTask, rvTaskDone);
        rvTasktoDone.setAdapter(mAdapter);

        setListeners();
    }

    private void setListeners() {
        fabCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etTask.setText("");
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

                if (titleOfTask.equals("")){
                    ToolBox.showToast(getApplicationContext(), getResources().getString(R.string.empty_task));
                } else {
                    date = new Date();
                    listOfTask.add(new TaskEntity(titleOfTask, dateFormat.format(date), false));
                    mAdapter.notifyDataSetChanged();
                    dialogBuilder.dismiss();
                }
            }
        });
    }
}