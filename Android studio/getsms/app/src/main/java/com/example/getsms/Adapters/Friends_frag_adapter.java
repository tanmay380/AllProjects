package com.example.getsms.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getsms.R;
import com.example.getsms.roomdatabe.IndividualUserInfo;

import java.util.List;

public class Friends_frag_adapter extends RecyclerView.Adapter<Friends_frag_adapter.holder> {
    List<IndividualUserInfo> list;

    public Friends_frag_adapter(List<IndividualUserInfo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.friends_frag_layout, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.sno.setText(position+1+"");
        holder.name.setText(list.get(position).getName());
        holder.paidAmount.setText("₹ "+list.get(position).getAmountPaid());
        holder.totalAmount.setText("₹ "+list.get(position).getTotalAmount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView name, sno, paidAmount, totalAmount;
        public holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_ff);
            sno = itemView.findViewById(R.id.sno_ff);
            paidAmount = itemView.findViewById(R.id.paid_amount_ff);
            totalAmount = itemView.findViewById(R.id.totalAmount_ff);
        }
    }
}
