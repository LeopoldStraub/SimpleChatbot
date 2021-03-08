package com.example.simplechatbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private EditText editTextChat;
    private Button buttonSend;
    private RecyclerView recyclerView;
    private ArrayList<ChatMessage> chats = new ArrayList<>();
    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        editTextChat = findViewById(R.id.edit_text_chat);
        buttonSend = findViewById(R.id.button_send);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list);
        setTitle("Chatbot");

        recyclerView = findViewById(R.id.recycler_view_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

       ChatAdapter adapter = new ChatAdapter(this, chats);
        recyclerView.setAdapter(adapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextChat.getText().toString().trim().isEmpty()){
                    chats.add(new ChatMessage(editTextChat.getText().toString(), ChatMessage.MessageType.RECEIVED));
                    adapter.setMessages(chats);
                    adapter.notifyDataSetChanged();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonSend.setClickable(false);


                            chats.add(new ChatMessage(chatViewModel.answer(chats.get(chats.size()-1).getChatMessage()), ChatMessage.MessageType.SENT));
                            adapter.setMessages(chats);
                            adapter.notifyDataSetChanged();

                            buttonSend.setClickable(true);
                        }
                    });


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.all_messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_menu_item:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}