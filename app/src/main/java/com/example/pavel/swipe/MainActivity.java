package com.example.pavel.swipe;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private ViewPager mViewPager;
    PlayerPageAdapter adapter;
    private int backPressedTimes = 0;
    private MyCounter mCounter;

    private class MyCounter extends CountDownTimer {

        public MyCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("on tick>>>>>>",millisUntilFinished+">>>>>");
        }

        @Override
        public void onFinish() {
            backPressedTimes = 0;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new PlayerPageAdapter(getSupportFragmentManager(),getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Score");

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabHistory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Statistics.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backPressedTimes = 0;
    }

    @Override
    public void onBackPressed() {
        backPressedTimes++;
        boolean showToast = true;
        if(backPressedTimes==2){
            backPressedTimes=0;
            showToast = false;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        }
        if(showToast) {
            Toast toast = Toast.makeText(this, "Are you sure you want to exit? Press button once more to confirm.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            mCounter=new MyCounter(2000, 100);
            mCounter.start();
        }
    }



   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.actionSettings:
                Intent main = new Intent(this, Settings.class);
                startActivity(main);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
