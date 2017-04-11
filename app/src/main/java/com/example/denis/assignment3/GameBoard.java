package com.example.denis.assignment3;

//Works on Nexus 5

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.view.animation.Animation;
import android.graphics.Canvas;
import android.graphics.Point;
import android.widget.ImageView;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import android.app.Activity;


import com.example.denis.assignment3.model.AccountContracts;
import com.example.denis.assignment3.model.AccountDbHelper;

import java.util.Calendar;

import static com.example.denis.assignment3.Utils.Utils.CURRENTUSER;
import static com.example.denis.assignment3.Utils.Utils.CURRENTUSERID;
import static com.example.denis.assignment3.Utils.Utils.LEVEL;
import static com.example.denis.assignment3.Utils.Utils.LEVELFIVEATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELFOURATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELONEATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELSIXATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELTHREEATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELTWOATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.SHARED_PREF_FILENAME;
import static com.example.denis.assignment3.Utils.Utils.TOTALFAILEDATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.levels;
import static com.example.denis.assignment3.Utils.Utils.mDbHelper;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_POINTS;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry._ID;
import static com.example.denis.assignment3.model.AccountContracts.TABLE_NAME;

public class GameBoard extends Activity{

        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        StartDraggingLsntr myStartDraggingLsnr;
        EndDraggingLsntr myEndDraggingLsntr;
        Button playBtn, btn1, btn2,btn3,btn4;
        ImageView panda;
        String direction="";
        String direction2= "";
        String direction3="";
        String direction4= "";
        Animation correct;
        String level;
        String[] solution = new String[4];
        MediaPlayer music;
        //Creates all the variables


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            String incomingMsg = getIntent().getStringExtra(LEVEL);
            level = incomingMsg;
            mDbHelper = new AccountDbHelper(this);
            sharedPreferences = getSharedPreferences(SHARED_PREF_FILENAME, Context.MODE_APPEND);
            editor = sharedPreferences.edit();
            //incomingMsg taks the value of LEVEL and puts it into the level variable

            if(level.equals("One"))
            {
                setContentView(R.layout.activity_game_board);
                solution[0] = "right";
                solution[1] = "down";
                solution[2] = "right";
                solution[3] = " ";
                correct = AnimationUtils.loadAnimation(GameBoard.this, R.anim.levelonesolution);
                //If the level variable equals "One" then gameboard level one is set as the layout, solution array receives the following values(correct solution)
                //The correct animation gets connected to the xml file that animates the panda image



            }
            else if(level.equals("Two"))
            {
                setContentView(R.layout.activity_game_board2);
                solution[0] = "down";
                solution[1] = "right";
                solution[2] = "up";
                solution[3] = "right";
                correct = AnimationUtils.loadAnimation(GameBoard.this, R.anim.leveltwosolution);

                //If the level variable equals "Two" then gameboard level two is set as the layout, solution array receives the following values(correct solution)
                //The correct animation gets connected to the xml file that animates the panda image

            }
            else if (level.equals("Three"))
            {

                setContentView(R.layout.activity_game_board3);

                solution[0] = "right";
                solution[1] = "down";
                solution[2] = "left";
                solution[3] = "down";
                correct = AnimationUtils.loadAnimation(GameBoard.this, R.anim.levelthreesolution);

                //If the level variable equals "Three" then gameboard level three is set as the layout, solution array receives the following values(correct solution)
                //The correct animation gets connected to the xml file that animates the panda image
            }
            else if(level.equals("Four"))
            {
                setContentView(R.layout.activity_game_board4);
                solution[0] = "right";
                solution[1] = "down";
                solution[2] = "right";
                solution[3] = " ";
                correct = AnimationUtils.loadAnimation(GameBoard.this, R.anim.levelfoursolution);

            }

            else if(level.equals("Five"))
            {
                setContentView(R.layout.activity_game_board5);
                solution[0] = "down";
                solution[1] = "right";
                solution[2] = "up";
                solution[3] = "left";
                correct = AnimationUtils.loadAnimation(GameBoard.this, R.anim.levelfivesolution);

            }
            else if(level.equals("Six"))
            {
                setContentView(R.layout.activity_game_board6);
                solution[0] = "right";
                solution[1] = "down";
                solution[2] = "right";
                solution[3] = "down";
                correct = AnimationUtils.loadAnimation(GameBoard.this, R.anim.levelsixsolution);

            }


            myStartDraggingLsnr=new StartDraggingLsntr();
            myEndDraggingLsntr=new EndDraggingLsntr();
            //Implements the drag listener

            playBtn=(Button) findViewById(R.id.playBtn);

            findViewById(R.id.upBtn).setOnLongClickListener(myStartDraggingLsnr);
            findViewById(R.id.downBtn).setOnLongClickListener(myStartDraggingLsnr);
            findViewById(R.id.leftBtn).setOnLongClickListener(myStartDraggingLsnr);
            findViewById(R.id.rightBtn).setOnLongClickListener(myStartDraggingLsnr);
            //Connects the arrows to the click listener

            findViewById(R.id.Btn1).setOnDragListener(myEndDraggingLsntr);
            findViewById(R.id.Btn2).setOnDragListener(myEndDraggingLsntr);
            findViewById(R.id.Btn3).setOnDragListener(myEndDraggingLsntr);
            findViewById(R.id.Btn4).setOnDragListener(myEndDraggingLsntr);
            //Connects Btn1,Btn2,Btn3,Btn4 to the drag listener

            btn1 = (Button)findViewById(R.id.Btn1);
            btn2=(Button)findViewById(R.id.Btn2);
            btn3=(Button)findViewById(R.id.Btn3);
            btn4=(Button)findViewById(R.id.Btn4);
            //Connects the buttons to the buttons in the xml





            playBtn.setOnClickListener(new MyLstr());
            panda = (ImageView)findViewById(R.id.PandaPic);





            music = MediaPlayer.create(GameBoard.this, R.raw.gamemusic);
            music.setLooping(true);
            music.start();
            //Sets the music and sets looping to true and starts the music
        }

    public void playMusic(View view)
    {
        music.start();
        //The music is started
    }

    public void setRect(View view) {
        SharedXYValues.drawingMode="RECT";
        String description;
        description = "sequence: " + findViewById(R.id.Btn1).getContentDescription() + ", "
                + findViewById(R.id.Btn2).getContentDescription() + ", "
                + findViewById(R.id.Btn3).getContentDescription() + ", "
                + findViewById(R.id.Btn4).getContentDescription();

    }


    public void exit(View view) {
        if(level.equals("Four") || level.equals("Five") || level.equals("Six")) {


            Intent intent = new Intent(GameBoard.this, LevelMenu2.class);
            startActivity(intent);
        }
        else{
            Intent intent2 = new Intent(GameBoard.this,LevelMenu.class);
            startActivity(intent2);
        }
        //The exit button returns to the level menu

    }
   class MyLstr implements View.OnClickListener{
       @Override
       public void onClick(View v) {

           direction = btn1.getContentDescription().toString();
           direction2 = btn2.getContentDescription().toString();
           direction3 = btn3.getContentDescription().toString();
           direction4 = btn4.getContentDescription().toString();
           //direction, direction2, direction3, direction4 is set to equal the values of the buttons content description after the play button is clicked

           if(solution[0].equals(direction) && solution[1].equals(direction2) && solution[2].equals(direction3) && solution[3].equals(direction4))
           {
               panda.startAnimation(correct);
               correct.setAnimationListener(new Animation.AnimationListener() {
                   @Override
                   public void onAnimationStart(Animation animation) {

                   }

                   @Override
                   public void onAnimationEnd(Animation animation) {
                       if(level.equals("Four") || level.equals("Five") || level.equals("Six")) {


                           Intent intent = new Intent(GameBoard.this, LevelMenu2.class);
                           startActivity(intent);
                       }
                       else{
                           Intent intent2 = new Intent(GameBoard.this,LevelMenu.class);
                           startActivity(intent2);
                       }



                   }

                   @Override
                   public void onAnimationRepeat(Animation animation) {

                   }
               });
                Toast.makeText(GameBoard.this, "Good job!", Toast.LENGTH_LONG).show();
               //If the direction variables that the player has selected equal the solution values then the panda animation for that level is started
               //It then informs you that you have gotten the puzzle correct
               Calendar c = Calendar.getInstance();
               System.out.println("Current time => " + c.getTime());


               String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


                   editor.putString(level, CURRENTUSER + " beat Level " + level + " at " + mydate + "\n\n");
                   editor.apply();




           }
           else
           {
               AlertDialog incorrect = new AlertDialog.Builder(GameBoard.this).create();
               incorrect.setMessage("Incorrect answer , try again");
               incorrect.show();
               TOTALFAILEDATTEMPTS+=1;
               if(level.equals("One"))
               {
                   LEVELONEATTEMPTS+=1;

               }
               else if(level.equals("Two"))
               {
                   LEVELTWOATTEMPTS+=1;

               }
               else if(level.equals("Three"))
               {
                   LEVELTHREEATTEMPTS+=1;

               }
               else if(level.equals("Four"))
               {
                   LEVELFOURATTEMPTS+=1;
               }
               else if(level.equals("Five"))
               {
                   LEVELFIVEATTEMPTS+=1;
               }
               else if(level.equals("Six"))
               {
                   LEVELSIXATTEMPTS+=1;
               }
               SQLiteDatabase db = mDbHelper.getWritableDatabase();
               ContentValues values = new ContentValues();
               values.put(COL_POINTS, String.valueOf(TOTALFAILEDATTEMPTS));
               db.update(TABLE_NAME,values,_ID + " = ?",new String[]{String.valueOf(CURRENTUSERID)});



               //If the direction variables arent correct then the alertdialog informs the player that they're not correct

           }





       }
   }




    private class EndDraggingLsntr implements View.OnDragListener{
        @Override
        public boolean onDrag(View view, DragEvent event) {
            if (event.getAction()==DragEvent.ACTION_DROP)
            {
                ((Button) view).setBackground( ((Button) event.getLocalState()).getBackground());
                ((Button) view).setContentDescription( ((Button) event.getLocalState()).getContentDescription());
                //implements the drag listener
            }

            return true;
        }
    }

    private class StartDraggingLsntr implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View view) {
            WithDraggingShadow shadow = new WithDraggingShadow(view);
            ClipData data=ClipData.newPlainText("","");
            view.startDrag( data, shadow, view, 0);
            return false;
            //StartDraggingLsntr
        }
    }


    private class WithDraggingShadow extends View.DragShadowBuilder{
        public WithDraggingShadow(View view){
            super(view);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);

        }
    }




    }







