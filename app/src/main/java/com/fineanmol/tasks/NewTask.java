package com.fineanmol.tasks;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class NewTask extends ActionBarActivity {

    Button btnSelectDate, btnSelectTime, btnSelectEvent;
    long timeOfExecution;
    DatePicker dtPicker = null;
    TimePicker tmPicker = null;
    TextView tvShowSelectedDate, tvShowSelectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        setTitle("Select Date and Time");
        btnSelectDate = (Button) findViewById(R.id.btnSelectDate);
        btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
        btnSelectEvent = (Button) findViewById(R.id.btnSelectEvent);
        tvShowSelectedDate = (TextView) findViewById(R.id.tvShowSelectedDate);
        tvShowSelectedTime = (TextView) findViewById(R.id.tvShowSelectedTime);

        final Intent in = new Intent(NewTask.this, SelectTask.class);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dlgDate = new Dialog(NewTask.this);
                dlgDate.setContentView(R.layout.date);
                dlgDate.setTitle("Select Date");

                //final DatePicker dtPicker = (DatePicker) dlgDate.findViewById(R.id.dp);
                dtPicker = (DatePicker) dlgDate.findViewById(R.id.dp);
                Button btnDateOK = (Button) dlgDate.findViewById(R.id.btnDateOK);

                btnDateOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year = dtPicker.getYear();
                        int month = dtPicker.getMonth();
                        int day = dtPicker.getDayOfMonth();
                        in.putExtra("year",year+"");
                        in.putExtra("month",month+"");
                        in.putExtra("day", day + "");
                        tvShowSelectedDate.setText(day + "/" + (month+1) + "/" + year);
                        dlgDate.dismiss();
                    }
                });
                dlgDate.show();
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dlgTime = new Dialog(NewTask.this);
                dlgTime.setContentView(R.layout.time);
                dlgTime.setTitle("Select Time");

                //final TimePicker tmPicker = (TimePicker) dlgTime.findViewById(R.id.tm);
                tmPicker = (TimePicker) dlgTime.findViewById(R.id.tm);
                Button btnTimeOK = (Button) dlgTime.findViewById(R.id.btnTimeOK);

                btnTimeOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int hour = tmPicker.getCurrentHour();
                        int minute = tmPicker.getCurrentMinute();
                        in.putExtra("hour",hour+"");
                        in.putExtra("minute", minute + "");
                        tvShowSelectedTime.setText(hour + ":" + minute);
                        dlgTime.dismiss();
                    }
                });
                dlgTime.show();
            }
        });

        btnSelectEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dtPicker != null && tmPicker != null) {
                    Calendar cal = Calendar.getInstance();
                    int year = Integer.parseInt(in.getStringExtra("year"));
                    int month = Integer.parseInt(in.getStringExtra("month"));
                    int day = Integer.parseInt(in.getStringExtra("day"));
                    int hour = Integer.parseInt(in.getStringExtra("hour"));
                    int minute = Integer.parseInt(in.getStringExtra("minute"));




                    in.removeExtra("year");
                    in.removeExtra("month");
                    in.removeExtra("day");
                    in.removeExtra("hour");
                    in.removeExtra("minute");
                    cal.set(year,month,day,hour,minute);
                    timeOfExecution = cal.getTimeInMillis();

                    String t_date = day + "/" + (month+1) + "/" + year;
                    String t_time = hour + ":" + minute;


                    in.putExtra("new_task_time", timeOfExecution);
                    in.putExtra("t_date",t_date);
                    in.putExtra("t_time",t_time);

                    finish();

                    startActivity(in);

                } else {
                    Toast.makeText(NewTask.this,"Please select date/time",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
