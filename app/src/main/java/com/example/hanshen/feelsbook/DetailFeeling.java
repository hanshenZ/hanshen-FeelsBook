package com.example.hanshen.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class DetailFeeling extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private TextView comment;
    private TextView time;
    private TextView feeling;
    private int index;
    private Feel feel;
    ArrayList<Feel> FeelingList;
    ArrayAdapter<Feel> adapter;

    //initial detailfeeling screen
    //update information about feel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeling_details);
        loadFromFile();
        Intent intent = getIntent();
        index = intent.getExtras().getInt("idx");
        feeling = findViewById(R.id.feeling_detail);
        time = findViewById(R.id.time_detail);
        comment = findViewById(R.id.comment_detail);
    }
    //set edit button
    //set delete button
    //jump to editfeeling screen when click edit
    //jump to main screen when click delete
    //using saveinfile to update feellist information
    protected void onStart(){
        super.onStart();
        String feelingd = (FeelingList.get(index)).getFeeling();
        String dated = (FeelingList.get(index)).getDate();
        String commentd = (FeelingList.get(index)).getComment();
        feeling.setText(feelingd);
        time.setText(dated);
        comment.setText(commentd);
        Button Edit = findViewById(R.id.edit);
        Edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(DetailFeeling.this, EditFeeling.class);
                startActivity(intent);
            }
        });
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                FeelingList.remove(index);
                saveInFile();
                Intent intent = new Intent(DetailFeeling.this, MainActivity.class);
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
