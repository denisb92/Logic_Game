package com.example.denis.assignment3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.denis.assignment3.model.AccountDbHelper;

import static com.example.denis.assignment3.Utils.Utils.mDbHelper;

import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_PASS;
import static com.example.denis.assignment3.model.AccountContracts.AccountEntry.COL_USERNAME;
import static com.example.denis.assignment3.model.AccountContracts.TABLE_NAME;

public class CreateAccount extends AppCompatActivity {
    TextView name;
    TextView pass;
    Button create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name = (TextView)findViewById(R.id.newUsername);
        pass = (TextView)findViewById(R.id.newPassword);
        create = (Button)findViewById(R.id.Created);
        create.setOnClickListener(new myListr());
        mDbHelper = new AccountDbHelper(this);


    }

    class myListr implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            addAccount();
            //Calls the addAccount function which adds the new user to the database
            Intent intent = new Intent(CreateAccount.this,MainActivity.class);
            startActivity(intent);
        }
    }


    public void addAccount()
    {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME,name.getText().toString());

        values.put(COL_PASS,pass.getText().toString());






// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);
        Intent intent2 = new Intent(CreateAccount.this,MainActivity.class);
        startActivity(intent2);

    }
}
