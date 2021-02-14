package com.example.simplechatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<com.example.simplechatbot.MessageAdapter.MessageHolder> {

    private List<com.example.simplechatbot.Message> messages = new ArrayList<>();

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item,parent,false);
        return new MessageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        com.example.simplechatbot.Message currentMessage = messages.get(position);
        holder.textViewText.setText(currentMessage.getText());
        holder.textViewAnswer.setText(currentMessage.getAnswer());
        // Datum anzeigen

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<com.example.simplechatbot.Message> messages){
        this.messages = messages;
        notifyDataSetChanged();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        private TextView textViewText;
        private TextView textViewAnswer;

        public MessageHolder(View itemView){
            super(itemView);
            textViewText = itemView.findViewById(R.id.text_text_view);
            textViewAnswer = itemView.findViewById(R.id.answer_text_view);
        }
    }

}
