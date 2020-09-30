package com.examen.tlist.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.examen.tlist.R;
import com.examen.tlist.data.local.RoomDb;
import com.examen.tlist.data.remote.FireStorageHelper;
import com.examen.tlist.databinding.ActivityHomeBinding;
import com.examen.tlist.services.firebase.FirebaseServices;
import com.examen.tlist.ui.home.adaptertodone.TaskToDoneAdapter;
import com.examen.tlist.data.model.TaskEntity;
import com.examen.tlist.utils.ToolBox;
import com.examen.tlist.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private AlertDialog dialogBuilder;
    private View dialogView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TaskEntity> listOfTask;
    private DateFormat dateFormat;
    private Date date;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private RoomDb dataBase;

    private EditText etTask;
    private Button btnCancel, btnSummit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityHomeBinding homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        // Init Prefs
        prefs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.key_prefs), Context.MODE_PRIVATE);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras!= null){
                saveUserPrefs(extras.getString("email"));
                dataBase = RoomDb.getInstance(getApplicationContext(), extras.getString("email"));
            } else {
                // do nothing maybe
            }
        }

        dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_template, null);
        btnSummit = (Button) dialogView.findViewById(R.id.btnSummit);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        etTask = (EditText) dialogView.findViewById(R.id.etComment);

        dateFormat = new SimpleDateFormat(getResources().getString(R.string.pattern_day));
        listOfTask = new ArrayList<>();

        if (!dataBase.localDao().getAllTaskToDone(false).isEmpty()){
            listOfTask.clear();
            listOfTask.addAll(dataBase.localDao().getAllTaskToDone(false));
        }

        // Go to task Firestore
        List<TaskEntity> taskss = new ArrayList<>();
        taskss = FireStorageHelper.getTaskToDone(getApplicationContext(), prefs.getString("email", ""));
        Log.i("TASK_TO_DONE", taskss.toString());

        // For AlertDialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setView(dialogView);
        builder.setCancelable(true);
        dialogBuilder = builder.create();

        // For RecyclerView
        homeBinding.rvTastToDone.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        homeBinding.rvTastToDone.setLayoutManager(layoutManager);

        // List of task mock
        // listOfTask.add(new TaskEntity("Tarea", dateFormat.format(date), false));

        mAdapter = new TaskToDoneAdapter(this, listOfTask, homeBinding.rvTaskDone, dataBase);
        homeBinding.rvTastToDone.setAdapter(mAdapter);

        setListeners(homeBinding);
    }

    private void saveUserPrefs(String email) {
        editor = prefs.edit();
        editor.putString("email", email);
        editor.apply();
    }

    private void setListeners(final ActivityHomeBinding homeBinding) {
        homeBinding.fabCreateTask.setOnClickListener(new View.OnClickListener() {
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
                    TaskEntity taskEntity = new TaskEntity(titleOfTask, dateFormat.format(date), false);
                    listOfTask.add(taskEntity);
                    FireStorageHelper.createTaskRemote(getApplicationContext(), taskEntity, prefs.getString("email", ""));
                    dataBase.localDao().insertTask(taskEntity);
                    mAdapter.notifyDataSetChanged();
                    dialogBuilder.dismiss();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.close_session:
                closeSession();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void closeSession() {
        editor.clear();
        editor.apply();
        FirebaseServices.closeSession();
        Intent goLogin = new Intent(this, LoginActivity.class);
        startActivity(goLogin);
        finishAffinity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_close_session, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}