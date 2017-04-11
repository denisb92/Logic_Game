package com.example.denis.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import static com.example.denis.assignment3.Utils.Utils.CURRENTUSER;
import static com.example.denis.assignment3.Utils.Utils.SHARED_PREF_FILENAME;
import static com.example.denis.assignment3.Utils.Utils.TOTALFAILEDATTEMPTS;

public class PickStage extends AppCompatActivity {
Button stats, progress, stage1,stage2,leaderboardsbtn;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_stage);
        leaderboardsbtn = (Button)findViewById(R.id.leaderboardsbtn);
        leaderboardsbtn.setOnClickListener(new MyListr5());
        stats = (Button)findViewById(R.id.Stats);

        progress =(Button)findViewById(R.id.Progress);
        stage1 = (Button)findViewById(R.id.bambooForest);
        stage2 = (Button)findViewById(R.id.cityExplorer);
        stage1.setOnClickListener(new MyListr1());
        stage2.setOnClickListener(new MyListr2());
        stats.setOnClickListener(new MyListr3());
        progress.setOnClickListener(new MyListr4());
        sharedPreferences = getSharedPreferences(SHARED_PREF_FILENAME, Context.MODE_APPEND);
        editor = sharedPreferences.edit();
        //Binds all the objects in the xml


        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());


        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        //Gets the current date and time
        editor.putString("Name",CURRENTUSER + " logged on at " + mydate + "\n\n");
        editor.apply();
        //Gets the name of the current user and the time that the user logged into the game and stores it into shared preference

    }
    class MyListr1 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(PickStage.this,LevelMenu.class);
            startActivity(intent1);
            //Goes to the First Stage level selections

        }
    }
    class MyListr2 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent(PickStage.this,LevelMenu2.class);
            startActivity(intent2);
            //Goes to the Second Stage level selections

        }
    }

    class MyListr3 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent3 = new Intent(PickStage.this,Stats.class);
            startActivity(intent3);
            //Goes to the stats pie chart page

        }
    }
    class MyListr4 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent4 = new Intent(PickStage.this,Progress.class);
            startActivity(intent4);
            //Goes to the progress log files
        }
    }
    class MyListr5 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent5 = new Intent(PickStage.this,Leaderboards.class);
            startActivity(intent5);
            //Goes to the leaderboards page
        }
    }
}
