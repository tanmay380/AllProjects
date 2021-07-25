package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CCPCountry;
import com.hbb20.CountryCodePicker;

import org.w3c.dom.Text;

import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends Activity {

    EditText prefixedt,numberedt;
    Button button,countryPrefix;
    Context context;
    CountryCodePicker countryCodePicker;
    ImageButton msg;

    boolean firt=true;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);




        countryCodePicker=findViewById(R.id.ccp);

        numberedt = findViewById(R.id.number);
        button = findViewById(R.id.submit);
        countryPrefix = findViewById(R.id.countryPrefix);
        msg=findViewById(R.id.scrollDown);

        edt = findViewById(R.id.msg);

//        countryCodePicker.setCcpClickable(false);
        button.setEnabled(false);

        button.setOnClickListener(v->openWhatsApp());
        countryPrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryCodePicker.launchCountrySelectionDialog();
            }
        });
        numberedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        msg.setOnClickListener(v->sendMessage());

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryPrefix.setText(countryCodePicker.getSelectedCountryName());
            }
        });

    }

    private void sendMessage() {
        TextView txt= findViewById(R.id.msdtxt);
        if (firt) {
            msg.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            txt.setVisibility(View.VISIBLE);
            edt.setVisibility(View.VISIBLE);
            firt=false;
        }else
        {
            msg.setImageResource(R.drawable.ccp_ic_arrow_drop_down);
            txt.setVisibility(View.GONE);
            edt.setVisibility(View.GONE);
            firt=true;
        }
    }

//    private void openCountryDialogue() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//        View view = inflater.inflate(R.layout.countycode,null);
//        builder.setView(view);





//        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        RVadapter rVadapter = new RVadapter(county);
//        recyclerView.setAdapter(rVadapter);
//
//        builder.show();

//    }

    private void openWhatsApp() {
        String url;
        String number = numberedt.getText().toString().trim();
        String prefix = countryCodePicker.getDefaultCountryCode();
        String msg=edt.getText().toString().trim();
        if (msg!=null){
            url = "https://wa.me/"+prefix+number+"?text="+msg;
        }else{
            url = "https://wa.me/"+prefix+number;
        }
//        String url = "https://api.whatsapp.com/send?phone="+prefix+number;


        try {
            PackageManager pm = getApplicationContext().getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Toast.makeText(getApplicationContext(), pm.toString(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}