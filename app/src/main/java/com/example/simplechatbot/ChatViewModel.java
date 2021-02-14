package com.example.simplechatbot;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends AndroidViewModel {

    private MessageRepository repository;
    private List<String> allMessages;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MessageRepository(application);
        this.allMessages = new ArrayList<>();
    }
    public void sendChat(String mess){
        allMessages.add(mess);
    }

    public List<String> getAllChats() {
        return allMessages;
    }

}
