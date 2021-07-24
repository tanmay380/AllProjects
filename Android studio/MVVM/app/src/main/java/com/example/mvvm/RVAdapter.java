package com.example.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends ListAdapter<Note, RVAdapter.NoteHolder>{

    private onItemClickListener listener;

    public RVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull  Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPriority() == (newItem.getPriority())  ;
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);


        return new NoteHolder(view );
    }



    @Override
    public void onBindViewHolder(@NonNull  NoteHolder holder, int position) {
        Note currentnote = getItem(position);
        holder.textViewTitle.setText(currentnote.getTitle());
        holder.textviewDescription.setText(currentnote.getDescription());
        holder.textviewPriority.setText(currentnote.getPriority()+"");
    }


    public Note getNoteAt(int positon){
        return  getItem(positon);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textviewDescription;
        private TextView textviewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textviewDescription = itemView.findViewById(R.id.text_view_description);
            textViewTitle= itemView.findViewById(R.id.text_view_title);
            textviewPriority = itemView.findViewById(R.id.text_view_prority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener!=null && position!= RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface  onItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }


}
