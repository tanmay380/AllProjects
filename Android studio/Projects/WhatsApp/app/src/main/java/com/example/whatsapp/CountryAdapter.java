package com.example.whatsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> implements Filterable {
    private final ArrayList<Country> clist;
    private final ArrayList<Country> clistfull;

    public CountryAdapter(@NonNull Context context, int resource, ArrayList<Country> clist) {
        super(context, resource, clist);
        this.clist = clist;
        clistfull=new ArrayList<>(clist);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rvlayout, parent, false);

        TextView name = convertView.findViewById(R.id.countryName);
        TextView code = convertView.findViewById(R.id.countryCode);


        name.setText(clist.get(position).getCountryName());
        code.setText("+"+clist.get(position).getCountryCode());

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Country> filterList = new ArrayList<>();
            if (constraint==null||constraint.length()==0){
                filterList.addAll(clistfull);
            }else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Country c: clistfull){
                    if (c.getCountryName().toLowerCase().startsWith(filterPattern)){
                        filterList.add(c);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clist.clear();
            clist.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
