package com.example.simplechatbot;

import android.app.Application;

public class RepositoryFactory {

    private static MessageRepository INSTANCE;

    public static MessageRepository getInstanceMessageRepository(Application application){
        if (INSTANCE != null) return INSTANCE;

        INSTANCE = new MessageRepository(application);
        return INSTANCE;
    }

}
