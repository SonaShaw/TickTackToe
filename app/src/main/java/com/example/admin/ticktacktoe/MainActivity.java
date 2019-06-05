package com.example.admin.ticktacktoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStart, btnResume, btnRules, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnStart = (Button) findViewById(R.id.btnStart);
        btnResume = (Button) findViewById(R.id.btnResume);
        btnRules = (Button) findViewById(R.id.btnRules);
        btnExit = (Button) findViewById(R.id.btnExit);



        btnStart.setOnClickListener(this);
        btnRules.setOnClickListener(this);
        btnExit.setOnClickListener(MainActivity.this);
        btnResume.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.btnStart :
                intent.setClass(MainActivity.this,StartActvity.class);
                startActivity(intent);
                break;

            case R.id.btnResume :
                boolean resume = true;
                intent.setClass(this,StartActvity.class);
                intent.putExtra("RESUME",resume);
                startActivity(intent);

                break;

            case R.id.btnRules:
                intent.setClass(MainActivity.this,RulesActivity.class);
                startActivity(intent);
                break;

            case R.id.btnExit :
                finish();
                break;
        }

    }
}
