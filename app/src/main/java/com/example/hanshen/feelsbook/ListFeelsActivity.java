package com.example.hanshen.feelsbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class ListFeelsActivity extends Activity {
    private static final String FILENAME = "file.sav";
    ArrayList<Feel> FeelingList ;
    ArrayAdapter<Feel> adapter;
    protected ListView list;
    private ListView feelshistory;

    //loading history screen
    //update feeling history
    //loading the feelings information
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeling_history_page);
        loadFromFile();
        feelshistory = (ListView) findViewById(R.id.feels_history);
        adapter = new ArrayAdapter<Feel>(this, android.R.layout.simple_list_item_1, FeelingList);
        list = (ListView) findViewById(R.id.feels_history);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    //set item button for clicking the history details
    //switch to datailfeeling screen
    @Override
    protected void onStart() {
        super.onStart();
        feelshistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int index, long id) {
                Intent intent = new Intent(ListFeelsActivity.this, DetailFeeling.class);
                intent.putExtra("idx", index);
                startActivity(intent);
            }
        });
    }
    //copy from lab2 LonglyTwitter LoadFromFile
    protected void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Feel>>() {}.getType();
            FeelingList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

