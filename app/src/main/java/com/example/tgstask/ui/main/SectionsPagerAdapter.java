package com.example.tgstask.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tgstask.Fragments.ContactListFrag;
import com.example.tgstask.Fragments.OtpDetailsFragment;
import com.example.tgstask.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public static final String TAB_TITLE_CONTACT_LIST = "Contact List";
    public static final String TAB_TITLE_OTP_LIST = "Sent Messages";
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
       switch (position){
           case 0:
               return new ContactListFrag();
           case 1:
               return new OtpDetailsFragment();

           default:
               return null;
       }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return TAB_TITLE_CONTACT_LIST;
            case 1:
                return TAB_TITLE_OTP_LIST;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}