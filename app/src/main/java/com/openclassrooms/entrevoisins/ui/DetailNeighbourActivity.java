package com.openclassrooms.entrevoisins.ui;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.item_detail_avatar)
    ImageView mDetailAvatar;
    @BindView(R.id.item_detail_name)
    TextView mDetailName;
    @BindView(R.id.item_detail_favoris_button)
    ImageButton mDetailFavorisButton;
    @BindView(R.id.item_detail_name2)
    TextView mDetailName2;
    @BindView(R.id.item_detail_adresse)
    TextView mDetailAdresse;
    @BindView(R.id.item_detail_telephone)
    TextView mDetailTelephone;
    @BindView(R.id.item_detail_web)
    TextView mDetailWeb;
    @BindView(R.id.item_detail_description)
    TextView mDetailDescription;

    private Neighbour mNeighbour;
    private NeighbourApiService mApiService;

    private void activeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void setUiData(Neighbour neighbour) {
        mDetailName.setText(neighbour.getName());
        mDetailName2.setText(neighbour.getName());
        mDetailAdresse.setText(neighbour.getAddress());
        mDetailTelephone.setText(neighbour.getPhoneNumber());
        mDetailWeb.setText(neighbour.getWebUrl());
        mDetailDescription.setText(neighbour.getAboutMe());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        NeighbourApiService mApiService = DI.getNeighbourApiService();

        this.activeActionBar();

        Intent intent = getIntent();
        mNeighbour = (Neighbour) intent.getSerializableExtra("neighbour");

        this.setUiData(mNeighbour);

        mDetailFavorisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNeighbour.isFavorite()) {
                    mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_black_24);
                } else {
                    mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_yellow_24);
                }
                mApiService.toggleFavorite(mNeighbour);
            }
        });

        this.showFavorite();

        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mDetailAvatar);

    }

    private void showFavorite() {
        if (mNeighbour.isFavorite()) {
            mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_yellow_24);
        } else {
            mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_black_24);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    
}