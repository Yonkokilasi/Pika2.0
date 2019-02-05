package com.yonko.pika;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.recipeImageView)
    ImageView mImageLabel;
    @BindView(R.id.ingredients)
    TextView mIngredientsLabel;
    @BindView(R.id.recipeSource)
    TextView mSourceLabel;
    @BindView(R.id.websiteLink)
    TextView mWebsiteLabel;
    @BindView(R.id.saveRecipeButton)
    Button mSaveRecipeButton;
    @BindView(R.id.recipeNameTextView)
    TextView mNameLabel;
    @BindView(R.id.yieldText)
    TextView mYieldLabel;
    private Recipe mRecipe;


    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipe = Parcels.unwrap(getArguments().getParcelable("recipe"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext()).load(mRecipe.getImageUrl()).into(mImageLabel);
        mNameLabel.setText(mRecipe.getLabel());
        mSourceLabel.setText("Sourced from : " + mRecipe.getSource());
        mYieldLabel.setText("Yield " + (mRecipe.getYield()));
        mIngredientsLabel.setText("Ingredients Required are " + android.text.TextUtils.join(",", mRecipe.getIngredientLines()));
        mSaveRecipeButton.setOnClickListener(this);
        mWebsiteLabel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRecipe.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mSaveRecipeButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user != null ? user.getUid() : "";
            DatabaseReference recipeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES).child(uid);
            DatabaseReference pushRef = recipeReference.push();
            String pushId = pushRef.getKey();
            mRecipe.setPushId(pushId);
            pushRef.setValue(mRecipe);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }
    }
}
