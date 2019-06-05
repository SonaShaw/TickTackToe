package com.example.admin.ticktacktoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;

/**
 * Created by admin on 2/16/2018.
 */

public class StartActvity extends AppCompatActivity implements View.OnClickListener{


    private boolean userWon, deviceWon, userTurn, deviceTurn;
    private int count;
    private TextView[] txt;
    private String[] str;
    private SecureRandom secureRandomNumber;

    StartActvity(){
       txt = new TextView[9];
       str = new String[9];
       secureRandomNumber = new SecureRandom();
       userWon = false;
       deviceWon = false;
       userTurn = true;
       deviceTurn = false;
   }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Intent resumeIntent = getIntent();
        boolean resumeValue = resumeIntent.getBooleanExtra("RESUME",false);
        userTurn = resumeIntent.getBooleanExtra("USER_TURN_AGAIN",true);

        txt[0] = (TextView) findViewById(R.id.txt0);
        txt[1] = (TextView) findViewById(R.id.txt1);
        txt[2] = (TextView) findViewById(R.id.txt2);
        txt[3] = (TextView) findViewById(R.id.txt3);
        txt[4] = (TextView) findViewById(R.id.txt4);
        txt[5] = (TextView) findViewById(R.id.txt5);
        txt[6] = (TextView) findViewById(R.id.txt6);
        txt[7] = (TextView) findViewById(R.id.txt7);
        txt[8] = (TextView) findViewById(R.id.txt8);

        for(int i=0; i<txt.length; i++)
           txt[i].setOnClickListener(this);
        if(resumeValue){
            displaySavedData();
        }
        if(userTurn)
            deviceTurn = false;
        else{
            deviceTurn = true;
            xSetByDevice(txt[generateRandomNumber()]);
            userTurn = true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt0 :
                setResponseById(txt[0]);
                break;
            case R.id.txt1 :
                setResponseById(txt[1]);
                break;
            case R.id.txt2 :
                setResponseById(txt[2]);
                break;
            case R.id.txt3 :
                setResponseById(txt[3]);
                break;
            case R.id.txt4 :
                setResponseById(txt[4]);
                break;
            case R.id.txt5 :
                setResponseById(txt[5]);
                break;
            case R.id.txt6 :
                setResponseById(txt[6]);
                break;
            case R.id.txt7 :
                setResponseById(txt[7]);
                break;
            case R.id.txt8 :
                setResponseById(txt[8]);
                break;
        }
    }

    private void setResponseById(TextView text){
        int randomNumber;
        if(text.getText().toString().equals("")){
            if(userTurn){
                userTurn = false;
                deviceTurn = true;
                text.setText("0");
                count++;
                if(isWon()) {
                    goToFinalActivityWithDelay();
                    return;
                }
            }
            if(deviceTurn){
                deviceTurn = false;
                userTurn = true;
                randomNumber = generateRandomNumber();
                xSetByDevice(txt[randomNumber]);
                count++;
                if(isWon()) {
                    goToFinalActivityWithDelay();
                    return;
                }
            }

        }
    }

    private void xSetByDevice(TextView text){
        text.setText("X");
    }

    private int generateRandomNumber(){
        int randomNumber = secureRandomNumber.nextInt(9);
        if(txt[randomNumber].getText().toString().equals(""))
            return randomNumber;
        return generateRandomNumber();
    }

    private void goToFinalActivityWithDelay(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToFinalActivity();
            }
        },1000);
    }

    private void goToFinalActivity(){

        Intent intent = new Intent(this,FinalActivity.class);
        //intent.setClass(StartActvity.this,FinalActivity.class);       //  passing value from one activity to
        String winner;                                                  //  another actvity .....For more details
        if(userWon || deviceWon)                                        //  go to page no.92
            winner = (userWon) ? "YOU WON" : "YOU LOST";
        else
            winner = "MATCH DRAW";
        intent.putExtra("WINNER",winner);
        intent.putExtra("USER_TURN",userTurn);
        startActivity(intent);
        finish();
    }

    private boolean isWon(){            //  This method checks win and draw condition............
        if(checkColumn() || checkRow() || checkDiagonal()){
            if(!userTurn) {
               // Toast.makeText(this, "user won", Toast.LENGTH_SHORT).show();
                userWon = true;
            }
            else {
               // Toast.makeText(this, "user Lost", Toast.LENGTH_SHORT).show();
                deviceWon = true;
            }
            //for(int i = 0; i < txt.length; i++)
              //  txt[i].setText("");
            for(int i = 0; i < txt.length; i++)
                txt[i].setEnabled(false);
            return true;
        }
        //.............Draw Condition..............
        if(count == 9){
           // Toast.makeText(this, "match draw", Toast.LENGTH_SHORT).show();
            for(int i = 0; i < txt.length; i++)
                txt[i].setEnabled(false);
            return true;
        }
        return false;

    }

    private boolean checkColumn(){
        for(int i=0; i<3;i++){
            if((txt[i].getText().toString().equals("0") && txt[i+3].getText().toString().equals("0") &&
                    txt[i+6].getText().toString().equals("0")) || (txt[i].getText().toString().equals("X") &&
                    txt[i+3].getText().toString().equals("X") && txt[i+6].getText().toString().equals("X")))
                return true;
        }
        return false;
    }

    private boolean checkRow(){
        for(int i = 0; i<7; i+=3){
            if((txt[i].getText().toString().equals("0") && txt[i+1].getText().toString().equals("0") &&
                    txt[i+2].getText().toString().equals("0")) || (txt[i].getText().toString().equals("X") &&
                    txt[i+1].getText().toString().equals("X") && txt[i+2].getText().toString().equals("X")))
                return true;
        }
        return false;
    }

    private boolean checkDiagonal(){
        if((txt[0].getText().toString().equals("0") && txt[4].getText().toString().equals("0") &&
                txt[8].getText().toString().equals("0")) || (txt[0].getText().toString().equals("X") &&
                txt[4].getText().toString().equals("X") && txt[8].getText().toString().equals("X")))
            return true;
        if((txt[2].getText().toString().equals("0") && txt[4].getText().toString().equals("0") &&
                txt[6].getText().toString().equals("0")) || (txt[2].getText().toString().equals("X") &&
                txt[4].getText().toString().equals("X") && txt[6].getText().toString().equals("X")))
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"game resumed",Toast.LENGTH_SHORT).show();
        saveData();
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getPreferences(0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("VALUE0", txt[0].getText().toString());
        editor.putString("VALUE1", txt[1].getText().toString());
        editor.putString("VALUE2", txt[2].getText().toString());
        editor.putString("VALUE3", txt[3].getText().toString());
        editor.putString("VALUE4", txt[4].getText().toString());
        editor.putString("VALUE5", txt[5].getText().toString());
        editor.putString("VALUE6", txt[6].getText().toString());
        editor.putString("VALUE7", txt[7].getText().toString());
        editor.putString("VALUE8", txt[8].getText().toString());
        editor.commit();
    }

    private void displaySavedData(){
        SharedPreferences sharedPreferences = getPreferences(0);
        String txtValue = sharedPreferences.getString("VALUE0", "");
        txt[0].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE1", "");
        txt[1].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE2", "");
        txt[2].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE3", "");
        txt[3].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE4", "");
        txt[4].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE5", "");
        txt[5].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE6", "");
        txt[6].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE7", "");
        txt[7].setText(txtValue);
        txtValue = sharedPreferences.getString("VALUE8", "");
        txt[8].setText(txtValue);
    }
}



