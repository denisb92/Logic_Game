package com.example.denis.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.denis.assignment3.model.AccountContracts;
import com.example.denis.assignment3.model.AccountDbHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.denis.assignment3.Utils.Utils.CURRENTUSER;
import static com.example.denis.assignment3.Utils.Utils.CURRENTUSERID;
import static com.example.denis.assignment3.Utils.Utils.SHARED_PREF_FILENAME;
import static com.example.denis.assignment3.Utils.Utils.TOTALFAILEDATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.mDbHelper;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_PASS;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_POINTS;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_USERNAME;
import static com.example.denis.assignment3.model.AccountContracts.TABLE_NAME;

//Works on Nexus 5

public class MainActivity extends AppCompatActivity {
    Button playgame;
    Button createAccount;
    EditText username;
    EditText password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    List<String> users = new ArrayList<>();
    List<String> passes = new ArrayList<>();
    List<Integer> points = new ArrayList<>();
    List<Integer> temps = new ArrayList<>();
    //Creates a button variable called playgame

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREF_FILENAME, Context.MODE_APPEND);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

       playgame = (Button)findViewById(R.id.playGame);
        playgame.setOnClickListener(new myListr());
        createAccount = (Button)findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new myListr2());
        username = (EditText)findViewById(R.id.enteredUser);
        password = (EditText)findViewById(R.id.enteredPass);
        mDbHelper = new AccountDbHelper(this);
        //connects the playgame button to the id of the button in the xml and set a listener

    }

    class myListr implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            boolean correct = checkRecord();
            //checkRecords return boolean is stored into correct
            if(correct)
            {
                //If correct is true, Login class is opened
                Intent intent = new Intent(MainActivity.this, PickStage.class);
                startActivity(intent);
                //intent.putExtra(CURRENTUSER,username.getText().toString());
                CURRENTUSER = username.getText().toString();

                startActivity(intent);
            }
            else
            {
                //If correct is false , an alert is activated telling the user that their username or password is incorrect
                AlertDialog invalid = new AlertDialog.Builder(MainActivity.this).create();
                invalid.setMessage("Invalid username or password");
                invalid.show();
            }

            //Goes to level menu when you click

        }
    }

    class myListr2 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CreateAccount.class);
            startActivity(intent);
            //Goes to level menu when you click

        }
    }



    public boolean checkRecord() {
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
        String temp = "";
        String tempStr = "";
        String tempStr2 = "";
        String tempStr3 = "";


        while (cursor.moveToNext()) {



            temp = cursor.getString(cursor.getColumnIndexOrThrow(String.valueOf(AccountContracts.AccountEntry._ID)));
            temps.add(Integer.parseInt(temp));


            tempStr = cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME));
            users.add(tempStr);
            //Adds the Username in the database to a temp string and then adds that string to the users array list

            tempStr2 = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASS));
            passes.add(tempStr2);

            tempStr3 = cursor.getString(cursor.getColumnIndexOrThrow(COL_POINTS));

            points.add(Integer.parseInt(tempStr3));


            //Adds the password in the database to a temp string and then adds that string to the passes array list


            //tempStr += cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL));
            //tempStr += cursor.getString(cursor.getColumnIndexOrThrow(COL_PASS));
            //tempStr += cursor.getString(cursor.getColumnIndexOrThrow(COL_AGE));
            //tempStr += cursor.getString(cursor.getColumnIndexOrThrow(COL_MAJOR)) +"\n";


        }
        cursor.close();



        for(int i=0; i <users.size(); i++)
        {


            if((username.getText().toString().equals(users.get(i))) && (password.getText().toString().equals(passes.get(i))))
            {
                CURRENTUSERID = temps.get(i);
                TOTALFAILEDATTEMPTS = points.get(i);
                //Loops through the users and passes array lists and checks if any of them match the username and password that the user has entered
                //Returns true if there is a match
                return true;

            }
        }
        //Returns false if there is no match
        return false;



    }
}
