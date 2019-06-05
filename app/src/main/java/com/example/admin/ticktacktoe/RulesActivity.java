package com.example.admin.ticktacktoe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by admin on 2/17/2018.
 */

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_activity);
        TextView txtMore = (TextView) findViewById(R.id.txtMore);
        txtMore.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
