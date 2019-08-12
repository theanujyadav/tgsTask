package com.example.tgstask;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.tgstask.Fragments.ContactListFrag;
import com.example.tgstask.ViewModel.ContactsListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tgstask.ui.main.SectionsPagerAdapter;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private ContactsListViewModel contactsListViewModel;
    SharedPreferences saveDataPreference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
//        my code
//        init View model
        initViewModel();
//        Get preference data
        saveDataPreference = getSharedPreferences(Constants.DATA_STORED_PREFERENCE_KEY,MainActivity.this.MODE_PRIVATE);
        boolean dataStored = saveDataPreference.getBoolean(Constants.DATA_STORED,false);

        if (dataStored != true) {
            Log.d(Constants.MY_TAG,"Data storing block is called") ;
            contactsListViewModel.getJsonData();
          saveDataPreference.edit().putBoolean(Constants.DATA_STORED,true).commit();

        }

}
    private void initViewModel() {
        contactsListViewModel =ViewModelProviders.of(this).get(ContactsListViewModel.class);

    }


    }