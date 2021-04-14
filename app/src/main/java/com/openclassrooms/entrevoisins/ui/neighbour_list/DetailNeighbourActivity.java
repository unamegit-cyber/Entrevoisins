package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        mDetailName2 = (TextView) findViewById(R.id.item_detail_name2);
        mDetailAdresse = (TextView) findViewById(R.id.item_detail_adresse);
        mDetailTelephone = (TextView) findViewById(R.id.item_detail_telephone);
        mDetailWeb = (TextView) findViewById(R.id.item_detail_web);
        mDetailDescription = (TextView) findViewById(R.id.item_detail_description);

        Intent intent = getIntent();
        int neighbour_position = Integer.parseInt(intent.getStringExtra("neighbour_position"));

        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        Neighbour neighbour = mNeighbours.get(neighbour_position);

        mDetailName.setText(neighbour.getName());
        mDetailName2.setText(neighbour.getName());
        mDetailAdresse.setText(neighbour.getAddress());
        mDetailTelephone.setText(neighbour.getPhoneNumber());
        mDetailWeb.setText("www.facebook.fr/" + neighbour.getName().toLowerCase());
        mDetailDescription.setText(neighbour.getAboutMe());

        mDetailFavorisButton.setImageResource(R.drawable.ic_baseline_star_yellow_24);

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