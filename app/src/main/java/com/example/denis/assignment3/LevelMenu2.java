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
import static com.example.denis.assignment3.Utils.Utils.LEVEL;
import static com.example.denis.assignment3.Utils.Utils.SHARED_PREF_FILENAME;



//Works on Nexus 5
public class LevelMenu2 extends AppCompatActivity {

    Button four, five, six,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_level_menu2);

        back = (Button)findViewById(R.id.backStage);
        back.setOnClickListener(new backLstr());

        four = (Button) findViewById(R.id.levelFour);
        five = (Button) findViewById(R.id.levelFive);
        six = (Button) findViewById(R.id.levelSix);

        four.setOnClickListener(new Mylistr());
        five.setOnClickListener(new Mylistr2());
        six.setOnClickListener(new Mylistr3());

        //Connects the three buttons to the buttons in the xml and sets listeners
    }

    class Mylistr implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(LevelMenu2.this, GameBoard.class);
            intent1.putExtra(LEVEL, "Four");
            startActivity(intent1);
            //Goes to the levelMenu class and puts the value "Four" in the LEVEL variable


        }
    }

    class Mylistr2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent(LevelMenu2.this, GameBoard.class);
            intent2.putExtra(LEVEL, "Five");
            startActivity(intent2);
            //Goes to the levelMenu class and puts the value "Five" in the LEVEL variable

        }
    }


    class Mylistr3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent3 = new Intent(LevelMenu2.this, GameBoard.class);
            intent3.putExtra(LEVEL, "Six");
            startActivity(intent3);
            //Goes to the levelMenu class and puts the value "Six" in the LEVEL variable

        }
    }
    class backLstr implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent4 = new Intent(LevelMenu2.this,PickStage.class);
            startActivity(intent4);
            //Goes back to the pick stage class
        }
    }
}


