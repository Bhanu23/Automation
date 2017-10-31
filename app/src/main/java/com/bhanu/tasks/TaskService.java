package com.bhanu.tasks;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import com.bhanu.tasks.controller.TaskController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TaskService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int wifi = intent.getIntExtra("wifi",-1);
        int mobile_data = intent.getIntExtra("mobile_data",-1);
        int silent_mode = intent.getIntExtra("silent_mode",-1);
        int normal_mode = intent.getIntExtra("normal_mode",-1);
        int vibration_mode = intent.getIntExtra("vibration_mode",-1);
        int bluetooth = intent.getIntExtra("bluetooth",-1);
        int rotate = intent.getIntExtra("rotate",-1);

        if(wifi!=-1) {
            if(wifi==0) {
                WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
                if(!manager.isWifiEnabled()) {
                    manager.setWifiEnabled(true);
                    Toast.makeText(TaskService.this,"Wifi Switched On",Toast.LENGTH_LONG).show();
                }

                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
            }


            if(wifi==1) {
                WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
                if(manager.isWifiEnabled()) {
                    manager.setWifiEnabled(false);
                    Toast.makeText(TaskService.this,"Wifi Switched Off",Toast.LENGTH_LONG).show();
                }

                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));

            }
        }

        if(mobile_data!=-1) {
            if(mobile_data==0) {
                try {
                    ConnectivityManager conman = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    Class conmanClass = Class.forName(conman.getClass().getName());
                    Field connectivityManagerField = conmanClass.getDeclaredField("mService");
                    connectivityManagerField.setAccessible(true);
                    final Object connectivityManager = connectivityManagerField.get(conman);
                    final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
                    final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
                    setMobileDataEnabledMethod.setAccessible(true);

                    setMobileDataEnabledMethod.invoke(connectivityManager, true);
                    Toast.makeText(TaskService.this,"Mobile Data Switched On",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
            }


            if(mobile_data==1) {
                try {
                    ConnectivityManager conman = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    Class conmanClass = Class.forName(conman.getClass().getName());
                    Field connectivityManagerField = conmanClass.getDeclaredField("mService");
                    connectivityManagerField.setAccessible(true);
                    final Object connectivityManager = connectivityManagerField.get(conman);
                    final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
                    final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
                    setMobileDataEnabledMethod.setAccessible(true);

                    setMobileDataEnabledMethod.invoke(connectivityManager, false);
                    Toast.makeText(TaskService.this,"Mobile Data Switched Off",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
            }
        }

        if(silent_mode!=-1) {
            if(silent_mode==0) {
                AudioManager audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audiomanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(TaskService.this,"Silent Ringing Mode Activated",Toast.LENGTH_LONG).show();
            }

            TaskController piCon = new TaskController(TaskService.this);

            piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
        }

        if(normal_mode!=-1) {
            if(normal_mode==0) {
                AudioManager audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audiomanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(TaskService.this,"Normal Ringing Mode Activated",Toast.LENGTH_LONG).show();
            }

            TaskController piCon = new TaskController(TaskService.this);

            piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
        }

        if(vibration_mode!=-1) {
            if(vibration_mode==0) {
                AudioManager audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audiomanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(TaskService.this,"Vibration Ringing Mode Activated",Toast.LENGTH_LONG).show();
            }

            TaskController piCon = new TaskController(TaskService.this);

            piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
        }


        if(bluetooth!=-1) {
            if(bluetooth==0) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter( );
                if(!bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.enable();
                    Toast.makeText(TaskService.this,"Bluetooth Switched On",Toast.LENGTH_LONG).show();
                }

                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
            }


            if(bluetooth==1) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter( );
                if(bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.disable();
                    Toast.makeText(TaskService.this,"Bluetooth Switched Off",Toast.LENGTH_LONG).show();
                }

                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
            }
        }

        if(rotate!=-1) {
            if(rotate==0) {
                if(Settings.System.getInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) != 1) {
                    Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 1);
                    Toast.makeText(TaskService.this,"Auto Rotation Switched On",Toast.LENGTH_LONG).show();
                }

                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
            }


            if(rotate==1) {
                if(Settings.System.getInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
                    Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
                    Toast.makeText(TaskService.this,"Auto Rotation Switched Off",Toast.LENGTH_LONG).show();
                }

                TaskController piCon = new TaskController(TaskService.this);

                piCon.deletePITime(intent.getLongExtra("time_of_execution",0));
            }
        }





        return super.onStartCommand(intent, flags, startId);
    }
}
