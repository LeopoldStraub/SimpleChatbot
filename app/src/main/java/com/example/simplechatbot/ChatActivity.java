package com.example.simplechatbot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private EditText editTextChat;
    private Button buttonSend;
    private RecyclerView recyclerView;
    private ArrayList<String> chats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editTextChat = findViewById(R.id.edit_text_chat);
        buttonSend = findViewById(R.id.button_send);

        recyclerView = findViewById(R.id.recycler_view_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

       ChatAdapter adapter = new ChatAdapter(this, chats);
        recyclerView.setAdapter(adapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextChat.getText().toString().trim().isEmpty()){
                    chats.add(editTextChat.getText().toString());
                    adapter.setMessages(chats);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}