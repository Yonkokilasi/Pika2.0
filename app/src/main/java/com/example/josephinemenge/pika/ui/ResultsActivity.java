package com.example.josephinemenge.pika.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.josephinemenge.pika.R;
import com.example.josephinemenge.pika.Recipe;
import com.example.josephinemenge.pika.service.EdmamService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity {
   @Bind(R.id.recipeTextView) TextView mRecipeTextView;
    @Bind(R.id.recipeListView) ListView mListView;
    public static final String TAG = ResultsActivity.class.getSimpleName();
    public ArrayList<Recipe> mRecipes = new ArrayList<>();

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
                if(response.isSuccessful()) {
                    Log.v(TAG,jsonData);
                    mRecipes = edmamService.processResults(response);
                    ResultsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] recipeNames = new String[mRecipes.size()];
                            for (int i = 0; i <recipeNames.length; i++) {
                                recipeNames[i]= mRecipes.get(i).getLabel();
                            }
                            ArrayAdapter adapter = new ArrayAdapter(ResultsActivity.this,android.R.layout.simple_list_item_1,recipeNames);
                            mListView.setAdapter(adapter);
                            for (Recipe recipe :mRecipes) {
                                Log.d(TAG,"Name:" + recipe.getLabel());
                                Log.d(TAG,"Image url:"+ recipe.getImageUrl());
                                Log.d(TAG,"Source:" + recipe.getSource());
                                Log.d(TAG,"Ingredients:" +android.text.TextUtils.join(",", recipe.getIngredientLines()));
                                Log.d(TAG,"Yield:" + recipe.getYield());
                                Log.d(TAG,"Yield:" + Double.toString(recipe.getYield()));
                                Log.d(TAG,"Website:" + recipe.getWebsite());
                            }

                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });
    }

}
