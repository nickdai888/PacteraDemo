package com.zezooz.pacterademo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zezooz.pacterademo.mvp.model.Fact;
import com.zezooz.pacterademo.mvp.presenter.CountryPresenter;
import com.zezooz.pacterademo.mvp.view.CountryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FactsActivity extends AppCompatActivity implements CountryView {
    @BindView(R.id.factListView)
    RecyclerView factListView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipContainer;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private ActionBar actionBar;
    private CountryPresenter presenter;
    private FactListAdapter factListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();

        initFactListView();

        swipContainer.setOnRefreshListener(mOnRefreshListener);
        swipContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        presenter = new CountryPresenter();
        presenter.attachView(this);
        presenter.loadData();
    }


    private void initFactListView() {
        factListView.setLayoutManager(new LinearLayoutManager(this));
        factListAdapter = new FactListAdapter();
        factListView.setAdapter(factListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }


    @Override
    public void showLoading(boolean loading) {
        if (loading) {
            swipContainer.setVisibility(View.GONE);
            swipContainer.setRefreshing(true);
        } else {
            progressBar.setVisibility(View.GONE);
            swipContainer.setVisibility(View.VISIBLE);
            swipContainer.setRefreshing(false);
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFacts(List<Fact> facts) {
        factListAdapter.setFacts(facts);
    }

    @Override
    public void showTitle(String title) {
        actionBar.setTitle(title);
    }


    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            presenter.loadData();
        }
    };


}
