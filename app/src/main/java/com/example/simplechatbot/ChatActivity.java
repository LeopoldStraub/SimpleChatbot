package com.example.simplechatbot;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplechatbot.ChatAdapter;
import com.example.simplechatbot.ChatMessage;
import com.example.simplechatbot.ChatViewModel;
import com.example.simplechatbot.MainActivity;
import com.example.simplechatbot.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ChatActivity extends AppCompatActivity {

    private EditText editTextChat;
    private Button buttonSend;
    private RecyclerView recyclerView;
    private List<ChatMessage> chats = new ArrayList<>();
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


       ChatAdapter adapter = new ChatAdapter(this,chats);
        recyclerView.setAdapter(adapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextChat.getText().toString().trim().isEmpty()){
                    chats.add(new ChatMessage(editTextChat.getText().toString(), ChatMessage.MessageType.SENT));

                            adapter.notifyDataSetChanged();      //Better use DiffUtil
                    recyclerView.smoothScrollToPosition(chats.size());



                       Executors.newSingleThreadExecutor().execute(new Runnable() {
                             @Override
                             public void run() {
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                                 Future<String> mess = chatViewModel.answer(chats.get(chats.size()-1).getChatMessage());
                                 String message = "BALAAA";
                                 try {
                                     message = mess.get();
                                 } catch (ExecutionException | InterruptedException e) {
                                     e.printStackTrace();
                                 }
                                if (message == null) message = "Ich verstehe das nicht";
                                 chats.add(new ChatMessage(message, ChatMessage.MessageType.RECEIVED));
                                 runOnUiThread(new Runnable() {
                                     @Override
                                     public void run() {
                                         adapter.notifyDataSetChanged();
                                         recyclerView.smoothScrollToPosition(chats.size());
                                     }

                                 });
                                    //lernen
                                 if (chats.size() > 2){
                                     String text1, answer1 = "";
                                     text1 = chats.get(chats.size()-3).getChatMessage();
                                     answer1 = chats.get(chats.size()-2).getChatMessage();
                                        chatViewModel.learn(text1,answer1);



                                 }

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