package com.example.swipeexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager;

    First first;
    Second second;
    Third fourth;
    MenuItem premenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialising view PAger
        viewPager = findViewById(R.id.viewPager);

        //Initialising Bottom NAvifation view
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        ClickPager();
        ClickBottom();
        setupViewPager(viewPager);

    }
    private void ClickPager() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if(premenu!=null){
                    premenu.setChecked(false);

                }else{
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

        });
    }

    private void setupViewPager(ViewPager2 viewPager) {
        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        first=new First();
        second=new Second();
        fourth=new Third();
        adapter.addFragment(fourth);
        adapter.addFragment(first);
        adapter.addFragment(second);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    private void ClickBottom() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int v = item.getItemId();
                if (v == R.id.fourth)
                    viewPager.setCurrentItem(0);
                else if (v == R.id.second)
                    viewPager.setCurrentItem(1);
                else if (v == R.id.home)
                    viewPager.setCurrentItem(2);


                return false;
            }

        });
    }


}