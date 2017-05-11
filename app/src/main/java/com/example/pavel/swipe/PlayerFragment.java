package com.example.pavel.swipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Pavel on 11.02.2017.
 */

public class PlayerFragment extends Fragment implements View.OnClickListener {
    public static final String PLAYER = "playerKey";
    public static final String SCORE = "scoreKey";
    ImageButton ace, king, queen, jack, ten, nine, eight, seven, six;
    TextView aceQuantity, kingQuantity, queenQuantity, jackQuantity, tenQuantity, nineQuantity, eightQuantity, sevenQuantity, sixQuantity, scoreTextView;
    ArrayList<Integer> points = new ArrayList<>();

    int aceCount = 0,kingCount = 0, queenCount = 0, jackCount = 0, tenCount = 0, nineCount = 0, eightCount = 0, sevenCount = 0, sixCount = 0;


    Button undo, confirm;

    String confirmedScore = "0";
    String player;


    int thisRowScore = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player, container, false);


        undo = (Button)view.findViewById(R.id.undo);
        confirm = (Button) view.findViewById(R.id.confirm);

        ace = (ImageButton) view.findViewById(R.id.imageAce);
        king = (ImageButton) view.findViewById(R.id.imageKing);
        queen = (ImageButton) view.findViewById(R.id.imageQueen);
        jack = (ImageButton) view.findViewById(R.id.imageJack);
        ten = (ImageButton) view.findViewById(R.id.imageTen);
        nine = (ImageButton) view.findViewById(R.id.imageNine);
        eight = (ImageButton) view.findViewById(R.id.imageEight);
        seven = (ImageButton) view.findViewById(R.id.imageSeven);
        six = (ImageButton) view.findViewById(R.id.imageSix);

        scoreTextView = (TextView) view.findViewById(R.id.textViewScore);
        aceQuantity = (TextView) view.findViewById(R.id.quantityAce);
        kingQuantity = (TextView) view.findViewById(R.id.quantityKing);
        queenQuantity = (TextView) view.findViewById(R.id.quantityQueen);
        jackQuantity = (TextView) view.findViewById(R.id.quantityJack);
        tenQuantity = (TextView) view.findViewById(R.id.quantityTen);
        nineQuantity = (TextView) view.findViewById(R.id.quantityNine);
        eightQuantity = (TextView) view.findViewById(R.id.quantityEight);
        sevenQuantity = (TextView) view.findViewById(R.id.quantitySeven);
        sixQuantity = (TextView) view.findViewById(R.id.quantitySix);

        ace.setOnClickListener(this);
        king.setOnClickListener(this);
        queen.setOnClickListener(this);
        jack.setOnClickListener(this);
        ten.setOnClickListener(this);
        nine.setOnClickListener(this);
        eight.setOnClickListener(this);
        seven.setOnClickListener(this);
        six.setOnClickListener(this);

        undo.setOnClickListener(this);
        confirm.setOnClickListener(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            player = bundle.getString(PLAYER);

        }

        return view;
    }

    private void setScore(int score){
        int total = Integer.parseInt(confirmedScore)+score;
        if(total==99 || total == 199 || total == 299){
            Toast toast = Toast.makeText(getActivity(), "Congratulations! You are the lucky one!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            total=0;
            confirmedScore = "0";
            points.clear();
        }
        scoreTextView.setText(String.valueOf(total));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageAce:
                addPoints(11);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageKing:
                addPoints(4);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageQueen:
                addPoints(3);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageJack:
                addPoints(2);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageTen:
                addPoints(10);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageNine:
                addPoints(9);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageEight:
                addPoints(8);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageSeven:
                addPoints(7);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.imageSix:
                addPoints(6);
                Log.d("points", String.valueOf(points.size()));
                break;
            case R.id.confirm:
                Log.d("players", String.valueOf(PlayerPageAdapter.players.length));
                if(PlayerPageAdapter.newPlayers.size()==0) {
                    Statistics.ok(player, Integer.parseInt(scoreTextView.getText().toString()) - Integer.parseInt(confirmedScore));
                }
                confirmedScore = scoreTextView.getText().toString();
                points.clear();
                setQuantityText();
                break;
            case R.id.undo:
                if(!points.isEmpty()) {
                    points.remove(points.size() - 1);
                    setQuantityText();
                }
                break;
        }
    }

    public void addPoints(int value){
        int count=0;
        points.add(value);
        for(int i = 0;i<points.size();i++){
            if(points.get(i)==value){
                count++;
            }
            if(count>4){
                points.remove(points.size()-1);
                count=4;
            }
        }
        setQuantityText();
    }

    public void setQuantityText(){
        aceCount = 0;
        kingCount = 0;
        queenCount = 0;
        jackCount = 0;
        tenCount = 0;
        nineCount = 0;
        eightCount = 0;
        sevenCount = 0;
        sixCount = 0;
        aceQuantity.setText("");
        kingQuantity.setText("");
        queenQuantity.setText("");
        jackQuantity.setText("");
        tenQuantity.setText("");
        nineQuantity.setText("");
        eightQuantity.setText("");
        sevenQuantity.setText("");
        sixQuantity.setText("");
        for(int i = 0; i<points.size();i++){
            if(points.get(i)==11 && aceCount<4){
                aceCount++;
                Log.d("points","ace"+String.valueOf(aceCount));
                aceQuantity.setText("x"+String.valueOf(aceCount));
            }else if(points.get(i)==4){
                kingCount++;
                kingQuantity.setText("x"+String.valueOf(kingCount));
            }else if(points.get(i)==3){
                queenCount++;
                queenQuantity.setText("x"+String.valueOf(queenCount));
            }else if(points.get(i)==2){
                jackCount++;
                jackQuantity.setText("x"+String.valueOf(jackCount));

            }else if(points.get(i)==10){
                tenCount++;
                tenQuantity.setText("x"+String.valueOf(tenCount));

            }else if(points.get(i)==8){
                eightCount++;
                eightQuantity.setText("x"+String.valueOf(eightCount));

            }else if(points.get(i)==7){
                sevenCount++;
                sevenQuantity.setText("x"+String.valueOf(sevenCount));

            }else if(points.get(i)==9){
                nineCount++;
                nineQuantity.setText("x"+String.valueOf(nineCount));

            }else if(points.get(i)==6){
                sixCount++;
                sixQuantity.setText("x"+String.valueOf(sixCount));

            }
        }
        thisRowScore = 11*aceCount+4*kingCount+3*queenCount+2*jackCount+10*tenCount+9*nineCount+8*eightCount+7*sevenCount+6*sixCount;
        setScore(thisRowScore);

    }
}
