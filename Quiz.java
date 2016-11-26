package com.exampleloksewar.loksewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by airoker80 on 11/24/2016.
 */
public class Quiz extends AppCompatActivity implements View.OnClickListener{
    Button btNext;
    TextView textView;
    RadioGroup rg;
    RadioButton rb1,rb2,rb3,rb4;
    String Question[]={"what is your name","what is your name1"};
    String Answer[]={"sammer","sammer1"};
    String Option[]={"sammer","sammer1","sammer2","sammer3","sammer4","sammer5","sammer1","sammer10"};
    int flag=0;
    public static int wrong,right;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        btNext=(Button)findViewById(R.id.button);
        rg=(RadioGroup)findViewById(R.id.rg);
        textView=(TextView)findViewById(R.id.textView3);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        textView.setText(Question[flag]);
        rb1.setText(Option[0]);
        rb2.setText(Option[1]);
        rb3.setText(Option[2]);
        rb4.setText(Option[3]);
        btNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btNext){
            RadioButton ans =(RadioButton)findViewById(rg.getCheckedRadioButtonId());
            String answ=ans.getText().toString();
            if(answ.equalsIgnoreCase(Answer[flag])){
                right++;
            }else {
                wrong++;
            }
            flag++;
            if(flag<Question.length){
                textView.setText(Question[flag]);
                rb1.setText(Option[flag*4]);
                rb2.setText(Option[(flag*4)+1]);
                rb3.setText(Option[(flag*4)+2]);
                rb4.setText(Option[(flag*4)+3]);
            }
            else {
                Intent intent=new Intent(Quiz.this,AnswerResult.class);
                String right1=Integer.toString(right);
                intent.putExtra("right1", right1);
                startActivity(intent);
            }
        }

    }
}
