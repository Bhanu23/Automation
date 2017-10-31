package com.bhanu.tasks.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bhanu.tasks.model.DatabaseHelper;
import com.bhanu.tasks.model.Task;

import java.util.ArrayList;

public class TaskController {

    DatabaseHelper dbHelper;

    public TaskController(Context context) {
        dbHelper = new DatabaseHelper(context, DatabaseHelper.DBName, null, DatabaseHelper.DBVersion);
    }

    public ArrayList<Task> getAllPI() {
        ArrayList<Task> listTask = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cur = db.rawQuery("select * from task", null);

        cur.getCount();
        cur.getColumnCount();

        listTask.clear();
        while (cur.moveToNext()) {
            Task obj = new Task(cur.getInt(0),cur.getString(1), cur.getInt(2),cur.getInt(3),cur.getString(4),cur.getString(5));
            listTask.add(obj);
        }
        cur.close();
        db.close();
        return listTask;
    }

    public void insertPI(Task obj) {

        String q = "insert into task(name,time,task_id,task_date,task_time) values('" +
                obj.getName() + "','" +
                obj.getTime() + "','" +
                obj.getTaskId() + "','" +
                obj.getTask_date() + "','" +
                obj.getTask_time() + "')";

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL(q);;
        db.close();
    }

    public void updatePI(Task obj) {
        String q = "update task set "
                + " name='" + obj.getName() + "', "
                + " time='" + obj.getTime() + "', "
                + " task_id='" + obj.getTaskId() + "', "
                + " task_date='" + obj.getTask_date() + "', "
                + " task_time='" + obj.getTask_time() + "', "
                + " where id=" + obj.getId();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }

    public void deletePI(int ID) {
        String q = "delete from task where id=" + ID;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }

    public void deletePITime(long time) {
        String q = "delete from task where time=" + time;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }
}
