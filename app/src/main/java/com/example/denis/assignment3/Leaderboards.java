package com.example.denis.assignment3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.denis.assignment3.model.AccountContracts;
import com.example.denis.assignment3.model.AccountDbHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.denis.assignment3.Utils.Utils.CURRENTUSERID;
import static com.example.denis.assignment3.Utils.Utils.TOTALFAILEDATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.mDbHelper;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_PASS;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_POINTS;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_USERNAME;
import static com.example.denis.assignment3.model.AccountContracts.TABLE_NAME;

public class Leaderboards extends AppCompatActivity {
List<String> names = new ArrayList<>();
List<String> Scores = new ArrayList<>();
    Button backleader;
    TextView leaders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        leaders = (TextView)findViewById(R.id.leaderboard);
        mDbHelper = new AccountDbHelper(this);
        backleader = (Button)findViewById(R.id.Back_leader);
        backleader.setOnClickListener(new leaderList());
        checkRecord();
    }


    public void checkRecord() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                AccountContracts.AccountEntry._ID,
                COL_USERNAME,
                COL_PASS,
                COL_POINTS


        };

// Filter results WHERE "title" = 'My Title'
        //String selection = RegistrationContracts.StudentEntry.COL_USERNAME + " = ?";
        //String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        //String sortOrder =
        //    FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        String tempStr = "";
        String tempStr2 = "";



        while (cursor.moveToNext()) {






            tempStr = cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME));
            names.add(tempStr);
            //Adds the Username in the database to a temp string and then adds that string to the users array list



            tempStr2 = cursor.getString(cursor.getColumnIndexOrThrow(COL_POINTS));

            Scores.add(tempStr2);


            //Adds the scores in the database to a temp string and then adds that string to the passes array list




        }
        cursor.close();



        for(int i=0; i <names.size(); i++)
        {

            leaders.append(names.get(i) + "                                                                              ");
            leaders.append(Scores.get(i) +'\n');




                //Loops through the users and Scores array lists and checks if any of them match the username and password that the user has entered

        }





    }

    class leaderList implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Leaderboards.this,PickStage.class);
            startActivity(intent);
        }
    }
}
