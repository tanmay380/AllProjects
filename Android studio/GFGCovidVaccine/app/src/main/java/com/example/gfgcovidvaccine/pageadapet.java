package com.example.gfgcovidvaccine;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class pageadapet extends FragmentStateAdapter {


    public pageadapet(@NonNull FragmentManager fragmentManager, @NonNull  Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Pincode();
            case 1: return new District2();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
