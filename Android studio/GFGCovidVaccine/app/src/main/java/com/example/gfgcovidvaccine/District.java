package com.example.gfgcovidvaccine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static android.app.DatePickerDialog.*;

public class District extends AppCompatActivity {

    Spinner spinner;
    Spinner district;

    ArrayList<String> states = new ArrayList<>();
    ArrayList<String> districts = new ArrayList<>();
    Map<Integer, String> statesMap;
    ArrayAdapter adapter, adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        spinner = findViewById(R.id.spinner);
        district = findViewById(R.id.districts);



        fillspinnerArrayStates1();

        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, states);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, statesMap.values().toArray());
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_1);
        district.setAdapter(adapter1);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                districts.clear();
                getDistricts(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });

    }

    private void getDistricts(int stateid) {
        String url = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/"+(stateid+1);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest array = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray stateArray = response.getJSONArray("districts");
                     Log.d("statearray", String.valueOf(stateArray.length()));
                    for (int i = 0; i < stateArray.length()+1; i++) {
                        JSONObject jsonObject = stateArray.getJSONObject(i);
                        String state_name = jsonObject.getString("district_name");
                        districts.add(state_name);
                    }

                    adapter1.notifyDataSetChanged();



                } catch (JSONException e) {
                    Log.d("error", e.toString());
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(array);
    }

    private void fillspinnerArrayStates() {
        String url = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest array = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray stateArray = response.getJSONArray("states");
                    // Log.d("statearray", String.valueOf(stateArray));
                    for (int i = 0; i < stateArray.length(); i++) {
                        JSONObject jsonObject = stateArray.getJSONObject(i);
                        String state_name = jsonObject.getString("state_name");
                        states.add(state_name);
                    }
                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    Log.d("error", e.toString());
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(array);


    }

    private void fillspinnerArrayStates1() {
        String url = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest array = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray stateArray = response.getJSONArray("states");
                    // Log.d("statearray", String.valueOf(stateArray));
                    for (int i = 0; i < stateArray.length(); i++) {
                        JSONObject jsonObject = stateArray.getJSONObject(i);
                        String state_name = jsonObject.getString("state_name");
                        int state_id = jsonObject.getInt("state_id");
                        //states.add(state_name);
                        statesMap.put(state_id,state_name);
                    }
                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    Log.d("error", e.toString());
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(array);


    }

}