package com.example.pavel.swipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    EditText player1, player2, player3;
    CheckBox addPlayer;
    Button confirmNewPlayer, start;
    int clicks = 0;
    ArrayList<String> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");

        player1 = (EditText)findViewById(R.id.player1);
        player2 = (EditText)findViewById(R.id.player2);
        player3 = (EditText)findViewById(R.id.player3);

        addPlayer = (CheckBox) findViewById(R.id.checkBox);

        confirmNewPlayer = (Button) findViewById(R.id.confirmPlayer);
        start = (Button)findViewById(R.id.startGame);
        confirmNewPlayer.setOnClickListener(this);
        start.setOnClickListener(this);

        player1.setVisibility(View.INVISIBLE);
        player2.setVisibility(View.INVISIBLE);
        player3.setVisibility(View.INVISIBLE);
        confirmNewPlayer.setVisibility(View.INVISIBLE);

        addPlayer.setOnCheckedChangeListener(new  CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    player1.setVisibility(View.VISIBLE);
                    confirmNewPlayer.setVisibility(View.VISIBLE);

                }else{
                    player1.setText("");
                    player2.setText("");
                    player3.setText("");
                    player2.setVisibility(View.INVISIBLE);
                    player1.setVisibility(View.INVISIBLE);
                    player3.setVisibility(View.INVISIBLE);
                    confirmNewPlayer.setVisibility(View.INVISIBLE);
                }

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.confirmPlayer:
                clicks++;
                if(clicks==1){
                    player2.setVisibility(View.VISIBLE);
                    players.add(player1.getText().toString());
                }else if(clicks==2){
                    player3.setVisibility(View.VISIBLE);
                    players.add(player2.getText().toString());
                }else if(clicks == 3){
                    confirmNewPlayer.setVisibility(View.INVISIBLE);
                    players.add(player3.getText().toString());
                    clicks=0;
                }
                break;
            case R.id.startGame:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }
}
