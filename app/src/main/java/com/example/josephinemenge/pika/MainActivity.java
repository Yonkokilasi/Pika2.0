
package com.example.josephinemenge.pika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josephinemenge.pika.ui.RecipeListActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedRecipeReference;
    private DatabaseReference mSearchedHealthReference;


    @Bind(R.id.FRecipeButton)
    Button mFRecipeButton;
    @Bind(R.id.fType)
    EditText mfType;
    @Bind(R.id.health)
    EditText mHealth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFRecipeButton.setOnClickListener(this);
        Log.d("Find recipe clicked", "Main activity");
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        mSearchedRecipeReference = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_RECIPE);
        mSearchedHealthReference = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_HEALTH);
    }

    @Override
    public void onClick(View v) {
        if (v == mFRecipeButton) {
            String health = mHealth.getText().toString();
            String foodType = mfType.getText().toString();
//            if (!(foodType).equals("")){
//                addToSharedPreferences(foodType);
//            }
            String Health = mHealth.getText().toString();
            Toast.makeText(MainActivity.this, "Finding Recipe", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            intent.putExtra("foodType", foodType);
            intent.putExtra("Health", Health);
            saveRecipeToFireBase(foodType);
            saveHealthToFireBase(health);
            startActivity(intent);
        }
    }
    private void saveRecipeToFireBase(String foodType) {
        mSearchedRecipeReference.push().setValue(foodType);
    }
    private void saveHealthToFireBase(String Health) {
        mSearchedHealthReference.push().setValue(Health);
    }
//    private void addToSharedPreferences(String foodType) {
//        mEditor.putString(Constants.PREFERENCES_FOOD_TYPE,foodType).apply();
//    }
}

