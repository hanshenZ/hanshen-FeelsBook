package com.example.hanshen.feelsbook;

import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

public class Feel implements Serializable {

    private String date;
    private String message;
    private String feeling;
    private static final Integer MAX_CHARS = 100;
    //initial feel() function for feel
    Feel() {
        this.feeling = "default feeling";
        this.date = new Date().toString();
        this.message = "I am default message";
    }
    //initial feel() function for feel
    Feel(String feeling, Date Date1, String message) {
        this.feeling = feeling;
        this.date = Date1.toString();
        this.message = message; // Tweet
    }
    //initial get() function for feel
    public String getDate() {
        return this.date;
    }
    //initial get() function for feel
    public String getComment() {
        return this.message;
    }
    //initial get() function for feel
    public String getFeeling() {
        return this.feeling;
    }
    //initial toString function for displaying information
    public String toString(){
        return feeling+": "+date+", comment: "+message;
    }
    //initial set() function for feel
    public void setDate(String date){this.date = date; }
    //initial set() function for feel
    public void setFeeling(String feeling){this.feeling = feeling; }
    //initial set() function for feel
    //limit the comment input length
    public void setComment(String message){
        if (message.length() <= this.MAX_CHARS) {
            try {
                this.message = message;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}