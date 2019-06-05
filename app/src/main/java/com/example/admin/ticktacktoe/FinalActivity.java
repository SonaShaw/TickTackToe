package com.example.admin.ticktacktoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin on 2/22/2018.
 */

public class FinalActivity extends AppCompatActivity implements View.OnClickListener{


    private String finalWinner;
    private Intent finalIntent;
    private boolean userTurn;




    /*FinalActivity(){
        finalIntent = getIntent();
        finalWinner = finalIntent.getStringExtra("WINNER");
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_activity);

        finalIntent = getIntent();
        finalWinner = finalIntent.getStringExtra("WINNER");
        userTurn = finalIntent.getBooleanExtra("USER_TURN",false);

        TextView txtWinner = (TextView) findViewById(R.id.txtwinner);
        ImageView imgResult = (ImageView) findViewById(R.id.imgResult);
        Button btnHome = (Button) findViewById(R.id.btnHome);
        Button btnFinalRestart = (Button) findViewById(R.id.btnFinalRestart);

        txtWinner.setText(finalWinner);
        if(finalWinner.equals("YOU LOST")){
            imgResult.setImageResource(R.drawable.sad);
            btnFinalRestart.setText("try again");
        }
        else{
            imgResult.setImageResource(R.drawable.happy);
            btnFinalRestart.setText("restart");
        }

        btnFinalRestart.setOnClickListener(FinalActivity.this);
        btnHome.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnFinalRestart :
                finalIntent = new Intent(FinalActivity.this,StartActvity.class);
                finalIntent.putExtra("USER_TURN_AGAIN",userTurn);
                startActivity(finalIntent);
                finish();
                break;
            case R.id.btnHome :
                finalIntent = new Intent(FinalActivity.this,MainActivity.class);
                startActivity(finalIntent);
                //Toast.makeText(FinalActivity.this,"Press again to exit",Toast.LENGTH_SHORT).show();

                //finish();
                break;
        }
    }
}
