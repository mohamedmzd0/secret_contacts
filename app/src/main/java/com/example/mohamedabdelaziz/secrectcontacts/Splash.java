package com.example.mohamedabdelaziz.secrectcontacts;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void cont(View view) {
        String name =((TextView)findViewById(R.id.name)).getText().toString() ;
        String pass =((TextView)findViewById(R.id.pass)).getText().toString() ;
        String repass =((TextView)findViewById(R.id.passrep)).getText().toString() ;
        String hint =((TextView)findViewById(R.id.passhint)).getText().toString() ;
        if(name.isEmpty() || name.equalsIgnoreCase(""))
            Toast.makeText(getApplicationContext(),"Invalied Name",Toast.LENGTH_SHORT).show();
        else if(pass.isEmpty() || name.equalsIgnoreCase("") ||pass.length()<8)
            Toast.makeText(getApplicationContext(),"Invalied password",Toast.LENGTH_SHORT).show();
        else if(repass.isEmpty() || name.equalsIgnoreCase("") ||repass.length()<8)
            Toast.makeText(getApplicationContext(),"Invalied repeat password",Toast.LENGTH_SHORT).show();
        else if(hint.isEmpty() || name.equalsIgnoreCase("")||hint.length()<4)
            Toast.makeText(getApplicationContext(),"Invalied hint",Toast.LENGTH_SHORT).show();
        else if(!pass.equals(repass))
            Toast.makeText(getApplicationContext(),"Password Not Match",Toast.LENGTH_SHORT).show();
        else {

            SharedPreferences sharedPreferences =getSharedPreferences("data",MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit() ;
            editor.putString("name",name);
            editor.putString("pass",pass);
            editor.putString("hint",hint);
            editor.commit() ;
            ItemListActivity.access=1 ;
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ItemListActivity.access=-1;
    }
}
