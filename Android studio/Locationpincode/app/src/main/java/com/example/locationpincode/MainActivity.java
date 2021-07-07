package com.example.locationpincode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.provider.Settings;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView country, city, pincode, state, address;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grantpermission();

        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        pincode = findViewById(R.id.pin);
        state = findViewById(R.id.state);
        address = findViewById(R.id.address);

        checklocationISEnableORnor();
        getLocation();

    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    500,5, (LocationListener) this);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private void checklocationISEnableORnor() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
            new AlertDialog.Builder(this)
                    .setTitle("Enable Gpa Services")
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);

        country.setText(addresses.get(0).getCountryName());
        state.setText(addresses.get(0).getAdminArea());
        city.setText(addresses.get(0).getLocality());
        pincode.setText(addresses.get(0).getPostalCode());
        address.setText(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
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