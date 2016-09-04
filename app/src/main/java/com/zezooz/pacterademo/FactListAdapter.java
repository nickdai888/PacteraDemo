package com.zezooz.pacterademo;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zezooz.pacterademo.mvp.model.Fact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nick on 16/9/4.
 */

public class FactListAdapter extends RecyclerView.Adapter<FactItemViewholder> {

    private List<Fact> facts;

    public FactListAdapter() {
        facts = new ArrayList<Fact>();
    }

    public void setFacts(List<Fact> list) {
        facts.clear();
        facts.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    @Override
    public FactItemViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FactItemViewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fact_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(FactItemViewholder factViewholder, int i) {
        Fact fact = facts.get(i);
        factViewholder.titleView.setText(fact.getTitle());
        factViewholder.descriptionView.setText(fact.getDescription());

        String imageUrl = fact.getImageHref();
        if (TextUtils.isEmpty(imageUrl)) {
            factViewholder.imageView.setVisibility(View.GONE);
        } else {
            factViewholder.imageView.setVisibility(View.VISIBLE);
            Glide.with(factViewholder.imageView.getContext())
                    .load(fact.getImageHref())
                    .centerCrop()
                    .error(R.drawable.error)
                    .into(factViewholder.imageView);
        }
    }
}

class FactItemViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.titleView)
    TextView titleView;
    @BindView(R.id.descriptionView)
    TextView descriptionView;
    @BindView(R.id.imageView)
    ImageView imageView;

    public FactItemViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 16/9/5
            }
        });
    }
}


