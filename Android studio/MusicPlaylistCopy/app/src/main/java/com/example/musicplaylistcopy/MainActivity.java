package com.example.musicplaylistcopy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplaylistcopy.recyclerView.Playlist;
import com.example.musicplaylistcopy.recyclerView.PlaylistRecyclerView;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PlaylistRecyclerView.ItemClickListener {

    private static final String CLIENT_ID = "35214c2d862e4409a137c78c64a5e670";
    private static final int REQUEST_CODE = 1337;
    private static final String TAG = "tanmay";
    private static String mAccessToken;

    private Button authButton, accountDetails;
    private TextView authStatus;
    private OkHttpClient client = new OkHttpClient();
    private Call mCall;

    PlaylistRecyclerView adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        authButton = findViewById(R.id.authButton);
        authStatus = findViewById(R.id.authStatus);
        accountDetails = findViewById(R.id.accountDetails);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        authButton.setOnClickListener(this);
        accountDetails.setOnClickListener(this);
    }

    private Uri getRedirectUri() {
        Uri uri = new Uri.Builder()
                .scheme("myapp")
                .authority("com.example")
                .build();
        Log.d(TAG, "getRedirectUri: " + uri.toString());
        return uri;
    }


    @SuppressLint("SetTextI18n")
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//
//        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    Log.i(TAG, "onActivityResult: " + response.getAccessToken());
                    mAccessToken = response.getAccessToken();
                    authStatus.setText("Authentication Succesfull");
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    Log.e(TAG, "onActivityResult: " + response.getError());
                    Log.e(TAG, "onActivityResult: " + response.describeContents());

                    authStatus.setText("Authentication error");
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.authButton)
            authButton();
        else if (v.getId() == R.id.accountDetails)
            accountDetails();


    }

    private void accountDetails() {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();

        if (mCall != null) {
            mCall.cancel();
        }

        mCall = client.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.message().equals("OK")){
                    return;
                }

                Log.i(TAG, "onResponse: " + response.message());
                try {
                    final JSONObject jsonObject = new JSONObject(response.body().string());
                    Log.i(TAG, "onResponse: " + jsonObject.toString(3));
                    Log.i(TAG, "onResponse: " + response.message());
                    Log.i(TAG, "onResponse: " + jsonObject.get("id"));
                    getPlaylist((String) jsonObject.get("id"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    private void getPlaylist(String id) {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/users/" + id + "/playlists")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();

        if (mCall != null) {
            mCall.cancel();
        }

        mCall = client.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
//                        Log.i(TAG, "onResponse: " + jsonObject.toString(3));
                    ArrayList<Playlist> playlists =new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        String link = jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("external_urls").getString("spotify");
                        String name = jsonObject.getJSONArray("items").getJSONObject(i).getString("name");
                        String id = jsonObject.getJSONArray("items").getJSONObject(i).getString("id");
                        Log.i(TAG, "onResponse: " + id);
                        Log.i(TAG, "onResponse: " + link);
                        playlists.add(new Playlist(id, name, link));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new PlaylistRecyclerView(playlists);
                            adapter.setClickListener(MainActivity.this);
                            recyclerView.setAdapter(adapter);
                        }
                    });

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void authButton() {
        AuthorizationRequest.Builder builder =
                new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, getRedirectUri().toString());

        builder.setScopes(new String[]{"streaming"});
        builder.setShowDialog(true);

        AuthorizationRequest request = builder.build();
        AuthorizationClient.openLoginActivity(MainActivity.this, REQUEST_CODE, request);
    }

    @Override
    public void onItemClick(View v, int position) {
        getMusicFromPlaylist(position);
    }

    private void getMusicFromPlaylist(int position) {
        Log.d(TAG, "getMusicFromPlaylist: " +adapter.getItem(position).getPlaylistId());
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/playlists/" + adapter.getItem(position).getPlaylistId() + "/tracks")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();

        if (mCall != null) {
            mCall.cancel();
        }

        mCall = client.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " +e );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int total = Integer.parseInt(jsonObject.get("total").toString());
                    Log.i(TAG, "onResponse: " + total);
                    for (int i = 0; i < 100; i++) {
                        Log.i(TAG, "onResponse: " + jsonObject.getJSONArray("items").getJSONObject(i).getJSONObject("track").get("name"));

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}

