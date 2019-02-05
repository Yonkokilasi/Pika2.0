package com.yonko.pika;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yonko.pika.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import static com.yonko.pika.R.id.recipeImageView;

/**
 * Created by Josephine Menge on 08/06/2017.
 */

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
    View mView;
    Context mContext;
    public ImageView mRecipeImageView;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }
    public void  bindRecipe(Recipe recipe) {
        mRecipeImageView= (ImageView) mView.findViewById(recipeImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.recipeNameTextView);
        TextView sourceTextView = (TextView) mView.findViewById(R.id.recipeSource);
        TextView yieldTextView = (TextView) mView.findViewById(R.id.yieldTextView);

        Picasso.with(mContext).load(recipe.getImageUrl()).into(mRecipeImageView);
        nameTextView.setText(recipe.getLabel());
        sourceTextView.setText("Sourced from : "+recipe.getSource());
        yieldTextView.setText("Feeds " + (recipe.getYield()) +" persons");

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

//    @Override
//    public void onClick(View v) {
//        final ArrayList<Recipe> recipes = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    recipes.add(snapshot.getValue(Recipe.class));
//                }
//                int itemPostion = getLayoutPosition();
//                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
//                intent.putExtra("position",itemPostion + "");
//                intent.putExtra("recipe", Parcels.wrap(recipes));
//                mContext.startActivity(intent);
//            }


//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });

    }

