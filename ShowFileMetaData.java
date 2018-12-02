package com.dev.aravinthvvs.fileapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowFileMetaData extends Activity {

    private TextView textViewToShowData;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        textViewToShowData = (TextView)findViewById(R.id.textView2);
        textViewToShowData.setText(getIntent().getStringExtra("metaData"));
    }
}
