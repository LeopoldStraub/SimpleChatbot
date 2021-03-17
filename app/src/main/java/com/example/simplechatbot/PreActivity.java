package com.example.simplechatbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PreActivity extends AppCompatActivity {

    Button button_start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        button_start = findViewById(R.id.start_button);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(PreActivity.this, ChatActivity.class);
               startActivity(intent);
            }
        });
    }
}
