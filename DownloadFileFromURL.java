package com.exampleloksewar.loksewa;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Created by airoker80 on 11/12/2016.
 */
public class DownloadFileFromURL extends ActionBarActivity {
    DownloadManager downloadManager;
    Button bt1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.download);
        Intent intent = getIntent();

        String id = intent.getStringExtra(Config.EMP_ID);
        bt1=(Button)findViewById(R.id.bt1);
        downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        //Uri uri= Uri.parse("http://www.lawcommission.gov.np/en/documents/2015/08/lok-sewa-aayog-karyabidhi-118.pdf");
            try {
                Uri uri = Uri.parse(id);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);

            }catch (Exception e){
                e.printStackTrace();
            }

            }

}
