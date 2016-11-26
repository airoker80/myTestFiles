package com.exampleloksewar.loksewa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by airoker80 on 11/25/2016.
 */
public class DbTest extends AppCompatActivity implements View.OnClickListener{
    TextView txtName,txtEmail;
    private SqliteDB db;
    public String s1,s2,s3;
    EditText et1,et2,et3;
    Button bt1,bt2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        db = new SqliteDB(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        bt1=(Button)findViewById(R.id.button3);
        bt2=(Button)findViewById(R.id.button4);
        txtName=(TextView)findViewById(R.id.name);
        txtEmail=(TextView)findViewById(R.id.email);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
         s1=et1.getText().toString();
         s2=et2.getText().toString();
         s3=et3.getText().toString();

    }

    @Override
    public void onClick(View v) {
        if(v==bt1){
            db.addUser("sammmer","sameer","1");
        }
        if(v==bt2){
            //db.getUserDetails();
            HashMap<String, String> user = db.getUserDetails();

            String name = user.get("name");
            String email = user.get("uid");

            // Displaying the user details on the screen
            txtName.setText(name);
            txtEmail.setText(email);
        }

    }
}
