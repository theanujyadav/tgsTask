package com.example.tgstask.Model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tgstask.Constants;
import com.example.tgstask.ContactDetailsActivity;
import com.example.tgstask.Database.ContactList;
import com.example.tgstask.R;

import java.util.List;

public class ContactListAdaptor extends RecyclerView.Adapter<ContactListAdaptor.MyViewHolder>{
    private Context context;
    private List<ContactList> mContactList;
    public ContactListAdaptor(Context context,List<ContactList> contactLists) {
    this.mContactList = contactLists;
    this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.contact_list_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            final ContactList contactList = mContactList.get(position);
            holder.contactTextView.setText(contactList.getContact());
              holder.nameTextView.setText(contactList.getName());

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
    @Override
            public void onClick(View view) {
        Log.d(Constants.MY_TAG,"action called");
        Intent intent = new Intent(context, ContactDetailsActivity.class);
            intent.putExtra(Constants.CONTACT_ID_KEY,contactList.getId());
            context.startActivity(intent);
                }
                });



    }

    @Override
    public int getItemCount() {

        return mContactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView , contactTextView;
        LinearLayout linearLayout;
            public MyViewHolder(@NonNull View itemView) {
                    super(itemView);

            nameTextView = itemView.findViewById(R.id.contactName);
            contactTextView = itemView.findViewById(R.id.contactNumber);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}