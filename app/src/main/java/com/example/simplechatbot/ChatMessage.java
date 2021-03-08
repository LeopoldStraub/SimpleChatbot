package com.example.simplechatbot;

public class ChatMessage {

    enum MessageType{SENT, RECEIVED}

    private String chatMessage;

    private MessageType messageType;

    public ChatMessage(String chatMessage, MessageType messageType){
        this.chatMessage = chatMessage;
        this.messageType = messageType;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
