package com.example.gfgcovidvaccine;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Pincode extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pincode() {
    }

    private Button pinButton;
    EditText edt_pin;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RV_Adapter adapter;


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
}
