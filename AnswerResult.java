package com.exampleloksewar.loksewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by airoker80 on 11/24/2016.
 */
public class AnswerResult extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        TextView textView=(TextView)findViewById(R.id.textView5);
        Intent intent=getIntent();
        String answer= intent.getStringExtra("right1");
        textView.setText("Your Score IS :"+answer);
    }
}
