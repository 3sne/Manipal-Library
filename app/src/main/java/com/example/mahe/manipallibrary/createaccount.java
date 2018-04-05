package com.example.mahe.manipallibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class createaccount extends AppCompatActivity {
    EditText nm,pass,uid,ph;
    librarydatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Welcome!");
        setContentView(R.layout.activity_createaccount);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nm=(EditText)findViewById(R.id.name);
        pass=(EditText)findViewById(R.id.password);
        uid=(EditText)findViewById(R.id.userid);
        ph=(EditText)findViewById(R.id.ph);
        db=new librarydatabase(this);
    }
    public void create(View v)
    {
        if (uid.getText()==null || nm.getText()==null||pass.getText()==null||ph.getText()==null)
        {

        }else{
        boolean success=db.insertUser(String.valueOf(uid.getText()), String.valueOf(pass.getText()), String.valueOf(nm.getText()), String.valueOf(ph.getText()));
        if (success==false)
        {
            Toast.makeText(this,"could not create",Toast.LENGTH_SHORT).show();
        }else
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        }
    }


}
