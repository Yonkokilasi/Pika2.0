package com.example.josephinemenge.pika;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecipeDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.recipeImageView) ImageView mImageLabel;
    @Bind(R.id.ingredients)TextView mIngredientsLabel;
    @Bind(R.id.recipeSource)TextView mSourceLabel;
    @Bind(R.id.websiteLink)TextView mWebsiteLabel;
    @Bind(R.id.saveRecipeButton)Button mSaveRecipeButton;
    @Bind(R.id.recipeNameTextView)TextView mNameLabel;
    @Bind(R.id.yieldText)TextView mYieldLabel;
    private Recipe mRecipe;


    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", Parcels.wrap(recipe));
        recipeDetailFragment.setArguments(args);
        return  recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = Parcels.unwrap(getArguments().getParcelable("recipe"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail,container,false);
        ButterKnife.bind(this,view);
        Picasso.with(view.getContext()).load(mRecipe.getImageUrl()).into(mImageLabel);
        mNameLabel.setText(mRecipe.getLabel());
        mSourceLabel.setText("Source :"+mRecipe.getSource());
        mYieldLabel.setText("Serves "+Double.toString(mRecipe.getYield()));
        mIngredientsLabel.setText("Requires "+android.text.TextUtils.join(",", mRecipe.getIngredientLines()));
        mWebsiteLabel.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRecipe.getWebsite()));
            startActivity(webIntent);
        }
    }
}
