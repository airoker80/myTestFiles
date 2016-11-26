package com.exampleloksewar.loksewa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by airoker80 on 11/10/2016.
 */
public class Signup extends AppCompatActivity implements View.OnClickListener {
    public static final String key_Username="UserName";
    public static final String key_Password="Password";

    private static final String REGISTERED_URL="http://192.168.100.3/loksewa/signup.php";
    EditText username,password;

    Button signup,login;
    TextView t1;

    @Override
    protected void onCreate(@Nullable Bundle a1) {
        super.onCreate(a1);
        setContentView(R.layout.signup);
        username =(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        signup=(Button)findViewById(R.id.btnRegister);
        login=(Button)findViewById(R.id.button2);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

    }
    public  void signup(){
        final String UserName=username.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Signup.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Signup.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();

                params.put(key_Username,UserName);
                params.put(key_Password,Password);
                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        // startActivity(new Intent(this,login.class));

    }

    @Override
    public void onClick(View v) {
        if(v==signup) {
            signup();
        }
        if(v==login){
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
