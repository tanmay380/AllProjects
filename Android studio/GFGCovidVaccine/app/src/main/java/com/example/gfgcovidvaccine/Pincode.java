package com.example.gfgcovidvaccine;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static androidx.core.content.ContextCompat.getSystemService;

public class Pincode extends Fragment implements LocationListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pincode() {
    }

    ProgressDialog dialog;

    private Button pinButton;
    EditText edt_pin;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RV_Adapter adapter;

    ImageButton imageButton;

    LocationManager locationManager;


    RecyclerView.LayoutManager layoutManager;
    ArrayList<Item_class> item_classes = new ArrayList<>();


    // TODO: Rename and change types and number of parameters
    public static Pincode newInstance(String param1, String param2) {
        Pincode fragment = new Pincode();
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
        return inflater.inflate(R.layout.fragment_pincode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pinButton = getView().findViewById(R.id.pinbuton);
        edt_pin = getView().findViewById(R.id.pinedit);
        recyclerView = getView().findViewById(R.id.recyclerview);
        progressBar = getView().findViewById(R.id.progressbar);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RV_Adapter(item_classes);

        dialog = new ProgressDialog(getActivity());

        recyclerView.setAdapter(adapter);

        imageButton = getView().findViewById(R.id.location);

        grantpermission();
        checklocationISEnableORnor();


        pinButton.setOnClickListener(v -> searchPin());
        imageButton.setOnClickListener(v->getLocation());

    }





    private void getLocation() {
        dialog.setMessage("Geting Location, Please Wait");
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);
        dialog.show();
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    500, 0, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void checklocationISEnableORnor() {
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

    private void grantpermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }else
        {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }





    private void searchPin() {
        edt_pin.onEditorAction(EditorInfo.IME_ACTION_DONE);
        String pin = edt_pin.getText().toString();
        if (pin.length() != 6) {
            edt_pin.setError("Enter a Valid PinCode");
        } else {
            item_classes.clear();
            Calendar v = Calendar.getInstance();
            int year = v.get(Calendar.YEAR);
            int month = v.get(Calendar.MONTH);
            int day = v.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    progressBar.setVisibility(View.VISIBLE);
                    String datestr = String.format("%d-%d-%d", dayOfMonth, (month + 1), year);
//                    Log.e("dateshow", "onDateSet: "+datestr);
                    getAppointmentDetails(pin, datestr);


                }
            }
                    , year, month, day);
            datePicker.show();
        }
    }

    private void getAppointmentDetails(String pincode, String date) {
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=" + pincode + "&date=" + date;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray centerArray = response.getJSONArray("sessions");
                    if (centerArray.length() == 0)
                        Toast.makeText(getContext(), "No Centers Available at the Moment", Toast.LENGTH_SHORT).show();
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
                        adapter.notifyDataSetChanged();
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
        Toast.makeText(getContext(), "LOCATION ISIDE", Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


            edt_pin.setText(addresses.get(0).getPostalCode());
            locationManager.removeUpdates(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
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
