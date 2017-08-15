package com.fineanmol.tasks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fineanmol.tasks.controller.TaskController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectTask extends ActionBarActivity {

    ListView lvTasks;
    Task[] tasks;
    ArrayAdapter<Task> eAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_task);
        setTitle("Select Task");
        lvTasks = (ListView) findViewById(R.id.lvTasks);

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = eAdapter.getItem(position);
                task.toggleChecked();
                TaskViewHolder viewHolder = (TaskViewHolder) view.getTag();
                viewHolder.getCheckBox().setChecked(task.isChecked());
            }
        });

        tasks = (Task[]) checkInstance() ;
        if ( tasks == null ) {
            tasks = new Task[] {
                    new Task("Wifi On"),
                    new Task("Wifi Off"),
                    new Task("Mobile Data On"),
                    new Task("Mobile Data Off"),
                    new Task("Silent Ringing Mode"),
                    new Task("Normal Ringing Mode"),
                    new Task("Vibration Ringing Mode"),
                    new Task("Bluetooth On"),
                    new Task("Bluetooth Off"),
                    new Task("Auto Rotate On"),
                    new Task("Auto Rotate Off")};
        }
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.addAll(Arrays.asList(tasks));

        eAdapter = new TaskArrayAdapter(this, taskList);
        lvTasks.setAdapter(eAdapter);

    }

    class Task {
        private String name = "";
        private boolean checked = false;

        public Task(String  name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String toString() {
            return name;
        }

        public void toggleChecked() {
            checked = !checked;
        }
    }

    class TaskViewHolder {
        private CheckBox checkBox;
        private TextView textView;

        public TaskViewHolder( TextView textView, CheckBox checkBox ) {
            this.checkBox = checkBox ;
            this.textView = textView ;
        }
        public CheckBox getCheckBox() {
            return checkBox;
        }
        public TextView getTextView() {
            return textView;
        }
    }

    class TaskArrayAdapter extends ArrayAdapter<Task> {

        private LayoutInflater inflater;

        public TaskArrayAdapter(Context context, List<Task> taskList ) {
            super(context, R.layout.cbview, R.id.rowTextView, taskList);
            inflater = LayoutInflater.from(context) ;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Task task = this.getItem(position);

            CheckBox checkBox ;
            TextView textView ;

            if ( convertView == null ) {
                convertView = inflater.inflate(R.layout.cbview, null);

                textView = (TextView) convertView.findViewById(R.id.rowTextView);
                checkBox = (CheckBox) convertView.findViewById(R.id.cb);

                convertView.setTag(new TaskViewHolder(textView,checkBox));

                checkBox.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Task Task = (SelectTask.Task) cb.getTag();
                        Task.setChecked( cb.isChecked() );
                    }
                });
            } else {
                TaskViewHolder viewHolder = (TaskViewHolder) convertView.getTag();
                checkBox = viewHolder.getCheckBox() ;
                textView = viewHolder.getTextView() ;
            }

            checkBox.setTag(task);

            checkBox.setChecked(task.isChecked());
            textView.setText(task.getName());

            return convertView;
        }

    }

    public Object checkInstance() {
        return tasks;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent in = getIntent();

        String name = "";
        int taskID = -1;

        if (id == R.id.done) {
            
            Intent intent = new Intent(SelectTask.this, TaskService.class);

            int one_task = 0;

            if(eAdapter.getItem(0).isChecked()) {
                intent.putExtra("wifi",0);
                name = "Wifi On";
                taskID = 0;
                one_task++;
            }

            if(eAdapter.getItem(1).isChecked()) {
                intent.putExtra("wifi",1);
                name = "Wifi Off";
                taskID = 1;
                one_task++;
            }

            if(eAdapter.getItem(2).isChecked()) {
                intent.putExtra("mobile_data",0);
                name = "Mobile Data On";
                taskID = 2;
                one_task++;
            }

            if(eAdapter.getItem(3).isChecked()) {
                intent.putExtra("mobile_data",1);
                name = "Mobile Data Off";
                taskID = 3;
                one_task++;
            }

            if(eAdapter.getItem(4).isChecked()) {
                intent.putExtra("silent_mode",0);
                name = "Silent Ringing Mode On";
                taskID = 4;
                one_task++;
            }

            if(eAdapter.getItem(5).isChecked()) {
                intent.putExtra("normal_mode",0);
                name = "Normal Ringing Mode On";
                taskID = 5;
                one_task++;
            }

            if(eAdapter.getItem(6).isChecked()) {
                intent.putExtra("vibration_mode",0);
                name = "Vibration Ringing Mode On";
                taskID = 6;
                one_task++;
            }

            if(eAdapter.getItem(7).isChecked()) {
                intent.putExtra("bluetooth",0);
                name = "Bluetooth On";
                taskID = 7;
                one_task++;
            }

            if(eAdapter.getItem(8).isChecked()) {
                intent.putExtra("bluetooth",1);
                name = "Bluetooth Off";
                taskID = 8;
                one_task++;
            }

            if(eAdapter.getItem(9).isChecked()) {
                intent.putExtra("rotate",0);
                name = "Auto Rotate On";
                taskID = 9;
                one_task++;
            }

            if(eAdapter.getItem(10).isChecked()) {
                intent.putExtra("rotate",1);
                name = "Auto Rotate Off";
                taskID = 10;
                one_task++;
            }

            if(taskID != -1 && one_task == 1) {
                long time = in.getLongExtra("new_task_time",0);
                String t_date = in.getStringExtra("t_date");
                String t_time = in.getStringExtra("t_time");

                intent.putExtra("time_of_execution",time);

                if(time!=0) {

                    PendingIntent pi = PendingIntent.getService(SelectTask.this, (int) time, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    manager.set(AlarmManager.RTC_WAKEUP, time, pi);

                    Toast.makeText(SelectTask.this, "Done", Toast.LENGTH_LONG).show();

                    com.fineanmol.tasks.model.Task obj = new com.fineanmol.tasks.model.Task(0, name, time, taskID, t_date, t_time);
                    TaskController piCon = new TaskController(SelectTask.this);
                    piCon.insertPI(obj);

                    finish();

                    startActivity(new Intent(SelectTask.this, Home.class));

                }

            } else {
                Toast.makeText(SelectTask.this,"Select one task",Toast.LENGTH_LONG).show();
            }

        }

        return true;
    }
}
