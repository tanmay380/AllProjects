package com.example.whatsapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity {

    EditText prefixedt, numberedt;
    Button button, countryPrefix;
    ImageButton msg;
    ListView listView;
    private ArrayList<Country> listcountry;
    CountryAdapter countryAdapter;
    boolean firt = true;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        prefixedt = findViewById(R.id.code);
//        countryCodePicker=findViewById(R.id.ccp);

        numberedt = findViewById(R.id.number);
        button = findViewById(R.id.submit);
        countryPrefix = findViewById(R.id.countryPrefix);
        msg = findViewById(R.id.scrollDown);

        edt = findViewById(R.id.msg);
        button.setEnabled(false);


        button.setOnClickListener(v -> openWhatsApp());
        countryPrefix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCountryDialogue();
            }
        });
        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())){
            numberedt.setText(intent.getData().toString().replace("tel:",""));
            button.setEnabled(true);
        }
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
        msg.setOnClickListener(v -> sendMessage());


    }

    private void sendMessage() {

        TextView txt = findViewById(R.id.msdtxt);
        if (firt) {
            msg.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            txt.setVisibility(View.VISIBLE);
            edt.setVisibility(View.VISIBLE);
            firt = false;
        } else {
            msg.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            txt.setVisibility(View.GONE);
            edt.setVisibility(View.GONE);
            firt = true;
        }
    }

    private void openCountryDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View view = inflater.inflate(R.layout.dialogue, null);
        builder.setView(view);
        listView = view.findViewById(R.id.listview);
        listcountry = new ArrayList<>();
        fillArray();

//

        countryAdapter = new CountryAdapter(getApplicationContext(),
                R.layout.rvlayout,
                listcountry);
        listView.setAdapter(countryAdapter);
        final AlertDialog show = builder.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                countryPrefix.setText(listcountry.get(position).CountryName);
                prefixedt.setText(listcountry.get(position).CountryCode);
                show.dismiss();
            }
        });

        EditText search = view.findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                countryAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void fillArray() {
        AssetManager as = getAssets();
        try {

            InputStream inputStream = as.open("countrycode.txt");
            DataInputStream bf = new DataInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bf));
            String st;
            int i = 0;
            while ((st = bufferedReader.readLine()) != null) {

                if (st.length() > 2) {
                    String[] token = st.split("[+]");
                    if (token.length > 1)
                        listcountry.add(new Country(token[0], token[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void openWhatsApp() {
        String url;
        String number = numberedt.getText().toString().trim();
        String prefix = prefixedt.getText().toString().trim();
        String msg = edt.getText().toString().trim();
        Toast.makeText(this, prefix+" " + number, Toast.LENGTH_SHORT).show();
        if (msg != null) {
            url = "https://wa.me/" + prefix + number + "?text=" + msg;
        } else {
            url = "https://wa.me/" + prefix + number;
        }


        try {
            PackageManager pm = getApplicationContext().getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Country {
    String CountryName;
    String CountryCode;

    public Country(String countryName, String countryCode) {
        CountryName = countryName;
        CountryCode = countryCode;
    }


    public String getCountryName() {
        return CountryName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

}