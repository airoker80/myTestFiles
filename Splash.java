package com.exampleloksewar.loksewa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by airoker80 on 11/23/2016.
 */
public class Splash extends AppCompatActivity {
    private boolean loggedIn;
    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        if (loggedIn) {
            //We will start the Profile Activity
            Intent intent = new Intent(Splash.this, Logout.class);
            startActivity(intent);
        }
        else {
            t1.start();
        }
        //If we will get true

    }
    @Override
    protected void onCreate(Bundle iamSameer) {
        super.onCreate(iamSameer);
        setContentView(R.layout.splash);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    Thread t1= new Thread(){
        public  void run(){
            try {
                sleep(2500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                startActivity(new Intent(Splash.this,MainActivity.class));
            }


        }
    };

}
