package com.example.simplechatbot;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message message);

    @Query("DELETE FROM messages_table")
    void deleteAll();

    @Query("SELECT * FROM messages_table ORDER BY id ASC")
    LiveData<List<Message>> getAllMessages();

    @Update
    void update(Message message);

    @Delete
    void delete(Message message);

    @Query("SELECT answer FROM messages_table WHERE text = :message LIMIT 1")
    String answer(String message);
}
