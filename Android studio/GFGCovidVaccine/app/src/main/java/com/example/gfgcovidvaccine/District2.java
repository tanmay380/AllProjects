package com.example.gfgcovidvaccine;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;


public class District2 extends Fragment implements LocationListener {

    Spinner spinner;
    Spinner districtSpinner;

    ProgressDialog nDialog;

    boolean firsttime = true;

    String districtname, statename;

    ArrayList<String> states = new ArrayList<>();
    ArrayList<String> districts = new ArrayList<>();
    TreeMap<String, Integer> statesMap;
    TreeMap<String, Integer> districtsMap;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RV_Adapter rv_adapter;
    Button selctdate, getLocation;

    LocationManager locationManager;

    ProgressBar progressBar;
    int district_id;

    TextView dateselect;

    ArrayAdapter adapter, adapter1;
    ArrayList<Item_class> item_classes = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public District2() {
    }


    // TODO: Rename and change types and number of parameters
    public static District2 newInstance(String param1, String param2) {
        District2 fragment = new District2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_district2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = getView().findViewById(R.id.spinner);
        districtSpinner = getView().findViewById(R.id.districts);
        progressBar = getView().findViewById(R.id.progressbar1);

        recyclerView = getView().findViewById(R.id.recyclerview1);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        nDialog = new ProgressDialog(getActivity());

        rv_adapter = new RV_Adapter(item_classes);
        recyclerView.setAdapter(rv_adapter);

        selctdate = getView().findViewById(R.id.selectdate);

        statesMap = new TreeMap<>();
        districtsMap = new TreeMap<>();

        states.add("Select Your State");
//        districts.add("Select Your District");

        getLocation = getView().findViewById(R.id.getlocation);
        dateselect = getView().findViewById(R.id.textview);


        fillspinnerArrayStates();

        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, states);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, districts);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_1);
        districtSpinner.setAdapter(adapter1);


        getLocation.setOnClickListener(v -> getLocation());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_classes.clear();
                rv_adapter.notifyDataSetChanged();
                int stateId = statesMap.get(parent.getSelectedItem().toString());
                fillSpinnerArrayDistricts(stateId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_classes.clear();
                rv_adapter.notifyDataSetChanged();
                Log.d("districtid", "onItemSelected: " + parent.getSelectedItem().toString());
                district_id = districtsMap.get(parent.getSelectedItem().toString());
                //setDate(district_id);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("districtid", "onItemSelectedMothinh: " + parent.getSelectedItem().toString());
            }
        });

        selctdate.setOnClickListener(v -> setDate(district_id));
    }

    private void fillspinnerArrayStates() {
        String url = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                        statesMap.put(state_name, state_id);
                    }
                    states.addAll(statesMap.keySet());
                    adapter.notifyDataSetChanged();

                    //district.setSelection(getintDistrictvalue(districts));


                } catch (JSONException e) {
                    Log.d("error", e.toString());
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(
                                getContext())
                                .setCancelable(false)
                                .setTitle("Error")
                                .setMessage("Some problem occurred with Server, \n Try Again Later")
                                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().finish();
                                    }
                                })
                                .show();
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(array);
    }

    private void fillSpinnerArrayDistricts(int stateid) {
        Log.d("district", "fillSpinnerArrayDistricts: " + stateid);
        String url = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/" + stateid;

        districtsMap.clear();
        districts.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        JsonObjectRequest array = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                        JSONArray stateArray = response.getJSONArray("districts");
                        Log.d("statearray", String.valueOf(stateArray.length()));
                        for (int i = 0; i < stateArray.length(); i++) {
                            JSONObject jsonObject = stateArray.getJSONObject(i);
                            int district_id = jsonObject.getInt("district_id");
                            String district_name = jsonObject.getString("district_name");

                            districtsMap.put(district_name, district_id);

                    }

                    districts.addAll(districtsMap.keySet());
                    adapter1.notifyDataSetChanged();

                }
                catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

                if (!districts.contains(districtname)) {
                    districtSpinner.setSelection(0);
                    district_id = districtsMap.get(districtSpinner.getSelectedItem().toString());
                } else {
                    districtSpinner.setSelection(districtsMap.get(districtname));
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "FAIled to get data", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        requestQueue.add(array);

    }


    private void getLocation() {
        // Toast.makeText(getContext(),"GET LOCARION", Toast.LENGTH_SHORT).show();
//        statesMap.clear();
//        districtsMap.clear();

        rv_adapter.notifyDataSetChanged();
        checklocationISEnableORnor();
        nDialog = new ProgressDialog(getActivity()); //Here I get an error: The constructor ProgressDialog(PFragment) is undefined
        nDialog.setMessage("Getting Your Location ");
        nDialog.setTitle("Please Wait");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    500, 0, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void checklocationISEnableORnor() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnbaled = false;
        boolean networkEnable = false;
        try {
            gpsEnbaled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            networkEnable = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!gpsEnbaled && !networkEnable) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Enable Gps Services")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();


        }
    }


    private String setDate(int district_id) {
        Calendar v = Calendar.getInstance();
        int year = v.get(Calendar.YEAR);
        int month = v.get(Calendar.MONTH);
        int day = v.get(Calendar.DAY_OF_MONTH);

        final String[] datestr = new String[1];

        DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                progressBar.setVisibility(View.VISIBLE);
                datestr[0] = String.format("%d-%d-%d", dayOfMonth, (month + 1), year);
                dateselect.setText(datestr[0]);
                getCenters(district_id, datestr[0]);


            }
        }
                , year, month, day);
        datePicker.show();

        return datestr[0];
    }

    private void getCenters(int district_id, String date) {
        Log.d("districtid", "getCenters: " + district_id + "  " + date);
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id=" + district_id + "&date=" + date;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray centerArray = response.getJSONArray("sessions");
                    if (centerArray.length() == 0) {
                        Toast.makeText(getContext(), "No Centers Available at the Moment", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    for (int i = 0; i < centerArray.length(); i++) {
                        JSONObject centerObj = centerArray.getJSONObject(i);
                        String centerName = centerObj.getString("name");
                        String centerAddress = centerObj.getString("address");
                        String centerFromtime = centerObj.getString("from");
                        String centerTotime = centerObj.getString("to");
                        String fee = centerObj.getString("fee_type");
                        int availabilty = centerObj.getInt("available_capacity");
                        String vaccine = centerObj.getString("vaccine");
                        String ageLimit = centerObj.getString("min_age_limit");

                        Item_class item_class = new Item_class(
                                centerName, centerAddress, centerFromtime, centerTotime, fee, ageLimit, vaccine, availabilty
                        );
                        item_classes.add(item_class);
                        rv_adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }

                    // Log.d("Error", )
                } catch (JSONException e) {
                    Log.d("erroe", e.toString());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "FAIled to get data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(jsonObjectRequest);


    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Log.d("locationchanged", "onLocationChanged: ");

            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            statename = addresses.get(0).getAdminArea();
            districtname = addresses.get(0).getLocality();

            spinner.setSelection(getintStatevalue(statename));
            fillSpinnerArrayDistricts(statesMap.get(statename));




            Log.d("districts", "onLocationChanged: " + districtsMap);

            Toast.makeText(getContext(), "LOCATION RECEIVED", Toast.LENGTH_SHORT).show();

            Log.d("districtspinner", districtname);


        } catch (Exception e) {
            e.printStackTrace();
//            nDialog.dismiss();
        }
        nDialog.dismiss();
        locationManager.removeUpdates(this);
    }


    private int getintStatevalue(String state) {
        if (adapter != null) {
            int stateposition = adapter.getPosition(state);
            return stateposition;
        }
        fillSpinnerArrayDistricts(statesMap.get(spinner.getSelectedItem().toString()));
        return 0;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
