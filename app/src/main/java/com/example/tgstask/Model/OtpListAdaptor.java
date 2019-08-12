package com.example.tgstask.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tgstask.Database.ContactList;
import com.example.tgstask.Database.MessaageLoadedList;
import com.example.tgstask.Database.MessageDao;
import com.example.tgstask.Database.MessageList;
import com.example.tgstask.Database.savedMessageList;
import com.example.tgstask.R;

import org.w3c.dom.Text;

import java.util.List;

public class OtpListAdaptor extends RecyclerView.Adapter<OtpListAdaptor.MyViewHolder> {
    private Context context;
    private List<savedMessageList> mMessageLists;
    public OtpListAdaptor(Context context, List<savedMessageList> messageLists) {
        this.mMessageLists = messageLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.message_list_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        savedMessageList messageList = mMessageLists.get(position);
        holder.nameTextView.setText(messageList.getName());
        holder.messageTextView.setText(messageList.getMessage());
        holder.numberTextView.setText(messageList.getContact());
        holder.otpTextView.setText(messageList.getOtp());

    }

    @Override
    public int getItemCount() {
        return mMessageLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView, messageTextView, numberTextView, otpTextView;
            public MyViewHolder(@NonNull View itemView){
                super(itemView);
                nameTextView = itemView.findViewById(R.id.message_name);
                numberTextView = itemView.findViewById(R.id.message_number);
                messageTextView = itemView.findViewById(R.id.message_sent);
                otpTextView = itemView.findViewById(R.id.message_otp);
            }
        }

}
