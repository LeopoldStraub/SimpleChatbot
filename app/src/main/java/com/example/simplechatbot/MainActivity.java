package com.example.simplechatbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private MessageViewModel messageViewModel;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {

                    switch (activityResult.getResultCode()) {

                        case Activity.RESULT_OK:
                            Intent intent = activityResult.getData();
                            assert intent != null;
                            String title = intent.getStringExtra(AddMessageActivity.EXTRA_TEXT);
                            String description = intent.getStringExtra(AddMessageActivity.EXTRA_ANSWER);

                           Message message = new Message(title,description,"01.01.2021"); // set Date
                            messageViewModel.insert(message);
                            Toast.makeText(MainActivity.this, "Message saved", Toast.LENGTH_SHORT).show();
                            break;
                        case Activity.RESULT_CANCELED:
                            Toast.makeText(MainActivity.this, "Message not saved", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this,AddMessageActivity.class);

        FloatingActionButton buttonAddMessage = findViewById(R.id.button_add_message);
        buttonAddMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    activityResultLauncher.launch(i);
            }});

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MessageAdapter adapter = new MessageAdapter();
        recyclerView.setAdapter(adapter);

        messageViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MessageViewModel.class);
        messageViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {

                    adapter.setMessages(messages);
            }
        });
    }
    }