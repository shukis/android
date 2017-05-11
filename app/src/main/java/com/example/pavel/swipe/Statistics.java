package com.example.pavel.swipe;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class Statistics extends AppCompatActivity {

    ProgressBar pointsBar, winsBar;
    public static DBHelper dbHelper;
    public static ArrayList<String> str = new ArrayList<>();
    public static ArrayList<Integer> pts = new ArrayList<>();

    public ArrayList<Integer> nataliPointsList = new ArrayList<>();
    public ArrayList<Integer> pavelPointsList = new ArrayList<>();

    TextView nataliWins, natalipointsPercent, nataliPoints, nataliPercent, pavelWins, pavelpointsPercent, pavelPoints, pavelPercent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        nataliWins = (TextView)findViewById(R.id.nataliWins);
        natalipointsPercent = (TextView)findViewById(R.id.nataliPointsPercent);
        nataliPoints = (TextView)findViewById(R.id.nataliPoints);
        nataliPercent = (TextView)findViewById(R.id.nataliPercent);
        pavelWins = (TextView)findViewById(R.id.pavelWins);
        pavelpointsPercent = (TextView)findViewById(R.id.pavelPointsPercent);
        pavelPoints = (TextView)findViewById(R.id.pavelPoints);
        pavelPercent = (TextView)findViewById(R.id.pavelPercent);

        winsBar = (ProgressBar)findViewById(R.id.winsBar);
        pointsBar = (ProgressBar)findViewById(R.id.pointsBar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarStats);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Stats");

        dbHelper = new DBHelper(this);

        if(str.size()==pts.size()) {
            for (int z = 0; z < str.size(); z++) {
                writeIntoDB(str.get(z), pts.get(z));
            }
        }

        readFromDatabase("Natali");
        readFromDatabase("Pavel");


        if(!nataliPointsList.isEmpty()) {
            int nataliWinsPercent = nataliWinsPercent();
            winsBar.setProgress(nataliWinsPercent);
            nataliPercent.setText(nataliWinsPercent + "%");
            pavelPercent.setText(100 - nataliWinsPercent + "%");

            int nataliPointsPercent = nataliPointsPerent();
            pointsBar.setProgress(nataliPointsPercent);
            natalipointsPercent.setText(nataliPointsPercent + "%");
            pavelpointsPercent.setText(100 - nataliPointsPercent + "%");


            nataliWins.setText(String.valueOf(pavelPointsList.size()));
            pavelWins.setText(String.valueOf(nataliPointsList.size()));

            nataliPoints.setText(String.valueOf(totalPoints(nataliPointsList)));
            pavelPoints.setText(String.valueOf(totalPoints(pavelPointsList)));
        }
        str.clear();
        pts.clear();

    }

    public int nataliPointsPerent(){
        int totalPoints = totalPoints(pavelPointsList) + totalPoints(nataliPointsList);
        int percent = totalPoints(nataliPointsList)*100/totalPoints;
        return percent;
    }

    public int nataliWinsPercent(){
        int totalGames = pavelPointsList.size() + nataliPointsList.size();
        int percent = pavelPointsList.size()*100/totalGames;
        return percent;
    }

    public int totalPoints(ArrayList<Integer> array){
        int totalPoints = 0;
        for(int i = 0; i<array.size(); i++){
            totalPoints+=array.get(i);
        }
        return totalPoints;
    }

    public static void writeIntoDB(String name, int points){
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_NAME, name);
        contentValues.put(DBHelper.KEY_POINTS, points);
        database.insert(DBHelper.TABLE_STATISTICS, null, contentValues);



    }
    public static void ok(String string, int points){
        str.add(string);
        pts.add(points);
    }

    public void readFromDatabase(String name){
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String[] columns = {DBHelper.KEY_NAME, DBHelper.KEY_POINTS};
        Cursor cursor = database.query(DBHelper.TABLE_STATISTICS, columns, DBHelper.KEY_NAME+" = '"+name+"'", null, null, null, null);

        if (cursor.moveToFirst()){
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int pointsIndex = cursor.getColumnIndex(DBHelper.KEY_POINTS);
                do{
                    if(name.equals("Natali")){
                        nataliPointsList.add(cursor.getInt(pointsIndex));
                    }
                    if(name.equals("Pavel")){
                        pavelPointsList.add(cursor.getInt(pointsIndex));
                    }

                }while (cursor.moveToNext());
        }else{
            cursor.close();
        }

    }

}
