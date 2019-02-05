package com.yonko.pika.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.yonko.pika.Constants;
import com.yonko.pika.FirebaseRecipeViewHolder;
import com.yonko.pika.R;
import com.yonko.pika.Recipe;
import com.yonko.pika.adapters.FirebaseRecipeListAdapter;
import com.yonko.pika.util.OnStartDragListener;
import com.yonko.pika.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRecipeListActivity extends AppCompatActivity implements OnStartDragListener{

    private DatabaseReference mRecipeReference;
    private FirebaseRecipeListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        setUpFireBaseAdapter();
        Toast.makeText(SavedRecipeListActivity.this,"Tip :Swipe left to delete",Toast.LENGTH_SHORT).show();
        Toast.makeText(SavedRecipeListActivity.this,"Tip : You can rearrange them by dragging up or down",Toast.LENGTH_SHORT).show();
    }
    private void setUpFireBaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_RECIPES)
                .child(uid).orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseRecipeListAdapter(Recipe.class, R.layout.recipe_list_item_drag, FirebaseRecipeViewHolder.class, query, this, this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
