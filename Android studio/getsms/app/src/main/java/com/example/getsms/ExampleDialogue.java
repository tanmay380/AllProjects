package com.example.getsms;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.getsms.roomdatabe.AppDatabase;
import com.example.getsms.roomdatabe.UserDao;
import com.example.getsms.roomdatabe.AmountInfo;
import com.example.getsms.roomdatabe.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class ExampleDialogue extends AppCompatDialogFragment {
    private LinearLayout parentLinearLayout;
    AmountInfo amountInfo;
    CheckBox checkBox;
    boolean isChecked1 = false;

    AppDatabase db;

    ExampleDialogue(AmountInfo User) {
        amountInfo = User;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialogue_main, null);
         db = Room.databaseBuilder(view.getContext(),
                AppDatabase.class, "Data_Store").allowMainThreadQueries().build();

        UserDao dao = db.userDao();
        checkBox = view.findViewById(R.id.split_equally_alert);
        checkBox.setChecked(amountInfo.getSplitEqually() == 1);
        parentLinearLayout = view.findViewById(R.id.parent_linear_layout);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isChecked1 = isChecked;
                for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
                    View v = parentLinearLayout.getChildAt(i);
                    EditText amount_et = v.findViewById(R.id.user_amount_alert);
                    Log.d("12345", "onCheckedChanged: " + v + " ");
                    try {
                        if (isChecked)
                            amount_et.setText(amountInfo.getAmt() / parentLinearLayout.getChildCount() + "");
                        else
                            amount_et.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        TextView date_view = view.findViewById(R.id.date_view_alert);
        date_view.setText(amountInfo.getDate());

        TextView amount_view = view.findViewById(R.id.price_view_alert);
        amount_view.setText("₹ " + amountInfo.getAmt());
        view.findViewById(R.id.add_users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsersEditBox();
            }
        });

        builder.setView(view)
                .setTitle("Add Users and Their Amount")
                .setPositiveButton("DONE", null)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false);

        List<UserInfo> userInfos = db.userDao().getUserInfo(amountInfo.getTid(), amountInfo.getDate());
        Log.d("12345", "onCreateDialog: " + userInfos);
        for (UserInfo s :
                userInfos) {
            View rowView=inflater.inflate(R.layout.fiels, null);
            rowView.findViewById(R.id.delete_user).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDelete(v);
                }
            });
            EditText e1 = rowView.findViewById(R.id.user_name_alert);
            EditText e2 = rowView.findViewById(R.id.user_amount_alert);

            e1.setText(s.getName());
            e2.setText(s.getAmount()+"");

            parentLinearLayout.addView(rowView);
        }
        return builder.create();
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.d("12345", "onResume: ");
        AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog!=null){

            Button positiveButton = (Button) dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean wantToCloseDialog = false;
                    //Do stuff, possibly set wantToCloseDialog to true then...

                    if (addUsersToDatabase())
                        dialog.dismiss();
                    //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                }
            });
        }
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d("12345", "onClick: " + which);
            dialog.cancel();
        }
    };

    private boolean addUsersToDatabase() {
        ArrayList<String> info = getNameWithAmount();
        db.userDao().splitValueUpdate(isChecked1 ?  1 : 0, amountInfo.getTid());
//        Log.d("12345", "addUsersToDatabase: " + parentLinearLayout.getChildCount());
        db.userDao().deleteUsers(amountInfo.getTid(), amountInfo.getDate());
        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
            View v = parentLinearLayout.getChildAt(i);
            EditText name_et = v.findViewById(R.id.user_name_alert);
            String name = name_et.getText().toString();

            EditText amount_et = v.findViewById(R.id.user_amount_alert);
            int amount = -1;
            Log.d("12345", "addUsersToDatabase: " +amount_et.getText() + " " + (amount_et.getText().toString().equals("")));
            if (!amount_et.getText().toString().equals(""))
                amount = Integer.parseInt(amount_et.getText().toString());
            if (!name.isEmpty() && amount!=-1) {
                db.userDao().insertUsers(new UserInfo(amountInfo.getTid(),
                        amountInfo.getDate(),
                        name,
                        amount
                ));
            }else {
                amount_et.setError("Cant be empty");
                name_et.setError("Cant be empty");
                return false;
            }
        }
        return true;
    }

    private ArrayList<String> getNameWithAmount() {
        ArrayList<String> info = new ArrayList<>();
        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
            View v = parentLinearLayout.getChildAt(i);
            EditText name_et = v.findViewById(R.id.user_name_alert);
            String name = name_et.getText().toString();

            EditText amount_et = v.findViewById(R.id.user_amount_alert);
            String amount = amount_et.getText().toString();

            if (name != null && amount != null)
                info.add(name + ":" + amount);
            Log.d("12345", "getNameWithAmount: " + name + " " + amount);
        }
        return info;
    }

    private void addUsersEditBox() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.fiels, null);
        // Add the new row at index 0
        rowView.findViewById(R.id.delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete( v);
            }
        });
        parentLinearLayout.addView(rowView, 0);
        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
            if (checkBox.isChecked()) {
                EditText et = parentLinearLayout.getChildAt(i).findViewById(R.id.user_amount_alert);
                et.setText(amountInfo.getAmt() / parentLinearLayout.getChildCount() + "");
                et.setEnabled(false);
            }
        }
        Log.d("12345", "addUsersEditBox: " + parentLinearLayout.getChildCount());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        db.close();
    }
    public void onDelete(View v){
        Log.d("12345", "onDelete: " + v.getId() + v.getParent());
        parentLinearLayout.removeView((View) v.getParent());
    }
}
