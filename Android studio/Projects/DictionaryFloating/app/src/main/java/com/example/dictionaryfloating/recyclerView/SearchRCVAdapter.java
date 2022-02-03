package com.example.dictionaryfloating.recyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryfloating.DataBase.DataBaseWord;
import com.example.dictionaryfloating.MainActivity;
import com.example.dictionaryfloating.R;
import com.example.dictionaryfloating.retrofit.ApiController;
import com.example.dictionaryfloating.retrofit.BaseClass;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRCVAdapter extends RecyclerView.Adapter<SearchRCVAdapter.Holder> implements Filterable {
    ArrayList<String> words = new ArrayList<>();
    Context context;

    public SearchRCVAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchrecyclerview, parent, false);
        context = parent.getContext();
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(words.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofitCall(words.get(position));
            }
        });
    }
    public void retrofitCall(String word) {
        Toast.makeText(context, word, Toast.LENGTH_SHORT).show();
        Call<List<BaseClass>> call = ApiController.getInstance()
                .apiset()
                .results("abandon");
        call.enqueue(new Callback<List<BaseClass>>() {
            @Override
            public void onResponse(Call<List<BaseClass>> call, Response<List<BaseClass>> response) {
                List<BaseClass> obj = response.body();
                Log.d("12345", "onResponse: " + obj.size());
                /*int i = 0;
                while (i < obj.size()) {
                    Log.d("12345", "onResponse: " + obj.get(i).getPhonetic());
                    i++;
                }*/
                Log.d("12345", "onResponse: " + obj.get(0).getMeanings().get(0).getDefinitions().get(0).getDefinition());
            }

            @Override
            public void onFailure(Call<List<BaseClass>> call, Throwable t) {
                Log.d("12345", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private Filter filter = new Filter() {
        Scanner sc;


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            words.clear();
            if (charSequence != null && charSequence.length() != 0) {
                String filePattern = charSequence.toString().toLowerCase(Locale.ROOT).trim();
                DataBaseWord dataBaseWord = new DataBaseWord(context);
                words = dataBaseWord.findWords(filePattern);
            }
            Log.d("12345", "performFiltering: " + words.size());

            FilterResults results = new FilterResults();
            results.values = words;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            words = (ArrayList<String>) filterResults.values;
            notifyDataSetChanged();
            getItemCount();
        }
    };

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.wordlist);
        }
    }


}
