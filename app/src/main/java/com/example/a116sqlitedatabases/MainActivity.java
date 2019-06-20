package com.example.a116sqlitedatabases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
        SQLiteDatabase myDB= this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS users (username VARCHAR, age INT(4),id INTEGER PRIMARY KEY )");
        myDB.execSQL("INSERT INTO users (username, age) VALUES ('Ashish', 7)");
        myDB.execSQL("INSERT INTO users (username, age) VALUES ('Abhishek', 16)");
        myDB.execSQL("UPDATE users SET age=2 WHERE username='Ashish' ");
        //myDB.execSQL("SELECT *FROM users where username='Ashish'");

        Cursor c = myDB.rawQuery("SELECT * FROM users ", null); // All types of select statements work here
        int usernameIndex = c.getColumnIndex("username");
        int id = c.getColumnIndex("id");
        int ageIndex = c.getColumnIndex("age");
        c.moveToFirst();
        TextView tv=(TextView)findViewById(R.id.tv);

        while (c != null) {
            Log.i("Resuts-id",c.getString(id));
            Log.i("Results - username", c.getString(usernameIndex));
            Log.i("Results - age", Integer.toString(c.getInt(ageIndex)));
            tv.setText(tv.getText() +c.getString(id)+"  "+c.getString(usernameIndex)+" "+Integer.toString(c.getInt(ageIndex))+" \n" );
            c.moveToNext();
        }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}

























