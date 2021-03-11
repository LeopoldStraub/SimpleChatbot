package com.example.simplechatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<ChatMessage> chats = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds


    ChatAdapter(Context context, ArrayList<ChatMessage>data){
        this.layoutInflater = LayoutInflater.from(context);
        chats = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView ;
        switch(viewType){

            case 0: itemView = layoutInflater.inflate(R.layout.chat_item_left,parent,false);
                    return new ChatHolder2(itemView);

            default: itemView = layoutInflater.inflate(R.layout.chat_item,parent,false);
                      return new ChatHolder1(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage currentMessage = chats.get(position);
      /*  if (currentMessage.getMessageType() != ChatMessage.MessageType.RECEIVED){
            holder.textViewChatLeft.setText(currentMessage.getChatMessage());
            holder.textViewChatRight.setVisibility(TextView.INVISIBLE);
        }else{
            holder.textViewChatRight.setText(currentMessage.getChatMessage());
            holder.textViewChatLeft.setVisibility(TextView.INVISIBLE);
        }
        */
        switch (holder.getItemViewType()) {
            case 1:
                ChatHolder1 chatHolder1 = (ChatHolder1) holder;
                chatHolder1.textViewChatRight.setText(currentMessage.getChatMessage());
                setAnimation(holder.itemView, position);
                break;

            case 0:
                ChatHolder2 chatHolder2 = (ChatHolder2) holder;
                chatHolder2.textViewChatLeft.setText(currentMessage.getChatMessage());
                setAnimation(holder.itemView, position);
                break;
        }


    }
    protected int mLastPosition = -1;
    protected void setAnimation(View viewToAnimate, int position) {
        if (position > mLastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(2000);
            viewToAnimate.startAnimation(anim);
            mLastPosition = position;
        }
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    public void setMessages(List<ChatMessage> messages){
        this.chats = messages;
    }

    public static class ChatHolder1 extends RecyclerView.ViewHolder{
        private TextView textViewChatRight;

        public ChatHolder1(View itemView){
            super(itemView);
            textViewChatRight = itemView.findViewById(R.id.text_view_chat_item_right);
        }
    }

    public static class ChatHolder2 extends RecyclerView.ViewHolder{
        private TextView textViewChatLeft;

        public ChatHolder2(View itemView){
            super(itemView);
            textViewChatLeft = itemView.findViewById(R.id.text_view_chat_item_left);
        }
    }
}
