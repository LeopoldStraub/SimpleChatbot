package com.example.simplechatbot;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ChatViewModel extends AndroidViewModel {

    public MessageRepository repository;
    private LiveData<List<Message>> allMessages;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        this.repository = RepositoryFactory.getInstanceMessageRepository(application);
        this.allMessages = repository.getAllMessages();
    }

    public LiveData<List<Message>> getAllChats() {
        return allMessages;
    }


    public Future<String> answer(String message) {

        return repository.answer(message);


    }

    public void learn(String text1, String answer1){
        repository.learn(text1, answer1);
    }

}
