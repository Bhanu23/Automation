package com.fineanmol.tasks;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class About extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.about);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
