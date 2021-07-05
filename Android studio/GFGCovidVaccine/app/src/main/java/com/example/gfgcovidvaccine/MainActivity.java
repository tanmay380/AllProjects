package com.example.gfgcovidvaccine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import static android.app.DatePickerDialog.*;

public class MainActivity extends AppCompatActivity {

    private Button pinButton;
    EditText edt_pin;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RV_Adapter adapter;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<Item_class> item_classes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinButton = findViewById(R.id.pinbuton);
        edt_pin = findViewById(R.id.pinedit);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressbar);

        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RV_Adapter(item_classes);

        recyclerView.setAdapter(adapter);


        pinButton.setOnClickListener(v -> searchPin());

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

            DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray centerArray= response.getJSONArray("sessions");
                    if (centerArray.length() == 0)
                        Toast.makeText(getApplicationContext(), "No Centers Available at the Moment", Toast.LENGTH_SHORT).show();
                   for (int i=0;i<centerArray.length();i++){
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
                               centerName,centerAddress,centerFromtime,centerTotime,fee,ageLimit,vaccine,availabilty
                       );
                       item_classes.add(item_class);
                       adapter.notifyDataSetChanged();
                       progressBar.setVisibility(View.GONE);
                   }

                  // Log.d("Error", )
                }catch (JSONException e){
                    Log.d("erroe",e.toString());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"FAIled to get data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }

}