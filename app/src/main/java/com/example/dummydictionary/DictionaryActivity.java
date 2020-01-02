package com.example.dummydictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.dummydictionary.util.VerticalSpacingItemDecorator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DictionaryActivity extends AppCompatActivity
        {

            private static final String TAG = "DictionaryActivity";
            private RecyclerView mRecyclerView;
            private FloatingActionButton mFab;
            private SwipeRefreshLayout mSwipeRefresh;
            private Handler mMainThreadHandler;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        mRecyclerView = findViewById(R.id.recyclerView);
        mFab = findViewById(R.id.fab);
        mSwipeRefresh = findViewById(R.id.swipe_refresh);

        mFab.setOnClickListener(this);
        mSwipeRefresh.setOnRefreshListener(this);

        mMainThreadHandler = new Handler(this);
        setupRecyclerView();

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback
            = new ItemTouchHelper.SimpleCallback(0, ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    }


        private void setupRecyclerView() {
                Log.d(TAG, "setupRecyclerView: called.");
            LinearLayoutManager linearLayoutManager
                    = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            VerticalSpacingItemDecorator itemDecorator
                    = new VerticalSpacingItemDecorator(10);
            mRecyclerView.addItemDecoration(itemDecorator);
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView();

        }
    }
