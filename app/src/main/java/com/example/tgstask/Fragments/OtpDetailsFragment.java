package com.example.tgstask.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tgstask.Constants;
import com.example.tgstask.Database.MessaageLoadedList;
import com.example.tgstask.Database.savedMessageList;
import com.example.tgstask.Model.OtpListAdaptor;
import com.example.tgstask.R;
import com.example.tgstask.ViewModel.otpDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtpDetailsFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    otpDetailsViewModel mViewModel;
    private List<MessaageLoadedList> mMessageLists = new ArrayList<MessaageLoadedList>();
   OtpListAdaptor otpListAdaptor;
    public List<savedMessageList> saveMessageListData = new ArrayList<>();

    public OtpDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_otp_details, container, false);
        Log.d(Constants.MY_TAG,"on fragment");
        recyclerView  = view.findViewById(R.id.messsgelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initViewHolder();

        return view;
    }


    private void initViewHolder() {
                Observer<List<savedMessageList>> observer =
                        new Observer<List<savedMessageList>>() {
                            @Override
                            public void onChanged(List<savedMessageList> messageData) {
                                saveMessageListData.clear();
                                saveMessageListData.addAll(messageData);
                                if (otpListAdaptor == null){
                                    otpListAdaptor = new OtpListAdaptor(getActivity(),messageData);
                                    recyclerView.setAdapter(otpListAdaptor);
                                }else {
                                    otpListAdaptor.notifyDataSetChanged();
                                }
                            }
                        };

        mViewModel = ViewModelProviders.of(getActivity()).get(otpDetailsViewModel.class);
        mViewModel.saveMessageListData.observe(getActivity(),observer);
    }


}
