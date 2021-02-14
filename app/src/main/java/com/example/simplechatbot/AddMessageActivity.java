package com.example.simplechatbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddMessageActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT =
            "com.example.simplechatbot.EXTRA_TEXT";
    public static final String EXTRA_ANSWER =
            "com.example.simplechatbot.EXTRA_ANSWER";

    private EditText editTextText;
    private EditText editTextAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        editTextText = findViewById(R.id.edit_text_text);
        editTextAnswer = findViewById(R.id.edit_text_answer);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Message");
    }

    private void saveMessage() {

        String text = editTextText.getText().toString();
        String answer = editTextAnswer.getText().toString();

        if (text.trim().isEmpty() || answer.trim().isEmpty()) {
            Toast.makeText(this, "Please insert something", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TEXT,text);
        data.putExtra(EXTRA_ANSWER,answer);

        setResult(RESULT_OK,data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_message:
                saveMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


}