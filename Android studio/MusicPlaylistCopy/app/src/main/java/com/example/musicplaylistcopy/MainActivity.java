package com.example.musicplaylistcopy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "35214c2d862e4409a137c78c64a5e670";
//    private static final String REDIRECT_URI = "tanmay380.github.io";
    private static final int REQUEST_CODE = 1337;
    private static final String TAG = "tanmay";
//    private SpotifyAppRemote mSpotifyAppRemote;

    private Button authButton;
    private TextView authStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        authButton = findViewById(R.id.authButton);
        authStatus = findViewById(R.id.authStatus);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 Request code will be used to verify if result comes from the login activity. Can be set to any integer.
                AuthorizationRequest.Builder builder =
                        new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, getRedirectUri().toString());

                builder.setScopes(new String[]{"streaming"});
                AuthorizationRequest request = builder.build();

                AuthorizationClient.openLoginActivity(MainActivity.this, REQUEST_CODE, request);

            }
        });
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
                    Log.d(TAG, "onActivityResult: " + response.getAccessToken());
                    authStatus.setText("Authentication Succesfull");
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    Log.d(TAG, "onActivityResult: " + response.getError());
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }
}