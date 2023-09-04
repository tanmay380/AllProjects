package com.example.getsms;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import androidx.room.Room;

import com.example.getsms.roomdatabe.AmountModel;
import com.example.getsms.roomdatabe.AppDatabase;
import com.example.getsms.roomdatabe.IndividualUserInfo;
import com.example.getsms.roomdatabe.UserDao;
import com.example.getsms.roomdatabe.AmountInfo;
import com.example.getsms.roomdatabe.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDialogue extends AppCompatDialogFragment implements View.OnClickListener {
    private LinearLayout parentLinearLayout;
    AmountInfo amountInfo;
    CheckBox checkBox;
    boolean isChecked1 = false;
    AppDatabase db;

    UserDao userDao;

    public UserInfoDialogue(AmountInfo User) {
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

        userDao = db.userDao();
        checkBox = view.findViewById(R.id.split_equally_alert);
        checkBox.setChecked(isChecked1 = amountInfo.getSplitEqually() == 1);
        parentLinearLayout = view.findViewById(R.id.parent_linear_layout);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateEditBox(isChecked);
            }
        });

        TextView date_view = view.findViewById(R.id.date_view_alert);
        date_view.setText(amountInfo.getDate());

        TextView amount_view = view.findViewById(R.id.price_view_alert);
        amount_view.setText("₹ " + amountInfo.getAmt());

        Button button = view.findViewById(R.id.cancel_button_dialogue);
        button.setOnClickListener(this);
        Button button1 = view.findViewById(R.id.add_button_dialogue);
        button1.setOnClickListener(this);
        view.findViewById(R.id.add_users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsersEditBox();
            }
        });
        View v = inflater.inflate(R.layout.custom_title_alert, null);
        builder.setCustomTitle(v)
                .setView(view)
                .setCancelable(false);

        if (!getStoredUsers())
            parentLinearLayout.addView(inflater.inflate(R.layout.dialogue_field, null));
        Log.d("12345", "onCreateDialog: " + parentLinearLayout.getChildCount());
        return builder.create();
    }

    private boolean getStoredUsers() {
        List<UserInfo> userInfos = userDao.getUserInfo(amountInfo.getTid(), amountInfo.getDate());
        if (userInfos.size() == 0) return false;
        int i = userInfos.size();
        for (UserInfo s :
                userInfos) {
            View rowView = getActivity().getLayoutInflater().inflate(R.layout.dialogue_field, null);
            TextView textView = rowView.findViewById(R.id.usersno);
            EditText e1 = rowView.findViewById(R.id.user_name_alert);
            EditText e2 = rowView.findViewById(R.id.user_amount_alert);
            EditText e3 = rowView.findViewById(R.id.amount_paid_user);

            textView.setText(i-- + "");
            e1.setText(s.getName());
            e2.setText(s.getAmount() + "");
            if (s.getPaidAmount() != 0) {
                e3.setText(s.getPaidAmount() + "");
            }

            if (isChecked1) e2.setEnabled(false);

            deleteButtonListener(rowView);

            parentLinearLayout.addView(rowView);
        }
        return true;
    }

    private void addUsersEditBox() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.dialogue_field, null);

        TextView textView = rowView.findViewById(R.id.usersno);
        textView.setText((parentLinearLayout.getChildCount() + 1) + "");
        // Add the new row at index 0

        deleteButtonListener(rowView);
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

    private boolean addUsersToDatabase() {
        if (parentLinearLayout.getChildCount() == 0) {
            return false;
        }
        ArrayList<String> info = getNameWithAmount();
        userDao.splitValueUpdate(isChecked1 ? 1 : 0, amountInfo.getTid());
        userDao.deleteUsers(amountInfo.getTid(), amountInfo.getDate());

        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
            View v = parentLinearLayout.getChildAt(i);
            EditText name_et = v.findViewById(R.id.user_name_alert);
            String name = name_et.getText().toString();
            EditText paidAmount_et = v.findViewById(R.id.amount_paid_user);
            int paidAmount = paidAmount_et.getText().toString().equals("") ? 0 : Integer.parseInt(paidAmount_et.getText().toString());

            EditText amount_et = v.findViewById(R.id.user_amount_alert);
            int amount = -1;
            if (!amount_et.getText().toString().equals(""))
                amount = Integer.parseInt(amount_et.getText().toString());
            AmountModel userAmount = null;
            if (!name.isEmpty() && amount != -1) {
                userAmount = userDao.getAmounts(name);
                userDao.insertUsers(new UserInfo(amountInfo.getTid(),
                        amountInfo.getDate(),
                        name,
                        amount,
                        paidAmount
                ));
                if (userDao.getCount(name) == 0){
                    Log.d("12345", "addUsersToDatabase: " + userAmount);
                    userDao.insert(new IndividualUserInfo(name, paidAmount, amount));
                }
                else{
                    Log.d("12345", "addUsersToDatabase: " + userAmount);
                    userDao.updateAmount(name, paidAmount + userAmount.getPaidAmount(),
                            amount + userAmount.getAmount());
                }
            } else {
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void deleteButtonListener(View rowView) {
        rowView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteUser(v);
            }
        });
    }

    public void onDeleteUser(View v) {
        Log.d("12345", "onDelete: " + v.getId() + v.getParent());
        parentLinearLayout.removeView((View) v.getParent());
        updateEditBox(isChecked1);
        updateSerialNumber();
    }

    private void updateSerialNumber() {
        int total = parentLinearLayout.getChildCount();
        for (int i = total - 1; i >= 0; i--) {
            View v = parentLinearLayout.getChildAt(i);
            TextView tv11 = v.findViewById(R.id.user_name_alert);
            Log.d("12345", "updateSerialNumber: " + tv11.getText());
            TextView tv = v.findViewById(R.id.usersno);
            tv.setText((total - i) + "");
        }
    }

    private void updateEditBox(Boolean isChecked) {
        isChecked1 = isChecked;
        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
            View v = parentLinearLayout.getChildAt(i);
            EditText amount_et = v.findViewById(R.id.user_amount_alert);
            Log.d("12345", "onCheckedChanged: " + v + " ");
            try {
                if (isChecked) {
                    amount_et.setText(amountInfo.getAmt() / parentLinearLayout.getChildCount() + "");
                    amount_et.setEnabled(false);
                } else {
                    amount_et.setText("");
                    amount_et.setEnabled(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("12345", "onClick: insidne swuch" + v.getId());
        switch (v.getId()) {
            case R.id.cancel_button_dialogue:
                getDialog().dismiss();
                break;
            case R.id.add_button_dialogue:
                if (addUsersToDatabase())
                    getDialog().dismiss();
                break;
        }
    }
}
