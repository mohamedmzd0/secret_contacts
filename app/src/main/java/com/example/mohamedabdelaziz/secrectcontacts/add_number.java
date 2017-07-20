package com.example.mohamedabdelaziz.secrectcontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class add_number extends AppCompatActivity {

    database db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        db =new database(getApplicationContext()) ;
        SQLiteDatabase sqLiteDatabase =db.getWritableDatabase() ;
    }

    public void addnumber(View view) {
        String name =((TextView)findViewById(R.id.name)).getText().toString() ;
        String phone =((TextView)findViewById(R.id.phone)).getText().toString() ;
        if(name.isEmpty())
        {
            ((TextView)findViewById(R.id.name)).setHighlightColor(Color.RED);
        }
        else if(phone.isEmpty())
        {

            ((TextView)findViewById(R.id.phone)).setHighlightColor(Color.RED);
        }else {
            if (db.add_num(name, phone) == -1)
                Toast.makeText(getApplicationContext(), "Number already exists", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(getApplicationContext(), name + " added", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void cancel(View view) {
        finish();
    }
}
