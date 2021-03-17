package com.example.simplechatbot;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Message.class, version = 4, exportSchema = false)
public abstract class MessageDatabase extends RoomDatabase {

    public abstract MessageDao messageDao();

    private static MessageDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized MessageDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                          MessageDatabase.class, "message_database")
                            .fallbackToDestructiveMigration().addCallback(roomCallback).build();
                }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private MessageDao messageDao;
        private PopulateDbAsyncTask(MessageDatabase messageDatabase){
            messageDao = messageDatabase.messageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            messageDao.insert(new Message("Hallo", "Hallo!", 1));
            messageDao.insert(new Message("Leck mich", "Du mich auch", 1));
            messageDao.insert(new Message("Wie gehts", "Gut, dir?", 1));
            return null;
        }
    }
}
