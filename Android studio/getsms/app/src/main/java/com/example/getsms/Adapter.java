package com.example.getsms;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getsms.roomdatabe.userInfo;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holderv>{
    List<userInfo> list;

    public Adapter(List<userInfo> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public holderv   onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout, parent, false);
        return new holderv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderv holder, int position) {
//        holder.id.setText(Integer.toString(list.get(position).getId()));
        holder.id.setText(position+1+"");
        holder.date.setText(list.get(position).getDate());
        holder.smsget.setText("₹"+list.get(position).getAmt());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class holderv extends RecyclerView.ViewHolder {
        TextView id,smsget,date;
        public holderv(@NonNull View itemView) {
            super(itemView);
            smsget=itemView.findViewById(R.id.seemsg);
            id=itemView.findViewById(R.id.id);
            date=itemView.findViewById(R.id.date);
        }
    }
}
