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



//Works on Nexus 5
public class LevelMenu extends AppCompatActivity {


    Button one,two,three,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_level_menu);



         one = (Button)findViewById(R.id.levelOne);
        two = (Button)findViewById(R.id.levelTwo);
        three = (Button)findViewById(R.id.levelThree);
        back = (Button)findViewById(R.id.backStage);
        back.setOnClickListener(new backLstr());
        one.setOnClickListener(new Mylistr());
        two.setOnClickListener(new Mylistr2());
        three.setOnClickListener(new Mylistr3());

        //Connects the three buttons to the buttons in the xml and sets listeners
    }

    class Mylistr implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(LevelMenu.this,GameBoard.class);
            intent1.putExtra(LEVEL,"One");
            startActivity(intent1);
            //Goes to the levelMenu class and puts the value "One" in the LEVEL variable


        }
    }

    class Mylistr2 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent(LevelMenu.this,GameBoard.class);
            intent2.putExtra(LEVEL,"Two");
            startActivity(intent2);
            //Goes to the levelMenu class and puts the value "Two" in the LEVEL variable

        }
    }


    class Mylistr3 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent3 = new Intent(LevelMenu.this,GameBoard.class);
            intent3.putExtra(LEVEL,"Three");
            startActivity(intent3);
            //Goes to the levelMenu class and puts the value "Three" in the LEVEL variable

        }
    }
    class backLstr implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent4 = new Intent(LevelMenu.this,PickStage.class);
            startActivity(intent4);
            //Goes back to the pick stage class
        }
    }

}
