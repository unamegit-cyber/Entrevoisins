package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.item_detail_avatar)
    ImageView mDetailAvatar;
    @BindView(R.id.item_detail_name)
    TextView mDetailName;
    @BindView(R.id.item_detail_favoris_button)
    ImageButton mDetailFavorisButton;

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mDetailAvatar = (ImageView) findViewById(R.id.item_detail_avatar);
        mDetailName = (TextView) findViewById(R.id.item_detail_name);
        mDetailFavorisButton = (ImageButton) findViewById(R.id.item_detail_favoris_button);

        Intent intent = getIntent();
        int neighbour_position = Integer.parseInt(intent.getStringExtra("neighbour_position"));

        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        Neighbour neighbour = mNeighbours.get(neighbour_position);

        mDetailName.setText(neighbour.getName());

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(mDetailAvatar);

    }
/*
    public View onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_detail_neighbour, parent, false);

        ImageView mFavorisBtn= (ImageView) findViewById(R.id.item_detail_favoris_button);
        int color = Color.parseColor("#FFD700");
        mFavorisBtn.setColorFilter(color);

        return view;
    }
*/

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}