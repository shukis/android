package com.example.pavel.swipe;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Pavel on 11.02.2017.
 */

public class PlayerPageAdapter extends FragmentPagerAdapter {

    public static ArrayList<String> newPlayers = new ArrayList<>();
    static String[] players;


    public PlayerPageAdapter(FragmentManager fm, Context context) {
        super(fm);

        Resources resources = context.getResources();

        players = resources.getStringArray(R.array.players);

    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putString(PlayerFragment.PLAYER, players[position]);

        PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(bundle);

        return playerFragment;
    }
    public static void addNewPlayer(String player){
        newPlayers.add(player);
        Log.d("count", "oooooooooooo "+String.valueOf(newPlayers.size()));
    }



    @Override
    public CharSequence getPageTitle(int position) {
        if(newPlayers.size()==0){
        return players[position];
        }else{
            newPlayers.add(players[0]);
            newPlayers.add(players[1]);
            return newPlayers.get(position);
        }
    }


    @Override
    public int getCount() {
        Log.d("count", String.valueOf(newPlayers.size()));
        if(newPlayers.size()==0){
            return players.length;
        }else{
            notifyDataSetChanged();
            Log.d("count"," jebaaa");
            return 3;
        }
    }
}
