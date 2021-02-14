package com.example.simplechatbot;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "messages_table")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String text;
    private String answer;
    private String dateSent;

    public Message(String text, String answer, String dateSent){
        this.text = text;
        this.answer = answer;
        this.dateSent = dateSent;
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }

    public String getDateSent() {
        return dateSent;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }
}
