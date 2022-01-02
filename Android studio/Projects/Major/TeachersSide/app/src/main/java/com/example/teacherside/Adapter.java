package com.example.teacherside;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherside.retrofit.getSubjects;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<getSubjects> subjectsList;
    Dialog dialog;
    Context context;

    public Adapter(List<getSubjects> subjectsList) {
        this.subjectsList = subjectsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        dialog= new Dialog(parent.getContext());
        View view = inflater.inflate(R.layout.single_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(subjectsList.get(position).getSname().toUpperCase());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = showQrCode(subjectsList.get(holder.getAdapterPosition()).getS_id());
                dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.dialogue, null));
                ImageView imageView= dialog.findViewById(R.id.image);
                imageView.setImageBitmap(bitmap);
                dialog.setCancelable(false);
                dialog.show();
                dialog.findViewById(R.id.buttondialogue).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private Bitmap showQrCode(int sid) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(Integer.toString(sid), BarcodeFormat.QR_CODE, 800, 800);
            BarcodeEncoder be = new BarcodeEncoder();
            Bitmap bitmap = be.createBitmap(bitMatrix);
            Log.d("12345", "showQrCode: " + " crted qr");
            return bitmap;
            
//            qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.subject);
        }
    }

}
