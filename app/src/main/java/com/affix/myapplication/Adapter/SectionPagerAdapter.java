package com.affix.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.affix.myapplication.Fragments.Calander;
import com.affix.myapplication.Fragments.SubjectDashboard;
import com.affix.myapplication.Fragments.UpcommingActivities;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SubjectDashboard();
            case 1:
                return new Calander();
            case 2:
                return new UpcommingActivities();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Subjects";
            case 1:
                return "Classes";
            case 2:
                return "Upcoming Activities";
            default:
                return null;
        }
    }
}
