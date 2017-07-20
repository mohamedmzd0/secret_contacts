package com.example.mohamedabdelaziz.secrectcontacts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    int time =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((TextView)findViewById(R.id.username)).setHint("username");
        ((TextView)findViewById(R.id.password)).setHint("password");

    }


    public void sign(View view) {
        if(time<3) {
            time++ ;
            String name = ((TextView) findViewById(R.id.username)).getText().toString();
            String pass = ((TextView) findViewById(R.id.password)).getText().toString();
            if (findViewById(R.id.forgetpassword).getVisibility() == View.VISIBLE) {
                if (name.isEmpty() || pass.isEmpty()) {
                    ((TextView) findViewById(R.id.username)).setText("");
                    ((TextView) findViewById(R.id.password)).setText("");
                    Toast.makeText(this, "Invaled Login "+time, Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    if (!name.equalsIgnoreCase(sharedPreferences.getString("name", "null"))) {
                        ((TextView) findViewById(R.id.username)).setText("");
                        ((TextView) findViewById(R.id.password)).setText("");
                        Toast.makeText(this, "Invaled Login "+time, Toast.LENGTH_SHORT).show();
                    } else if (!pass.equals(sharedPreferences.getString("pass", "null"))) {
                        ((TextView) findViewById(R.id.username)).setText("");
                        ((TextView) findViewById(R.id.password)).setText("");

                        Toast.makeText(this, "Invaled Login "+time, Toast.LENGTH_SHORT).show();
                    } else{
                          ItemListActivity.access=1 ;
                          finish();
                }
                }
            } else {
                String hint = ((TextView) findViewById(R.id.hint)).getText().toString();
                if (name.isEmpty() || pass.isEmpty() || hint.isEmpty()) {
                    ((TextView) findViewById(R.id.username)).setText("");
                    ((TextView) findViewById(R.id.password)).setText("");
                    ((TextView) findViewById(R.id.hint)).setText("");
                    Toast.makeText(this, "Invaled access "+time, Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    if (hint.equals(sharedPreferences.getString("hint", "null000")) && (name.equalsIgnoreCase(sharedPreferences.getString("name", "null")) || pass.equals(sharedPreferences.getString("pass", null)))) {
                        startActivity(new Intent(getApplicationContext(), Splash.class));
                        finish();
                    }
                    else
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        }
        else{
            ItemListActivity.access=-1 ;
            finish();
    }
    }
    public void forget(View view) {
        ((TextView)findViewById(R.id.username)).setHint("last user name");
        ((TextView)findViewById(R.id.password)).setHint("last password");
        (findViewById(R.id.lay)).setVisibility(View.VISIBLE);
       findViewById(R.id.forgetpassword).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ItemListActivity.access=-1;
    }
}

