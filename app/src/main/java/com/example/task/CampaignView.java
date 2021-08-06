package com.example.task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.task.Fragment.CurrentCampaign;
import com.example.task.Fragment.UpcomingCampaign;
import com.example.task.Fragment.Weekly;


import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class CampaignView extends AppCompatActivity {
    private TabLayout tabs;
    private ViewPager viewpager;
    Activity context;
    private CampaignView activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_view);
//        initView();
        context = activity = this;
//        initViews();
//        initView();

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);



    }

    public void back_button(View view) {
        onBackPressed();
    }

    private void initViews() {
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setupViewPager(ViewPager viewPager) {


        CampaignView.ViewPagerAdapter adapter = new CampaignView.ViewPagerAdapter(activity.getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);



            adapter.addFragment(new CurrentCampaign(), "Currently Campaign");
            adapter.addFragment(new Weekly(), "Weekly Campaign");
            adapter.addFragment(new UpcomingCampaign(), "Upcoming Campaign");
            viewPager.setAdapter(adapter);

    }

    private void initView() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}