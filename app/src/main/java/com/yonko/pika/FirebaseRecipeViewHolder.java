package com.yonko.pika;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.yonko.pika.ui.RecipeDetailActivity;
import com.yonko.pika.util.ItemTouchHelperViewHolder;

import org.parceler.Parcels;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import static com.yonko.pika.R.id.recipeImageView;

/**
 * Created by Josephine Menge on 08/06/2017.
 */

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder, View.OnClickListener {
    public ImageView mRecipeImageView;
    View mView;
    private Context mContext;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void BindViewRecipe(Recipe recipe) {
        mRecipeImageView = mView.findViewById(recipeImageView);
        TextView nameTextView = mView.findViewById(R.id.recipeNameTextView);
        TextView sourceTextView = mView.findViewById(R.id.recipeSource);
        TextView yieldTextView = mView.findViewById(R.id.yieldTextView);

        Picasso.with(mContext).load(recipe.getImageUrl()).into(mRecipeImageView);
        nameTextView.setText(recipe.getLabel());
        sourceTextView.setText("Sourced from : " + recipe.getSource());
        yieldTextView.setText("Feeds " + (recipe.getYield()) + " persons");

    }

    @Override
    public void onItemSelected() {
        itemView.animate().alpha(0.2f).scaleX(0.8f).scaleY(0.8f).setDuration(700);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(1000);
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Recipe> recipes = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recipes.add(snapshot.getValue(Recipe.class));
                }

                int itemPostion = getLayoutPosition();
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("position", itemPostion + "");
                intent.putExtra("recipe", Parcels.wrap(recipes));
                mContext.startActivity(intent);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}

