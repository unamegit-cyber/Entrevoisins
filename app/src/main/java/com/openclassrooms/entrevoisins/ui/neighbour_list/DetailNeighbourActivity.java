package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;

public class DetailNeighbourActivity extends AppCompatActivity {
/*
    @BindView(R.id.item_detail_avatar)
    ImageView mDetailAvatar;
    @BindView(R.id.item_detail_name)
    TextView mDetailName;
    @BindView(R.id.item_detail_favoris_button)
    ImageButton mDetailFavorisButton;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
/*
        mDetailAvatar = (ImageView) findViewById(R.id.item_detail_avatar);
        mDetailName = (TextView) findViewById(R.id.item_detail_name);
        mDetailFavorisButton = (ImageButton) findViewById(R.id.item_detail_favoris_button);

 */
    }

}