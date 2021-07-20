package com.example.gfgcovidvaccine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RV_Adapter extends RecyclerView.Adapter<RV_Adapter.ViewHolder> {

    public RV_Adapter(ArrayList<Item_class> item_classes) {
        this.item_classes = item_classes;
    }

    private ArrayList<Item_class> item_classes;
    TextView textView;
    // ArrayList<Item_class> item_classes= new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView centerName;
        TextView centerlocation;
        TextView centerTimings;
        TextView fee;
        TextView ageLimit;
        TextView vaccineName;
        TextView availability;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            centerName = itemView.findViewById(R.id.centerName);
            centerlocation = itemView.findViewById(R.id.centerLocation);
            centerTimings = itemView.findViewById(R.id.timings);
            fee = itemView.findViewById(R.id.fees);
            ageLimit = itemView.findViewById(R.id.agelimit);
            vaccineName = itemView.findViewById(R.id.vaccinename);
            availability = itemView.findViewById(R.id.availability);

        }
    }


    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.center_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Adapter.ViewHolder holder, int position) {
        Item_class item_class = item_classes.get(position);
        holder.centerName.setText(item_class.centerName);
        holder.centerlocation.setText(item_class.centerlocation);
        holder.centerTimings.setText("From : " + item_class.centerFromTime + " To : " + item_class.centerToTime);
        holder.vaccineName.setText(item_class.vaccineName);
        holder.fee.setText(item_class.fee);
        holder.ageLimit.setText("Age Limit : " + item_class.ageLimit);
        holder.availability.setText("Availability : " + Integer.toString(item_class.availability));



    }

    @Override
    public int getItemCount() {
        return item_classes.size();
    }


}
