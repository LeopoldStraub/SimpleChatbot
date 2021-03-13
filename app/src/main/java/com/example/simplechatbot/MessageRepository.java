package com.example.simplechatbot;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class MessageRepository {

    private MessageDao mMessageDao;
    private LiveData<List<Message>> mAllMessages;
    String currentAnswer = "BLABLA";




    public MessageRepository(Application application) {

       MessageDatabase db = MessageDatabase.getDatabase(application);
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

    public Future<String> answer(String message) {


            return MessageDatabase.databaseWriteExecutor.submit(new Callable<String>() {
                @Override
                public String call() {
                  return mMessageDao.answer(message);

                }
            });

    }


}
