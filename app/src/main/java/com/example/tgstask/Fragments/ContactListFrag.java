package com.example.tgstask.Fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tgstask.Constants;
import com.example.tgstask.Database.ContactList;
import com.example.tgstask.MainActivity;
import com.example.tgstask.Model.ContactListAdaptor;
import com.example.tgstask.R;
import com.example.tgstask.SplashScreen;
import com.example.tgstask.ViewModel.ContactsListViewModel;
import com.example.tgstask.ViewModel.FragContactListViewModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFrag extends Fragment {
    RecyclerView recyclerView;
    private List<ContactList> mContactLists = new ArrayList<>();
    private FragContactListViewModel mViewModel;
    ContactListAdaptor contactListAdaptor;
    View view;

    public ContactListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        recyclerView  = view.findViewById(R.id.contactListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initViewModel();
        // Inflate the layout for this fragment

        return view;


    }

    private void initViewModel() {
        Observer<List<ContactList>> Contactsobserver =
                new Observer<List<ContactList>>() {
                    @Override
                    public void onChanged(List<ContactList> contactList) {
                        mContactLists.clear();
                        mContactLists.addAll(contactList);
                        if (contactListAdaptor == null){
                            contactListAdaptor =new ContactListAdaptor(getActivity(),contactList);
                            recyclerView.setAdapter(contactListAdaptor);
                        }else {
                            contactListAdaptor.notifyDataSetChanged();
                        }
                    }
                };
                mViewModel = ViewModelProviders.of(getActivity()).get(FragContactListViewModel.class);
                mViewModel.mContactList.observe(getActivity(),Contactsobserver);
    }

}
