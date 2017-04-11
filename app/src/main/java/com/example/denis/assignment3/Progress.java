package com.example.denis.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.denis.assignment3.Utils.Utils.SHARED_PREF_FILENAME;
//This class shows the log files for the Player, it shows the time that the user logged in, and the times that the player beat the levels

public class Progress extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView logged;
    Button prog_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        logged = (TextView)findViewById(R.id.logs);
        prog_back = (Button)findViewById(R.id.Back_Prog);
        prog_back.setOnClickListener(new backList());
        sharedPreferences = getSharedPreferences(SHARED_PREF_FILENAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        //Gets the key Name from shared preference and appends it to the text view
        logged.append(name +'\n');


            String levelOne = sharedPreferences.getString("One","");
            String levelTwo = sharedPreferences.getString("Two","");
            String levelThree = sharedPreferences.getString("Three","");
            String levelFour = sharedPreferences.getString("Four", "");
            String levelFive = sharedPreferences.getString("Five", "");
            String levelSix = sharedPreferences.getString("Six", "");

        //Gets the the key for each of the levels from the shared perference and appends it to the text view
        logged.append(levelOne +'\n');
        logged.append(levelTwo +'\n');
        logged.append(levelThree +'\n');
        logged.append(levelFour + '\n');
        logged.append(levelFive + '\n');
        logged.append(levelSix + '\n');




    }
    class backList implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Progress.this,PickStage.class);
            startActivity(intent);
            //Goes back to the pick stage menu

        }
    }
}
