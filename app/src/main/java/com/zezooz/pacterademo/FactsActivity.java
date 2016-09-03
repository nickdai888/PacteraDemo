package com.zezooz.pacterademo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FactsActivity extends AppCompatActivity {
    @BindView(R.id.factListView)  RecyclerView factListView;
    @BindView(R.id.swipe_container)  SwipeRefreshLayout swipContainer;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
