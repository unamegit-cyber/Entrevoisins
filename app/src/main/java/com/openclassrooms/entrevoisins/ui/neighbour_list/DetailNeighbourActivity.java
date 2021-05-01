package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.List;

import butterknife.BindView;

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mApiService = DI.getNeighbourApiService();

        mDetailAvatar = (ImageView) findViewById(R.id.item_detail_avatar);
        mDetailName = (TextView) findViewById(R.id.item_detail_name);
        mDetailFavorisButton = (ImageButton) findViewById(R.id.item_detail_favoris_button);
        mDetailName2 = (TextView) findViewById(R.id.item_detail_name2);
        mDetailAdresse = (TextView) findViewById(R.id.item_detail_adresse);
        mDetailTelephone = (TextView) findViewById(R.id.item_detail_telephone);
        mDetailWeb = (TextView) findViewById(R.id.item_detail_web);
        mDetailDescription = (TextView) findViewById(R.id.item_detail_description);

        Intent intent = getIntent();
        mNeighbour = (Neighbour) intent.getSerializableExtra("neighbour");

        mDetailName.setText(mNeighbour.getName());
        mDetailName2.setText(mNeighbour.getName());
        mDetailAdresse.setText(mNeighbour.getAddress());
        mDetailTelephone.setText(mNeighbour.getPhoneNumber());
        mDetailWeb.setText(mNeighbour.getWebUrl());
        mDetailDescription.setText(mNeighbour.getAboutMe());
        mDetailFavorisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNeighbour.isFavorite()) {
                    mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_black_24);
                    mApiService.getNeighbour(mNeighbour.getId()).isFavorite(false);
                } else {
                    mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_yellow_24);
                    mApiService.getNeighbour(mNeighbour.getId()).isFavorite(true);
                }
            }
        });

        if (mNeighbour.isFavorite()) {
            mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_yellow_24);
        } else {
            mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_black_24);
        }

        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mDetailAvatar);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    
}