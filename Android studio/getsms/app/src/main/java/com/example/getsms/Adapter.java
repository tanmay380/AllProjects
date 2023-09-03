package com.example.getsms;

import android.util.Log;
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

public class Adapter extends RecyclerView.Adapter<Adapter.holderv> implements View.OnLongClickListener {
    List<AmountInfo> list;
    Boolean mFromNotification = false;

    FragmentManager fm;

    public Adapter() {
    }

    public Adapter(List<AmountInfo> list, FragmentManager context, Boolean isFromNotification) {
        this.list=list;
        fm = context;
        mFromNotification = isFromNotification;
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
        holder.id.setText(position+1+"");
        holder.date.setText(list.get(position).getDate());
        holder.smsget.setText("₹ "+list.get(position).getAmt());
        holder.itemView.setTag(position);

        holder.itemView.setOnLongClickListener(this);

        if (mFromNotification && position == 0) {
            Log.d("12345", "onBindViewHolder: perform autoclick");
            holder.itemView.performLongClick();
        }

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d("12345", "onLongClick: ");
        AppDatabase db = Room.databaseBuilder(v.getContext(),
                AppDatabase.class, "Data_Store").allowMainThreadQueries().build();

        UserDao dao = db.userDao();
        UserInfoDialogue userInfoDialogue = new UserInfoDialogue(dao.getAmountInfo(list.get((int)v.getTag()).getTid()));
        userInfoDialogue.show(fm, "example_dialogue");

        return true;
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
