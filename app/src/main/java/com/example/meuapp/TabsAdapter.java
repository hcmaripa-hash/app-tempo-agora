package com.example.meuapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class TabsAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public TabsAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public void addFragment(Fragment f, String title) {
        fragments.add(f);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
