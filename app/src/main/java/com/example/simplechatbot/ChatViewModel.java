package com.example.simplechatbot;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends AndroidViewModel {

    private MessageRepository repository;
    private LiveData<List<Message>> allMessages;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        this.repository = RepositoryFactory.getInstanceMessageRepository(application);
        this.allMessages = repository.getAllMessages();
    }

    public LiveData<List<Message>> getAllChats() {
        return allMessages;
    }


    public String answer(String message){

       return repository.answer(message);

    }
}
