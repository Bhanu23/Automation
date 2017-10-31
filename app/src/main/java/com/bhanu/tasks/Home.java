package com.bhanu.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bhanu.tasks.controller.TaskController;
import com.bhanu.tasks.model.Task;

import java.util.ArrayList;

public class Home extends ActionBarActivity {

    ListView lvAllTasks;

    ArrayList<Task> listTasks;

    ArrayAdapter<Task> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        lvAllTasks = (ListView) findViewById(R.id.listEvents);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TaskController piCon = new TaskController(Home.this);

        listTasks = piCon.getAllPI();

        adapter = new ArrayAdapter<>(Home.this, android.R.layout.simple_list_item_1, listTasks);

        if(adapter.getCount()==0)
            setContentView(R.layout.no_task);

        lvAllTasks.setAdapter(adapter);

        lvAllTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task obj = adapter.getItem(i);

                Intent inModify = new Intent(Home.this, ModifyTask.class);

                inModify.putExtra("task", obj);

                finish();

                startActivity(inModify);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.newEvent) {
            Intent intent = new Intent(Home.this, NewTask.class);

            finish();
            startActivity(intent);

        }

        if (id == R.id.about) {
            Intent intent = new Intent(Home.this, About.class);

            startActivity(intent);

        }

        return true;
    }
}
