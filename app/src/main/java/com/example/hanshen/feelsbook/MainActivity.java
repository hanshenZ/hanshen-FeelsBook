/*******
Copyright (c) 2018 Hanshen Zhao
 /**
 * This is the mainActivity of FeelsBook
 *
 *Date: Oct.5th 2018
 *@author Hanshen Zhao
 *@version 1.0
 *
 *
 *
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 *******/




package com.example.hanshen.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private EditText edit_comment;
    private String edit_feeling;
    private ArrayList<Feel> loadlist;
    ArrayList<Feel> FeelingList = new ArrayList<Feel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile();
    }

    //click Joy button then add it to history
    public void joybutton(View View) {
        edit_comment = (EditText) findViewById(R.id.main_comment);
        edit_feeling = "Joy";
        String feeling = edit_feeling;
        String comment = edit_comment.getText().toString();
        Date date = new Date(System.currentTimeMillis());
        Feel subs = new Feel(feeling, date, comment);
        FeelingList.add(subs);
        saveInFile();
    }
    //click anger button then add it to history
    public void angerbutton(View View) {
        edit_comment = (EditText) findViewById(R.id.main_comment);
        edit_feeling = "anger";
        String feeling = edit_feeling;
        String comment = edit_comment.getText().toString();
        Date date = new Date(System.currentTimeMillis());
        Feel subs = new Feel(feeling, date, comment);
        FeelingList.add(subs);
        saveInFile();
    }
    //click love button then add it to history
    public void lovebutton(View View) {
        edit_comment = (EditText) findViewById(R.id.main_comment);
        edit_feeling = "love";
        String feeling = edit_feeling;
        String comment = edit_comment.getText().toString();
        Date date = new Date(System.currentTimeMillis());
        Feel subs = new Feel(feeling, date, comment);
        FeelingList.add(subs);
        saveInFile();
    }
    //click fear button then add it to history
    public void fearbutton(View View) {
        edit_comment = (EditText) findViewById(R.id.main_comment);
        edit_feeling = "fear";
        String feeling = edit_feeling;
        String comment = edit_comment.getText().toString();
        Date date = new Date(System.currentTimeMillis());
        Feel subs = new Feel(feeling, date, comment);
        FeelingList.add(subs);
        saveInFile();
    }
    //click surprise button then add it to history
    public void surprisebutton(View View) {
        edit_comment = (EditText) findViewById(R.id.main_comment);
        edit_feeling = "surprise";
        String feeling = edit_feeling;
        String comment = edit_comment.getText().toString();
        Date date = new Date(System.currentTimeMillis());
        Feel subs = new Feel(feeling, date, comment);
        FeelingList.add(subs);
        saveInFile();
    }
    //click sadness button then add it to history
    public void sadnessbutton(View View) {
        edit_comment = (EditText) findViewById(R.id.main_comment);
        edit_feeling = "sadness";
        String feeling = edit_feeling;
        String comment = edit_comment.getText().toString();
        Date date = new Date(System.currentTimeMillis());
        Feel subs = new Feel(feeling, date, comment);
        FeelingList.add(subs);
        saveInFile();
    }

    //click history button then switch to history screen
    public void historybutton(View View) {
        Intent intent = new Intent(MainActivity.this, ListFeelsActivity.class);
        startActivity(intent);
    }
    //click record button then switch to record screen
    public void recordbutton(View View) {
        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
        startActivity(intent);
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



