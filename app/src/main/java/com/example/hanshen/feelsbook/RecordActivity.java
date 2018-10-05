package com.example.hanshen.feelsbook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordActivity extends Activity {
    ArrayList<Feel> FeelingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_history_page);
        TextView joy = findViewById(R.id.joycounter);
    }

}
