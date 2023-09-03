package com.example.getsms.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.getsms.Adapter;
import com.example.getsms.MainActivity;
import com.example.getsms.R;
import com.example.getsms.roomdatabe.AmountInfo;
import com.example.getsms.roomdatabe.AppDatabase;
import com.example.getsms.roomdatabe.UserDao;

import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    RecyclerView recyclerView;
    List<AmountInfo> list;
    public static RecyclerView.Adapter adapter1;
    private AppDatabase db;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        Log.d("12345", "onCreate: " + mParam1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rcv);
        context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        context.registerReceiver(broadcastReceiver, new IntentFilter("MESSAGE_RECIEVED_UPDATE"));

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // internet lost alert dialog method call from here...
            Log.d("12345", "onReceive: mainactivirt");
            getList();
        }
    };


    public void getList() {
        db = Room.databaseBuilder(context,
                AppDatabase.class, "Data_Store").allowMainThreadQueries().build();
        MainActivity activity =(MainActivity) getActivity();
        if (activity == null) activity = new MainActivity();

        UserDao dao = db.userDao();
        list = dao.selectAll();
        Collections.reverse(list);
        Log.d("12345", "getList: " + getActivity());
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Log.d("12345", "getList: intent" + "intent");
        adapter1 = new Adapter(list, fragmentManager, mParam1 != null);
        recyclerView.setAdapter(adapter1);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("12345", "onResume: fragmtn");
        getList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(broadcastReceiver);
    }
}