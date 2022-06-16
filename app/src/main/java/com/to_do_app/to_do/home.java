package com.to_do_app.to_do;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.to_do.R;
import com.google.android.material.tabs.TabLayout;

public class home extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setviewpager();
    }

    private void setviewpager() {
        homefragementadapter fragementadapter=new homefragementadapter(getSupportFragmentManager());
        viewPager=findViewById(R.id.viewpager);
        fragementadapter.add(new tasksfragement(),"tasks");
        fragementadapter.add(new calendarfragement(),"calendar");
        fragementadapter.add(new minefragement(),"mine");
        viewPager.setAdapter(fragementadapter);
        tabLayout=findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager,true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    fragementadapter.getItem(0).onPause();
                    fragementadapter.getItem(1).onStart();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}