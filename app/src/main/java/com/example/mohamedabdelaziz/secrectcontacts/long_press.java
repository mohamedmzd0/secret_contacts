package com.example.mohamedabdelaziz.secrectcontacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

public class long_press extends AppCompatActivity {

    String name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_press);
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");


    }

    public void delete(View view) {
        database db = new database(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        db.delete(phone);
        finish();
    }

    public void call(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+Uri.encode(phone.trim())));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
    }

    public void message(View view) {
        if (!TextUtils.isEmpty(phone)) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
            smsIntent.putExtra("sms_body", "");
            startActivity(smsIntent);
        }
    }
}
