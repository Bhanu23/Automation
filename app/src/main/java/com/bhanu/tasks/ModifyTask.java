package com.bhanu.tasks;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bhanu.tasks.controller.TaskController;
import com.bhanu.tasks.model.Task;

public class ModifyTask extends ActionBarActivity {

    TextView tvShowTask, tvShowDate, tvShowTime;
    Button btnCancelTask, btnDeleteTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_task);
        setTitle("Modify Task");
        tvShowTask = (TextView) findViewById(R.id.tvShowTask);
        tvShowDate = (TextView) findViewById(R.id.tvShowDate);
        tvShowTime = (TextView) findViewById(R.id.tvShowTime);
        btnCancelTask = (Button) findViewById(R.id.btnCancelTask);
        btnDeleteTask = (Button) findViewById(R.id.btnDeleteTask);

        Intent in = getIntent();

        final Task obj = (Task) in.getSerializableExtra("task");

        tvShowTask.setText(obj.getName());
        tvShowDate.setText(obj.getTask_date());
        tvShowTime.setText(obj.getTask_time());


        btnCancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ModifyTask.this, Home.class));
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent = new Intent(ModifyTask.this, TaskService.class);

                if(obj.getTaskId()==0) {
                    intent.putExtra("wifi",0);
                }

                if(obj.getTaskId()==1) {
                    intent.putExtra("wifi",1);
                }

                if(obj.getTaskId()==2) {
                    intent.putExtra("mobile_data",0);
                }

                if(obj.getTaskId()==3) {
                    intent.putExtra("mobile_data",1);
                }

                if(obj.getTaskId()==4) {
                    intent.putExtra("silent_mode",0);
                }

                if(obj.getTaskId()==5) {
                    intent.putExtra("normal_mode",0);
                }

                if(obj.getTaskId()==6) {
                    intent.putExtra("vibration_mode",0);
                }

                if(obj.getTaskId()==7) {
                    intent.putExtra("bluetooth",0);
                }

                if(obj.getTaskId()==8) {
                    intent.putExtra("bluetooth",1);
                }

                if(obj.getTaskId()==9) {
                    intent.putExtra("rotate",0);
                }

                if(obj.getTaskId()==10) {
                    intent.putExtra("rotate",1);
                }


                AlertDialog.Builder deleteBox = new AlertDialog.Builder(ModifyTask.this);
                deleteBox.setTitle("Do you want to delete task?");
                deleteBox.setMessage("Task Name:" + obj.getName());
                deleteBox.setNeutralButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                deleteBox.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        TaskController piCon = new TaskController(ModifyTask.this);

                        piCon.deletePI(obj.getId());


                        PendingIntent cancelIntent = PendingIntent.getService(ModifyTask.this, (int) obj.getTime(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        cancelIntent.cancel();

                        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

                        am.cancel(cancelIntent);


                        Toast.makeText(ModifyTask.this, "Task Deleted", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(ModifyTask.this, Home.class));
                    }
                });
                deleteBox.show();

            }
        });
    }
}
