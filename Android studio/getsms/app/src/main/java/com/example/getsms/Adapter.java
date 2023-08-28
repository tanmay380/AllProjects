package com.example.getsms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.getsms.roomdatabe.AmountInfo;
import com.example.getsms.roomdatabe.AppDatabase;
import com.example.getsms.roomdatabe.UserDao;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holderv>{
    List<AmountInfo> list;
    MainActivity mainActivity;

    FragmentManager fm;

    public Adapter() {
    }

    public Adapter(List<AmountInfo> list, FragmentManager context) {
        this.list=list;
        fm = context;
    }



    @NonNull
    @Override
    public holderv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout, parent, false);
        return new holderv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderv holder, int position) {
//        holder.id.setText(Integer.toString(list.get(position).getId()));
        holder.id.setText(position+1+"");
        holder.date.setText(list.get(position).getDate());
        holder.smsget.setText("₹ "+list.get(position).getAmt());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AppDatabase db = Room.databaseBuilder(view.getContext(),
                        AppDatabase.class, "Data_Store").allowMainThreadQueries().build();

                UserDao dao = db.userDao();
                ExampleDialogue exampleDialogue = new ExampleDialogue(dao.getAmountInfo(list.get(position).getTid()));
                exampleDialogue.show(fm, "example_dialogue");

                return true;
            }
        });
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
