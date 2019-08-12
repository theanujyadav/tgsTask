package com.example.tgstask;

import android.content.Intent;
import android.os.Bundle;

import com.example.tgstask.Database.ContactList;
import com.example.tgstask.ViewModel.ContactDetailsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.Random;

public class ContactDetailsActivity extends AppCompatActivity {
    int id;
    ContactDetailsViewModel mViewModel;
    TextView contactName, contactNumber, otpData;
    Button proceedButton;
    StringBuilder otp = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);

             contactName = findViewById(R.id.ContactNameText);
        contactNumber = findViewById(R.id.ContactNumberText);
        otpData = findViewById(R.id.otptext);
        proceedButton = findViewById(R.id.proceedButton);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt(Constants.CONTACT_ID_KEY);
        Log.d(Constants.MY_TAG,String.valueOf(id));

        initViewModel();
        mViewModel.LoadContactDetails(id);
         generateOpt();

         proceedButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(ContactDetailsActivity.this,ComposeScreenActivity.class);
                 intent.putExtra(Constants.CONTACT_NAME,contactName.getText());
                 intent.putExtra(Constants.CONTACTT_NUMBER,contactNumber.getText());
                 intent.putExtra(Constants.CONTACT_OTP,otpData.getText());
                 intent.putExtra(Constants.CONTACT_ID,id);
                 startActivity(intent);
             }
         });
    }

    private void generateOpt() {
                int number;
                Random random = new Random();
                for (int counter=1;counter<=6;counter++){
                    number = random.nextInt(6);
                    otp.append(String.valueOf(number));
                }
                otpData.setText( String.valueOf(otp));
    }

    private void initViewModel() {
        Observer<ContactList> observer = new Observer<ContactList>() {
            @Override
            public void onChanged(ContactList contactList) {
                if (contactList!=null){
                    contactName.setText(contactList.getName());
                    contactNumber.setText(contactList.getContact());
                }
            }
        };
        mViewModel = ViewModelProviders.of(this).get(ContactDetailsViewModel.class);
        mViewModel.liveContactList.observe(ContactDetailsActivity.this,observer);
    }


}
