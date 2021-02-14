package com.example.simplechatbot;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MessageRepository {

    private MessageDao mMessageDao;
    private LiveData<List<Message>> mAllMessages;


    public MessageRepository(Application application) {

       MessageDatabase db =MessageDatabase.getDatabase(application);
        mMessageDao = db.messageDao();
        mAllMessages = mMessageDao.getAllMessages();
    }

        LiveData<List<Message>> getAllMessages(){
            return mAllMessages;
        }

        public void insert(Message message){
            MessageDatabase.databaseWriteExecutor.execute(() -> {
                mMessageDao.insert(message);
            });
        }

        public void delete(Message message){
          MessageDatabase.databaseWriteExecutor.execute(() -> {
                mMessageDao.delete(message);
            });
        }

        public void update(Message message){
            MessageDatabase.databaseWriteExecutor.execute(() -> {
                mMessageDao.update(message);
            });
        }

        public void deleteAll(){
           MessageDatabase.databaseWriteExecutor.execute(() -> {
                mMessageDao.deleteAll();
            });
        }

    }
