package com.example.simplechatbot;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "messages_table")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String text;
    private String answer;
    private int priority;

    public Message(String text, String answer, int priority){
        this.text = text;
        this.answer = answer;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
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

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
