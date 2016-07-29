package com.sharkfintech.insurancesharksure;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sharkfintech.insurancesharksure.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 11 ;

    public IntroFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.fragment_intro, null);//main fragment has a tab layout with nested fragments
        tabLayout = (TabLayout) x.findViewById(R.id.tab);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        /**
         *Set an Adapter for the View Pager
         */
        viewPager.setAdapter(new swipeAdapter(getChildFragmentManager()));
        /**
         * Now , this is a workaround
         * The setupWithViewPager doesn't work without the runnable .
         * Maybe it's a Support Library Bug .
         * Now it still doesn't work, the swiping is fine, but the tabs don't show up.
         * Nvm that was the toolbar blocking the tab bar
         */
        tabLayout.setupWithViewPager(viewPager);//populate the tabs
        /*tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });*/
        // Inflate the layout for this fragment
        return x;
    }

    class swipeAdapter extends FragmentPagerAdapter {

        public swipeAdapter(FragmentManager fm) {
            super(fm);
        }
        /**
         * Return fragment with respect to Position .
         */
        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new NotesFragment();
                case 1 : return new ListOfPremiumsFragment();
                case 2 : return new DetailsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }
        /**
         * This method returns the title of the tab according to the position.
         */
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Main";
                case 1 :
                    return "List";
                case 2 :
                    return "Details";
            }
            return null;
        }

    }
}
