package com.example.simplechatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder>  {

    private List<String> chats = new ArrayList<>();
    private LayoutInflater layoutInflater;

    ChatAdapter(Context context, ArrayList<String>data){
        this.layoutInflater = LayoutInflater.from(context);
        chats = data;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.chat_item,parent,false);
        return new ChatAdapter.ChatHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatHolder holder, int position) {
        String currentMessage = chats.get(position);
        holder.textViewChat.setText(currentMessage);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public void setMessages(List<String> messages){
        this.chats = messages;
    }

    public static class ChatHolder extends RecyclerView.ViewHolder{
        private TextView textViewChat;

        public ChatHolder(View itemView){
            super(itemView);
            textViewChat = itemView.findViewById(R.id.text_view_chat_item);
        }
    }
}
