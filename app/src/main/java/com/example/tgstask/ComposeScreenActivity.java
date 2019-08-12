package com.example.tgstask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.tgstask.ViewModel.ComposeScreenViewModel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.tgstask.Constants.CONTACT_ID;


public class ComposeScreenActivity extends AppCompatActivity {
    String Name, otp,number;
    TextView nameTextView, numberTextView, otpTextView, extraMessage;
    Button sendMessageButton;
    ComposeScreenViewModel mViewModel;
    int id;
//Broad cast
private BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean status = false ;
        AlertDialog.Builder alertboxBuilder = new AlertDialog.Builder(ComposeScreenActivity.this);
        alertboxBuilder
                .setCancelable(false)
                .setTitle("Message")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ComposeScreenActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }
                        });
        status = intent.getBooleanExtra(Constants.BroadID,status);
        if (status == true){
            alertboxBuilder.setMessage("Message is succesfully sent");
            alertboxBuilder.show();
        }else {
            alertboxBuilder.setMessage("Error in sending messafe");
            alertboxBuilder.show();
        }

        Log.d(Constants.MY_TAG,"BroadCast");
    }

};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//    taking intent
        Intent intent = getIntent();
        Name = intent.getStringExtra(Constants.CONTACT_NAME);
        number = intent.getStringExtra(Constants.CONTACTT_NUMBER);
        otp = intent.getStringExtra(Constants.CONTACT_OTP);
        id = (int) intent.getIntExtra(Constants.CONTACT_ID,0);

//        getting view
        nameTextView = findViewById(R.id.name);
        numberTextView = findViewById(R.id.numberText);
        otpTextView = findViewById(R.id.otpText);
        extraMessage = findViewById(R.id.extraMessage);
        sendMessageButton = findViewById(R.id.sendMessageButton);

//        setting data
        nameTextView.setText(Name);
        numberTextView.setText(number);
        otpTextView.setText(otp);
        initViewModel();

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String finalMessage;
            finalMessage = "Otp is :" + otp + "." + extraMessage.getText();

                Log.d(Constants.MY_TAG,"Button Message");
            mViewModel.sendSms(id,number,finalMessage,otp);
            }
        });



    }
    public void initViewModel(){
        mViewModel = ViewModelProviders.of(this).get(ComposeScreenViewModel.class);

    }


    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(ComposeScreenActivity.this)
                .registerReceiver(mReceiver,new IntentFilter(Constants.SERVICE_PAYLOAD));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(ComposeScreenActivity.this)
                .unregisterReceiver(mReceiver);
    }

}
