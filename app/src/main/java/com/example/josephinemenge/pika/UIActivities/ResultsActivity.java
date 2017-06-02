package com.example.josephinemenge.pika.UIActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.josephinemenge.pika.R;
import com.example.josephinemenge.pika.Service.EdmamService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = ResultsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String foodType = intent.getStringExtra("foodType");
        String ingredients = intent.getStringExtra("ingredients");
        getRecipes(foodType,ingredients);
    }
    private void getRecipes(String foodType, String ingredients) {
        final EdmamService edmamService = new EdmamService();
        edmamService.findRecipes(foodType, ingredients, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("Results Activity","Failed to mke api call");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            try {
                String jsonData = response.body().string();
                Log.v(TAG, jsonData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });
    }

}
