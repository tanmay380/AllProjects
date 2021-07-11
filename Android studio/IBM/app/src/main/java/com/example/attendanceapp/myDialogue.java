package com.example.attendanceapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class myDialogue extends DialogFragment {
    public static final String CLASS_AND_DIALOGUE = "addClass";
    public static final String STUDENT_AND_DIALOGUE = "addStudent";
    public static final String CLASS_UPDATE_DIALOG = "updateclass";
    public static final String STUDENT_UPDATE_DIALOGE = "updatestudents";
    OnClickListener listener;
    private int roll;
    private String name;

    public myDialogue(int roll, String name) {

        this.roll = roll;
        this.name = name;
    }

    public myDialogue() {

    }

    public interface OnClickListener {
        void OnClick(String text1, String text2);
    }

    public void setListener(OnClickListener onClickListener) {

        this.listener = onClickListener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if (getTag().equals(CLASS_AND_DIALOGUE)) dialog = getAddClassDialogue();
        if (getTag().equals(STUDENT_AND_DIALOGUE)) dialog = getAddStudentDialogue();
        if (getTag().equals(CLASS_UPDATE_DIALOG)) dialog = getUpdateClassDialoge();
        if (getTag().equals(STUDENT_UPDATE_DIALOGE)) dialog = getUpdateStudentDialoge();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    private Dialog getUpdateStudentDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.alertdialogue, null);

        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("Update Student Details");


        EditText roll_edt = view.findViewById(R.id.edt01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt.setText(roll+"");
        name_edt.setText(name);
        roll_edt.setEnabled(false);


        Button cancel = view.findViewById(R.id.cancel);
        Button add = view.findViewById(R.id.add);
        add.setText("Update");

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
                    String roll = roll_edt.getText().toString();
                    String name = name_edt.getText().toString();
                    roll_edt.setText("");
                    name_edt.setText("");
                    listener.OnClick(roll, name);
                    dismiss();
                }

        );


        return builder.create();
    }

    private Dialog getUpdateClassDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.alertdialogue, null);

        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("UPDATE CLASS");


        EditText class_edt = view.findViewById(R.id.edt01);
        EditText subject_edt = view.findViewById(R.id.edt02);

        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");


        Button cancel = view.findViewById(R.id.cancel);
        Button add = view.findViewById(R.id.add);
        add.setText("Update");

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
                    String className = class_edt.getText().toString();
                    String subname = subject_edt.getText().toString();
                    if (className.isEmpty()) {
                        class_edt.setError("This field can't be left empty");
                        return;
                    } else if (subname.isEmpty()) {
                        subject_edt.setError("This Field cant be left Empty");
                        return;
                    }
                    listener.OnClick(className, subname);
                    dismiss();
                }

        );


        return builder.create();
    }

    private Dialog getAddStudentDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.alertdialogue, null);

        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("Add New Student");


        EditText roll_edt = view.findViewById(R.id.edt01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt.setHint("Roll Number");
        name_edt.setHint("Name");


        Button cancel = view.findViewById(R.id.cancel);
        Button add = view.findViewById(R.id.add);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
                    String roll = roll_edt.getText().toString();
                    String name = name_edt.getText().toString();
                    roll_edt.setText(String.valueOf(Integer.parseInt(roll) + 1));
                    name_edt.setText("");
                    if (roll.isEmpty()) {
                        roll_edt.setError("This field can't be left empty");
                        return;
                    } else if (name.isEmpty()) {
                        name_edt.setError("This Field cant be left Empty");
                        return;
                    }
                    listener.OnClick(roll, name);
                }

        );


        return builder.create();
    }

    private Dialog getAddClassDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.alertdialogue, null);

        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("Add New Class");


        EditText class_edt = view.findViewById(R.id.edt01);
        EditText subject_edt = view.findViewById(R.id.edt02);

        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");


        Button cancel = view.findViewById(R.id.cancel);
        Button add = view.findViewById(R.id.add);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
                    String className = class_edt.getText().toString();
                    String subname = subject_edt.getText().toString();
                    if (className.isEmpty()) {
                        class_edt.setError("This field can't be left empty");
                        return;
                    } else if (subname.isEmpty()) {
                        subject_edt.setError("This Field cant be left Empty");
                        return;
                    }
                    listener.OnClick(className, subname);
                    dismiss();
                }

        );


        return builder.create();
    }

}
