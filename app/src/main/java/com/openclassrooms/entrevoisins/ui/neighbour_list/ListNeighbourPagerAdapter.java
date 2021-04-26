package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.R;

import java.util.Arrays;
import java.util.List;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private List<NeighbourFragment> fragments = Arrays.asList(NeighbourListFragment.newInstance(), NeighbourFavorisFragment.newInstance());
    private Context context;

    public ListNeighbourPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getResources().getString(R.string.Fragment_Neighbour_List);
        } else {
            return context.getResources().getString(R.string.Fragment_Neighbour_Favorites_List);
        }
    }
}