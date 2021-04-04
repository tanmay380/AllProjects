package com.example.swipeexample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static com.example.swipeexample.MainActivity.viewPager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Second#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Second extends Fragment {
    Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button= getView().findViewById(R.id.changeFrag);
        FragButton();
    }

    public void FragButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "HELLO" ,Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(1);
            }
        });
    }
}