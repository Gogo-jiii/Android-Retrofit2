package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnGetRequest, btnPostRequest;
    TextView txtResultValue;
    ProgressBar progressBar;
    RetrofitBuilder retrofitBuilder = RetrofitBuilder.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetRequest = findViewById(R.id.btnGetRequest);
        btnPostRequest = findViewById(R.id.btnPostRequest);
        txtResultValue = findViewById(R.id.txtResultValue);
        progressBar = findViewById(R.id.progressBar);

        btnGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                retrofitBuilder.build(retrofitBuilder.BASE_URL_GET);

                Call<List<GetRequestModel>> call =
                        retrofitBuilder.callApi().getHeros();

                call.enqueue(new Callback<List<GetRequestModel>>() {
                    @Override public void onResponse(Call<List<GetRequestModel>> call,
                                                     Response<List<GetRequestModel>> response) {
                        txtResultValue.setText(response.body().toString());
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override public void onFailure(Call<List<GetRequestModel>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });

        btnPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                retrofitBuilder.build(retrofitBuilder.BASE_URL_POST);

                PostRequestModel postRequestModel = new PostRequestModel("IT wala");
                Call<PostResponseModel> call = retrofitBuilder.callApi().postHeros(postRequestModel);

                call.enqueue(new Callback<PostResponseModel>() {
                    @Override public void onResponse(Call<PostResponseModel> call,
                                                     Response<PostResponseModel> response) {
                        txtResultValue.setText(response.body().toString());
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override public void onFailure(Call<PostResponseModel> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}