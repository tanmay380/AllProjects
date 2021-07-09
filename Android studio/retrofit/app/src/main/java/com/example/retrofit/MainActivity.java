package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi  = retrofit.create(JsonPlaceHolderApi.class);

        //getPosts();
//       getComments();
//        createpost();
    }

    private void createpost() {
//        Post post = new Post(23, "new Title", "New Text");
        Call<Post> call = jsonPlaceHolderApi.createPost(23,"NEW TITLE","NEW TEXT");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textView.setText("COde: " + response.code());
                    return;
                }

                Post postresponse = response.body();
                String content="";
                content+="Code:" + response.code() + "\n";
                content += "ID:" + postresponse.getId() +"\n";
                content += "User ID:" + postresponse.getUserid() +"\n";
                content += "Title:" + postresponse.getTitle() +"\n";
                content += "Body:" + postresponse.getText() +"\n\n";

                textView.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });


    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()){
                    textView.setText("COde: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();
                Log.d("response", String.valueOf(response.body()));
//                for (Comment comment: comments){
//                    String content="";
//                    content += "ID:" + comment.getId() +"\n";
//                    content += "User ID:" + comment.getPostId() +"\n";
//                    content += "Email:" + comment.getEmail() +"\n";
//                    content += "Body:" + comment.getBody() +"\n\n";
//
//                    textView.append(content);
//
//                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textView.setText("COde: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post: posts){
                    String content="";
                    content += "ID:" + post.getId() +"\n";
                    content += "User ID:" + post.getUserid() +"\n";
                    content += "Title:" + post.getTitle() +"\n";
                    content += "Body:" + post.getText() +"\n\n";

                    textView.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }
}