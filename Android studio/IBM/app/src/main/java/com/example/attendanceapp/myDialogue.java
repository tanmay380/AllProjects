package com.example.attendanceapp;

import android.app.Dialog;
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
    OnClickListener listener;

    public interface OnClickListener {
        void  OnClick(String text1, String text2);
    }

    public void setListener(OnClickListener onClickListener) {
        this.listener = onClickListener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if (getTag().equals(CLASS_AND_DIALOGUE)) dialog = getAddClassDialogue();
        return dialog;
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
