package com.example.josephinemenge.pika;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Josephine Menge on 08/06/2017.
 */

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void  bindRecipe(Recipe recipe) {
        ImageView recipeImageView = (ImageView) mView.findViewById(R.id.recipeImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.recipeNameTextView);
        TextView sourceTextView = (TextView) mView.findViewById(R.id.recipeSource);
        TextView yieldTextView = (TextView) mView.findViewById(R.id.yieldTextView);

    }
}
