package com.exampleloksewar.loksewa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * Created by airoker80 on 11/14/2016.
 */
public class checkDir extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = new File(Environment.getExternalStorageDirectory() + "/download/facebook.html");
        if (file.exists()) {
            startActivity(new Intent(this,Login.class));
        }
    }
}
