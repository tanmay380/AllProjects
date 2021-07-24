package com.example.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class EditNode extends AppCompatActivity {

    public static final String EXTRA_TITLE=
            "com.example.mvvm.EXTRA_TITLE";
    public static final String EXTRA_DSC=
            "com.example.mvvm.EXTRA_DSC";
    public static final String EXTRA_PRIORITY=
            "com.example.mvvm.EXTRA_PRIORITY";

    private EditText titleedt;
    private EditText dscedt;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleedt = findViewById(R.id.title_edt);
        dscedt = findViewById(R.id.description_edt);
        numberPicker = findViewById(R.id.number_picker);

        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            setTitle("Edit Node");
            titleedt.setText(intent.getStringExtra(EXTRA_TITLE));
            dscedt.setText(intent.getStringExtra(EXTRA_DSC));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }
        else
            setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.savenote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = titleedt.getText().toString().trim();
        String dsc = dscedt.getText().toString().trim();
        int priotity = numberPicker.getValue();

        if (title.isEmpty()||dsc.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please inser DATA", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DSC, dsc);
        data.putExtra(EXTRA_PRIORITY, priotity);

        int id = getIntent().getIntExtra("id",-1);
        if (id!=-1){
            data.putExtra("id", id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
}