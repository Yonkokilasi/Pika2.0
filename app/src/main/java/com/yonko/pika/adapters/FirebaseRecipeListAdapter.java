package com.yonko.pika.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.yonko.pika.FirebaseRecipeViewHolder;
import com.yonko.pika.Recipe;
import com.yonko.pika.ui.RecipeDetailActivity;
import com.yonko.pika.util.ItemTouchHelperAdapter;
import com.yonko.pika.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import androidx.core.view.MotionEventCompat;

/**
 * Created by bubbles on 6/13/17.
 */

public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnstartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Recipe> mRecipes = new ArrayList<>();


    public FirebaseRecipeListAdapter(Class<Recipe> modelClass, int modelLayout, Class<FirebaseRecipeViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnstartDragListener = onStartDragListener;
        mContext = context;
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mRecipes.add(dataSnapshot.getValue(Recipe.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mRecipes, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mRecipes.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void populateViewHolder(final FirebaseRecipeViewHolder viewHolder, Recipe model, int position) {
        viewHolder.BindViewRecipe(model);
        viewHolder.mRecipeImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
                    public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnstartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("recipes", Parcels.wrap(mRecipes));
                mContext.startActivity(intent);
            }
        });
    }

    private void setIndexInFirebase() {
        for (Recipe recipe : mRecipes) {
            int index = mRecipes.indexOf(recipe);
            DatabaseReference ref = getRef(index);
            recipe.setIndex(Integer.toString(index));
            ref.setValue(recipe);
        }
    }
}
