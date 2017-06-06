
package com.example.josephinemenge.pika;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josephinemenge.pika.ui.RecipeListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.FRecipeButton)
    Button mFRecipeButton;
    @Bind(R.id.ingredients)
    EditText mIngredients;
    @Bind(R.id.fType)
    EditText mfType;
    @Bind(R.id.health)
    EditText mHealth;
    @Bind(R.id.healthLabel)
    EditText mHealthLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFRecipeButton.setOnClickListener(this);
        Log.d("Find recipe clicked", "Main activity");
    }

    @Override
    public void onClick(View v) {
        if (v == mFRecipeButton) {
            String ingredients = mIngredients.getText().toString();
            String foodType = mfType.getText().toString();
            String Health = mHealth.getText().toString();
            String HealthLabel = mHealthLabel.getText().toString();
            Toast.makeText(MainActivity.this, "Finding Recipe", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            intent.putExtra("ingredients", ingredients);
            intent.putExtra("foodType", foodType);
            intent.putExtra("Health", Health);
            intent.putExtra("healthLabel", HealthLabel);
            startActivity(intent);
        }
    }
}

