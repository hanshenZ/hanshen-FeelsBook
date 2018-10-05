package com.example.hanshen.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class EditFeeling extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private EditText comment;
    private EditText time;
    private EditText feeling;
    private int index;
    ArrayList<Feel> FeelingList;

    //initial editfeelling screen information
    //get feellist feel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feelings_edit);
        loadFromFile();
        feeling = findViewById(R.id.feeling_edit);
        time = findViewById(R.id.date_edit);
        comment = findViewById(R.id.comment_edit);
    }
    //initial save button
    //update informations about feel in feellist
    //save it and return to main screen
    //helped by kehan wang(CCID:kehan1),gave some idea about getting list position
    protected void onStart() {
        super.onStart();
        Button saveButton = findViewById(R.id.save_edit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            String nameStr = feeling.getText().toString();
            String date = time.toString();
            String CommentStr = comment.getText().toString();
            (FeelingList.get(index)).setDate(date);
            (FeelingList.get(index)).setComment(CommentStr);
            (FeelingList.get(index)).setFeeling(nameStr);
            saveInFile();
            Intent intent = new Intent(EditFeeling.this, DetailFeeling.class);
            startActivity(intent);
            }
        });
    }
    //copy from lab2 LonglyTwitter LoadFromFile
    public void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME );
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Feel>>(){}.getType();
            FeelingList = gson.fromJson(in, listType);
        }catch (FileNotFoundException e){
            FeelingList = new ArrayList<Feel>();
        }
    }
    //copy from lab2 LonglyTwitter saveInFile
    public void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(FeelingList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
